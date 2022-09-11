package com.antino.avengers.presentation.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.antino.avengers.databinding.ChildReviewItemBinding

class ReviewAdapter(
    private var list: MutableList<com.antino.avengers.data.pojo.getReviewsApi.response.Data?>?,
    val context: Context
) : RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {
    private var onItemClickListener: ((position: Int) -> Unit)? = null


    fun setOnItemClickListener(onItemClickListener: (position: Int) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            ChildReviewItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.date.text = list?.get(position)?.review.toString()
        holder.time.text = list?.get(position)?.reviewTime.toString()

        holder.layyy.setOnClickListener {
            onItemClickListener?.invoke(position)


        }
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    class ViewHolder(binding: ChildReviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val date = binding.date
        val time = binding.time
        val layyy = binding.layout

    }
}