package com.example.myapplication.ui.movies

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.ApiRequests
import com.example.myapplication.api.moviesJson
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.databinding.FragmentMoviesBinding
import com.example.myapplication.ui.home.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://moviesdatabase.p.rapidapi.com"

class MoviesFragment : Fragment(){

    private var _binding: FragmentMoviesBinding? = null
    private var TAG = "MoviesFragment"

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val moviesViewModel =
            ViewModelProvider(this)[MoviesViewModel::class.java]

        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.movieName
        moviesViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

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
            val response: Response<moviesJson> = api.getMovies().awaitResponse()
            Log.d("api",response.message())
            if(response.isSuccessful){
                val data: moviesJson = response.body()!!
                Log.d(TAG, data.next)

                withContext(Dispatchers.Main){
                   _binding?.movieName?.text = data.next[0].toString()
                }
            }
        }
    }


}

