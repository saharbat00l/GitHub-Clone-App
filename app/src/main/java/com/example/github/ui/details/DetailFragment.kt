package com.example.github.ui.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.github.R
import com.example.github.api.ApiClient
import com.example.github.api.ApiService
import com.example.github.api.Repository
import com.example.github.databinding.FragmentDetailBinding
import com.example.github.ui.main.MainViewModel
import com.example.github.ui.main.MainViewModelFactory

class DetailFragment : Fragment() {

    lateinit var mainViewModel: MainViewModel
    val apiService = ApiClient.retrofit().create(ApiService::class.java)
    val repository = Repository(apiService)
    private lateinit var binding: FragmentDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        mainViewModel = ViewModelProvider(requireActivity(), MainViewModelFactory(repository)).get(MainViewModel::class.java)
        val selectedUser = mainViewModel.getSelectedUser()

        selectedUser?.let {
            Toast.makeText(context, it.login, Toast.LENGTH_LONG).show()
        }
    }
}