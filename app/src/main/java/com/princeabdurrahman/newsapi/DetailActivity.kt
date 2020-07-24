package com.princeabdurrahman.newsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import coil.api.load
import coil.size.Scale
import com.princeabdurrahman.newsapi.Model.ArticlesItem
import com.princeabdurrahman.newsapi.databinding.ActivityDetailBinding
import kotlinx.android.synthetic.main.activity_main.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val DETAIL_NEWS = "DETAIL_NEWS"
    }

    // untuk menampilkan view, karena kita akan menampilkan detail Activity maka yang di extend Activity
    // jika yang di extend MainActivity maka yang di extend
    // intinya tinggal ditambahin tulisan binding di akhir
    private lateinit var binding:ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        // untuk menampilkan data yang dikirim oleh MainActivity melalui CDVHeadlineAdapter
        val data = intent.getParcelableExtra(DETAIL_NEWS) as ArticlesItem

        // untuk membuild Layout
        binding.run {
            setContentView(root)

            setSupportActionBar(toolBar)

            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            title = data.title

            imgToolbar.apply{
               load(data.urlToImage){
                   scale(Scale.FILL)
               }
                contentDescription = data.description
            }
            // untuk get content
            txtContent.text = data.content

            //untuk get publishAt
            txtDate.text = data.publishedAt
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}