package com.example.github.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.github.R
import com.example.github.api.ApiClient
import com.example.github.api.ApiService
import com.example.github.api.Repository
import com.example.github.data.UserResponse
import com.example.github.databinding.ActivityMainBinding
import com.example.github.databinding.FragmentListBinding
import com.example.github.ui.details.DetailFragment


class ListFragment : Fragment(), ClickListener {

    lateinit var mainViewModel: MainViewModel
    val apiService = ApiClient.retrofit().create(ApiService::class.java)
    val repository = Repository(apiService)

    private lateinit var binding: FragmentListBinding
    private val adapter by lazy {
        Adapter(mutableListOf(), this)
        //our adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //populating recyclerview
        binding.recyclerView.layoutManager= LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter

        //API service
        mainViewModel = ViewModelProvider(requireActivity(), MainViewModelFactory(repository)).get(MainViewModel::class.java)
        val button = binding.btnSearch
        button.setOnClickListener {
            //  button.isEnabled = false
            mainViewModel.searchUser(binding.enterQuery.text.toString())
        }
        mainViewModel.users.observe(viewLifecycleOwner, Observer {
            Log.d("SeharBatool", it.items.toString())

            adapter.setData(it.items)
        })

    }

    override fun onClick(index: Int, user: UserResponse.Item) {
//        mainViewModel.setSelectedIndex(index)
        mainViewModel.setSelectedUser(user)

        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(
                requireActivity().findViewById<FrameLayout>(R.id.fragment_container).id,
                DetailFragment()
            )
            addToBackStack(null)
            commit()
        }

    }

}