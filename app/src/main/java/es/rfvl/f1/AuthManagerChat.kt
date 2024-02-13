package es.rfvl.f1

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresApi
import com.google.firebase.firestore.FirebaseFirestore
import es.rfvl.f1.classes.ChatMessage
import kotlinx.coroutines.tasks.await
import com.google.firebase.Timestamp
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class AuthManagerChat() {
    val myDBChat: FirebaseFirestore = FirebaseFirestore.getInstance()

    val coleccionChat = myDBChat.collection("Messages")




    suspend fun getData(): MutableList<ChatMessage>{
        val datos = mutableListOf<ChatMessage>()
        val ordenHora = coleccionChat.orderBy("hora").get().await()
        for (chatMensaje in ordenHora){
            val datoChat = chatMensaje.data
            datos.add(ChatMessage(mensaje = datoChat["texto"].toString(), user = datoChat["user"].toString()))
        }
        return datos
    }

    fun addData(texto: String, user: String){
        coleccionChat.add(mapOf(
            "texto" to texto,
            "hora" to Timestamp.now(),
            "user" to user
        ))
    }

    suspend fun getDataFLow(): Flow<List<ChatMessage>> = callbackFlow {

        try {
            val subscription = coleccionChat.orderBy("hora").addSnapshotListener { snapshot, _ ->
                if (snapshot != null){
                    val mensajes = mutableListOf<ChatMessage>()
                    snapshot.forEach{
                        mensajes.add(
                            ChatMessage(
                                mensaje = it.get("texto").toString(), user = it.get("user").toString()
                            )
                        )
                    }
                    trySend(mensajes)
                }
            }
            awaitClose { subscription?.remove() }
        }catch (e: Throwable){
            close(e)
        }
    }

}