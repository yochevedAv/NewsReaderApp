package com.example.myapplication.ui.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.ApiRequests
import com.example.myapplication.adapters.recyclerViewNewsAdapter
import com.example.myapplication.api.*
import com.example.myapplication.databinding.FragmentNewsBinding
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

    private var mAdapter: recyclerViewNewsAdapter? = null;
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

        /*val textView: TextView = binding.textNews
        newsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/

        binding.recyclerView!!.layoutManager = LinearLayoutManager(context)

        mAdapter = context?.let { recyclerViewNewsAdapter(mArticles, it) }
        binding.recyclerView!!.adapter = mAdapter


        fetchArticleList()


        return root

    }

    private fun fetchArticleList() {

        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequests::class.java)


        GlobalScope.launch(Dispatchers.IO) {
            val response: Response<ArticleJson> = api.getNews().awaitResponse()
            Log.d("api", response.message())
            if (response.isSuccessful) {
                val res = response.body()
                if (res != null) {


                    val data: ArticleJson = response.body()!!
                    Log.d(TAG, data.status + " " + data.results)
                    Log.d(TAG, mArticles.toString())
                    Log.d(TAG, mAdapter.toString())
                    withContext(Dispatchers.Main) {
                        mArticles.addAll(res.results!!)
                        //mAdapter = recyclerViewNewsAdapter( mArticles)
                        mAdapter!!.notifyDataSetChanged()
                    }
                }
            }
        }
    }


}




