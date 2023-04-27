package com.example.myapplication.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.myapplication.api.ArticleJson
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.db.ArticleDatabase
import com.example.myapplication.network.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://newsdata.io/api/1/"

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://myserver.com/")
            .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val myApi = retrofit.create(Api::class.java)

//        viewModelScope.launch {
//            try {
//                val articles = myApi.getArticles()
//                // do something with the list of articles
//            } catch (e: Exception) {
//                // handle any errors that occur
//            }
//        }


        return root
    }
}

//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }

//    private fun fetchArticleList() {
//
//        val api = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(Api::class.java)
//
//
//        GlobalScope.launch(Dispatchers.IO) {
//            val response: Response<ArticleJson> = api.getNews().awaitResponse()
//            Log.d("api", response.message())
//            if (response.isSuccessful) {
//                val res = response.body()
//                if (res != null) {
//
//                    withContext(Dispatchers.Main) {
//                        mArticles.addAll(res.ResultXObjects!!)
//                        mAdapter!!.notifyDataSetChanged()
//
//
//                        val database = context?.let {
//                            Room.databaseBuilder(
//                                it.applicationContext,
//                                ArticleDatabase::class.java, "article-db"
//                            ).build()
//                        }
//
//                        val exampleDao = database.articleDao()
//                        exampleDao.insertAllExampleObjects(listOf(res))
//                    }
//                }
//            }
//        }
//
//    }