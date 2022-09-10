package com.antino.avengers.presentation.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.antino.avengers.data.pojo.getReviewsApi.response.Data
import com.antino.avengers.databinding.DeveloperListChildBinding

class DeveloperAdapter(
    private var list: MutableList<com.antino.avengers.data.pojo.getDevelopersApi.Data?>?,
    val context: Context
) : RecyclerView.Adapter<DeveloperAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            DeveloperListChildBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.managerName.text = list?.get(position)?.name.toString()
        holder.email.text = list?.get(position)?.email.toString()
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    class ViewHolder(binding: DeveloperListChildBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val managerName = binding.managerName
        val email = binding.emailId
        val image = binding.developerImage
    }
}