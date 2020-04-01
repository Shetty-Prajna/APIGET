package com.example.retrofitdemo.ViewModel


import androidx.lifecycle.MutableLiveData
import com.example.retrofitdemo.ApiCall.ApiClient
import com.example.retrofitdemo.ApiCall.ApiManager
import com.example.retrofitdemo.model.Post
import com.example.retrofitdemo.model.PostComment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PostDetails{
    var postDetailsList : MutableLiveData<List<Post>> = MutableLiveData()
    var postCommentList : MutableLiveData<List<PostComment>> = MutableLiveData()
    var isLoading : MutableLiveData<Boolean> = MutableLiveData()

    fun getListofPost() {
        isLoading.postValue(true)
        val service: ApiClient? = ApiManager.getServices()
        if (service != null) {
            service?.getPosts()?.enqueue(object : retrofit2.Callback<List<Post>> {
                override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                    response.body()?.let {
                        postDetailsList.postValue(it)
                        isLoading.postValue(false)
                    }
                }

                override fun onFailure(call: Call<List<Post>>, throwable: Throwable) {
                    isLoading.postValue(false)

                }

            })
        }

    }

    fun getListofComment(id:Int?) {
        isLoading.postValue(true)
        val service: ApiClient? =
            ApiManager.getServices()
        if (id != null) {
            service?.getPostcomments(id)?.enqueue(object : Callback<List<PostComment>> {
                override fun onResponse(
                    call: Call<List<PostComment>>,
                    response: Response<List<PostComment>>
                ) {
                    response.body()?.let {
                        postCommentList.postValue(it)
                        isLoading.postValue(false)
                    }
                }

                override fun onFailure(call: Call<List<PostComment>>, throwable: Throwable) {
                    isLoading.postValue(false)

                }
            })
        }
    }

}