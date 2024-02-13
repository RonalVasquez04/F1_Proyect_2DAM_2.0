package es.rfvl.f1.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import es.rfvl.f1.R
import es.rfvl.f1.classes.ChatMessage

class chatMessageAdapter(private val context: Context, private val mensajes: MutableList<ChatMessage> ): RecyclerView.Adapter<chatMessageAdapter.ChatMessageViewFolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatMessageViewFolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rec_message_chat, parent, false)
        return ChatMessageViewFolder(view)
    }

    override fun onBindViewHolder(holder: ChatMessageViewFolder, position: Int) {
        val chat = mensajes[position]

        val prefs = context.getSharedPreferences("es.rfvl.f1_preferences", Context.MODE_PRIVATE)
        val colorHex = prefs.getString("color", "#000000")
        val color = if (colorHex != null) {
            getColorFromHex(colorHex)
        } else {
            Color.BLACK
        }



        holder.itemView.findViewById<CardView>(R.id.cardViewMessageChat).setCardBackgroundColor(color)

        holder.bindItem(chat)

    }
    private fun getColorFromHex(hexColor: String): Int {
        return Color.parseColor(hexColor)
    }

    override fun getItemCount(): Int {
        return mensajes.size
    }

    class ChatMessageViewFolder(view: View): RecyclerView.ViewHolder(view){
        private val texto: TextView = view.findViewById(R.id.textMessageChat)
        private val user: TextView = view.findViewById(R.id.textUserChat)

        fun bindItem(i: ChatMessage){
            texto.text = i.mensaje
            user.text = i.user
        }
    }

}