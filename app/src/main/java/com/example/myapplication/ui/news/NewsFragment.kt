package com.example.myapplication.ui.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.ApiRequests
import com.example.myapplication.api.NewsJson
import com.example.myapplication.api.moviesJson
import com.example.myapplication.databinding.FragmentNewsBinding
import com.example.myapplication.ui.movies.BASE_URL
import com.example.myapplication.ui.movies.MoviesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://extract-news.p.rapidapi.com/"

class NewsFragment: Fragment() {
    private var _binding: FragmentNewsBinding? = null
    private var TAG = "NewsFragment"

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val newsViewModel =
            ViewModelProvider(this)[NewsViewModel::class.java]

        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        val root: View = binding.root

       /* val textView: TextView = binding
        NewsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/

        getCurrentData()


        return root

    }

    private fun getCurrentData(){

        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequests::class.java)


        GlobalScope.launch(Dispatchers.IO) {
            val response: Response<NewsJson> = api.getNews().awaitResponse()
            Log.d("api",response.message())
            if(response.isSuccessful){
                val data: NewsJson = response.body()!!
                Log.d(TAG, data.status + " " + data.article)

                withContext(Dispatchers.Main){
                    //_binding?.movieName?.text = data.next[0].toString()
                }
            }
        }
    }


}




