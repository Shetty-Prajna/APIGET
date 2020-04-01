package com.example.retrofitdemo.Adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitdemo.model.Post
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.retrofitdemo.R
import kotlinx.android.synthetic.main.recycle_post_view.view.*

class UserAdapter(posts: List<Post>, var onClickListener: View.OnClickListener) : RecyclerView.Adapter<RetrofitViewHolder>() {

    val dataSet = posts
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetrofitViewHolder {
        return RetrofitViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycle_post_view,
                parent,
                false
            ), onClickListener
        )

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
    override fun onBindViewHolder(holder: RetrofitViewHolder, position: Int) {

       holder.initialize(dataSet[position])
     }

}
class RetrofitViewHolder( itemView: View,onClickListener: View.OnClickListener) : RecyclerView.ViewHolder(itemView) {
    fun initialize(post: Post){
        itemView.rvTextTittle.text =post.title
        itemView.rvTextContent.text = post.body
        itemView.tag = post
    }
    init {
        itemView.setOnClickListener(onClickListener)
    }


}

