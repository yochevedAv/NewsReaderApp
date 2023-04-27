package com.example.myapplication.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myapplication.MyViewModel
import com.example.myapplication.adapter.RecyclerViewNewsAdapter
import com.example.myapplication.api.ResultX
import com.example.myapplication.data.Resource
import com.example.myapplication.databinding.FragmentNewsBinding

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding
    private lateinit var adapter: RecyclerViewNewsAdapter
    private val viewModel: MyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.myData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> showLoading()
                is Resource.Success -> showData(result.data)
                is Resource.Error -> showError(result.message)
            }
        }
        viewModel.loadData()
    }

    private fun showLoading() {
        // Show a loading indicator
    }

    private fun showData(data: List<ResultX>) {
        adapter = RecyclerViewNewsAdapter(data)
        binding.recyclerView.adapter = adapter
    }

    private fun showError(message: String) {
        // Show an error message
    }
}
