package com.princeabdurrahman.newsapi

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.size.Scale
import com.princeabdurrahman.newsapi.Model.ArticlesItem
import com.princeabdurrahman.newsapi.databinding.CdvNewsHeadlineBinding

class CdvNewsHeadlineAdapter : RecyclerView.Adapter<CdvNewsHeadlineVH>(){
    // untuk mengambil data di dalam model article
    private val listData = ArrayList<ArticlesItem>()

    // Fungsi ini berfungsi untuk add data ke dalam Recyclerview
    fun addData(items: List<ArticlesItem>){
        listData.clear()
        listData.addAll(items)
        notifyDataSetChanged()
    }

    // berfungsi untuk menginflate atau merupakan operasi yang di buat kedalam layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CdvNewsHeadlineVH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CdvNewsHeadlineBinding.inflate(layoutInflater, parent, false)
        return CdvNewsHeadlineVH(binding)
    }

    // digunakan untuk mengetahui panjang/banyak data (size) guna kebutuhan looping
    override fun getItemCount(): Int {
        return listData.size
    }

    //digunakan untuk memposisikan widget pada layout model
    override fun onBindViewHolder(holder: CdvNewsHeadlineVH, position: Int) {
        holder.bind(listData[position])

    }
}

    //sebagai adapter recyclerview
class CdvNewsHeadlineVH (private val binding: CdvNewsHeadlineBinding):
    RecyclerView.ViewHolder(binding.root){
        fun bind(article: ArticlesItem){
            binding.run {
                txtTitle.text = cropTerxt(article.title?: "Tidak ada judul")
                txtSubtitle.text = article.publishedAt
                imgHeadline.apply {
                    load(article.urlToImage){
                        scale(Scale.FILL)
                    }
                    contentDescription = article.description
                }
                // untuk melakukan aksi klik pada gambar
                root.setOnClickListener {
                    val intent = Intent(it.context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.DETAIL_NEWS, article) // DETAIL_NEWS berfungsi
                    }

                    it.context.startActivity(intent)
                }
            }
        }
        private fun cropTerxt(text: String): String{
            return text.take(50) + if (text.length > 50)"..." else ""
        }

}
