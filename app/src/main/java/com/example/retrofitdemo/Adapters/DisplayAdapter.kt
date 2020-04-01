package com.example.retrofitdemo.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitdemo.R
import com.example.retrofitdemo.model.PostComment
import kotlinx.android.synthetic.main.recycle_post_view.view.*


class DisplayAdapter(data: List<PostComment>) : RecyclerView.Adapter<DisplayViewHolder>() {

    val dataSet = data
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisplayViewHolder {
        return DisplayViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recycle_post_view,
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: DisplayViewHolder, position: Int) {

        holder.initialize(dataSet[position])
    }

}

class DisplayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun initialize(post: PostComment) {
        itemView.rvTextTittle.text = post.name
        itemView.rvTextContent.text = post.email
        itemView.tag = post
    }


}

