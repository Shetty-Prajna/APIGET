package com.example.retrofitdemo.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class PostViewModel(application: Application) : AndroidViewModel(application){

    var postDetailsRepo = PostDetails()
    var postDetailsList = postDetailsRepo.postDetailsList
    var postCommentList=postDetailsRepo.postCommentList
    var isLoading = postDetailsRepo.isLoading

    fun getPostDetails(){
        postDetailsRepo.getListofPost()

    }

   fun getPostCommentsDetails(id: Int?) {
        postDetailsRepo.getListofComment(id)
    }

}
