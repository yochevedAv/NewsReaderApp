package com.example.myapplication.ui.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.myapplication.api.*
import com.example.myapplication.databinding.FragmentNewsBinding
import com.example.myapplication.db.ArticleDatabase
import com.example.myapplication.network.Api
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://newsdata.io/api/1/"

class NewsFragment : Fragment() {
    private var _binding: FragmentNewsBinding? = null
    private var TAG = "NewsFragment"
    private val binding get() = _binding!!

    private var mAdapter: RecyclerViewNewsAdapter? = null;
    private var mArticles: MutableList<ResultX> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val newsViewModel =
            ViewModelProvider(this)[NewsViewModel::class.java]

        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        val root: View = binding.root


        binding.recyclerView!!.layoutManager = LinearLayoutManager(context)

        mAdapter = context?.let { RecyclerViewNewsAdapter(mArticles) }
        binding.recyclerView!!.adapter = mAdapter


        fetchArticleList()


        return root

    }

    private fun fetchArticleList() {

        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)


        GlobalScope.launch(Dispatchers.IO) {
            val response: Response<ArticleJson> = api.getNews().awaitResponse()
            Log.d("api", response.message())
            if (response.isSuccessful) {
                val res = response.body()
                if (res != null) {

                    withContext(Dispatchers.Main) {
                        mArticles.addAll(res.results)
                        mAdapter!!.notifyDataSetChanged()




//                        val database = Room.databaseBuilder(
//                            requireContext().applicationContext,
//                            ArticleDatabase::class.java, "article-db"
//                        ).build()
//
//                        val exampleDao = database.articleDao()
//                        exampleDao.insertAllExampleObjects(listOf(res))
                    }
                }
            }
        }

    }


}




