package com.example.retrofitdemo.Fragments

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitdemo.Adapters.UserAdapter
import com.example.retrofitdemo.R
import com.example.retrofitdemo.ViewModel.PostViewModel
import com.example.retrofitdemo.model.Post
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_main.*



class MainFragment : Fragment(),View.OnClickListener {
    var postViewModel: PostViewModel? = null
    private var snackbar: Snackbar? = null
    override fun onClick(v: View?) {
        Log.d("TAG", "clicked")
        when (v?.id) {

            R.id.rvContainerLayout -> {
                Log.d("TAG", "inside when")
                val fragment = DisplayFragment()
                val bundle = Bundle()
                val postDetail = v.tag as Post
                bundle.putParcelable("id",postDetail)
                fragment.arguments = bundle
                val connectivityManager = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val network = connectivityManager.activeNetwork
                if (network != null) {
                fragmentManager?.beginTransaction()?.replace(R.id.framefragment,fragment)
                    ?.addToBackStack(null)?.commit()
                } /*else {

                    snackbar= Snackbar.make(fragmentmain, getString(R.string.internet), Snackbar.LENGTH_INDEFINITE).setAction(
                        "Ok"
                    ) { snackbar?.dismiss() }
                    snackbar?.show()

                }*/

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            return inflater.inflate(R.layout.fragment_main, container, false)

        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

            super.onViewCreated(view, savedInstanceState)
            postViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
                .create(PostViewModel::class.java)
            postViewModel?.getPostDetails()
            observeStates()
            }

    private fun observeStates() {
        postViewModel?.isLoading?.observe(this,androidx.lifecycle.Observer{
            it?.let {
                if (it) {
                    progressBar.visibility = View.VISIBLE
                } else {
                    progressBar.visibility = View.GONE
                }
            }
        })
        postViewModel?.postDetailsList?.observe(this, androidx.lifecycle.Observer {
            it?.let {
                if (it.isNotEmpty()) {
                    loadDataList(it)
                }
                else{
                    snackbar= Snackbar.make(fragmentmain,getString(R.string.data), Snackbar.LENGTH_INDEFINITE).setAction(
                        "Ok"
                    ) { snackbar?.dismiss() }
                    snackbar?.show()

                }
            }
        })
    }

        private fun loadDataList(users: List<Post>) {
            rv.layoutManager = LinearLayoutManager(context)
            rv.adapter = UserAdapter(users, this)
        }


    }



