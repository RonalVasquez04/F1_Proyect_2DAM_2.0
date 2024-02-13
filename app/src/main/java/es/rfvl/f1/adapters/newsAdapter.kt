package es.rfvl.f1.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.rfvl.f1.R
import es.rfvl.f1.classes.Drivers
import es.rfvl.f1.classes.News

class newsAdapter(private val context: Context, private val NEWS: MutableList<News>): RecyclerView.Adapter<newsAdapter.NewsViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rec_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return NEWS.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val noticia = NEWS[position]
        holder.bindItem(noticia)
    }

    class NewsViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val imagen: ImageView = view.findViewById(R.id.imagenNoticia)
        private val textoNoticia: TextView = view.findViewById(R.id.textoNoticia)

        fun bindItem(news: News){
            imagen.setImageResource(news.imagenNews)
            textoNoticia.text = news.textoNoticia
        }
    }

}