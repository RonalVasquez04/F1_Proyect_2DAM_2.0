package es.rfvl.f1

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitObject {

    companion object {
        private var instance: Retrofit? = null

        fun getInstance(): Retrofit {
            synchronized(this){
                if (instance == null){
                    instance = Retrofit.Builder().baseUrl("http://ergast.com/api/f1/")
                        .addConverterFactory(GsonConverterFactory.create()).build()
                }
                return instance!!
            }
        }
    }
}