package com.antino.avengers.presentation.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.antino.avengers.Utils.gone
import com.antino.avengers.data.pojo.getReviewsApi.response.Data
import com.antino.avengers.databinding.DeveloperListChildBinding
import com.antino.avengers.databinding.ProjectListContainerBinding

class DeveloperAdapter(
    private var list: MutableList<com.antino.avengers.data.pojo.getDevelopersApi.Data?>?,
    val context: Context
) : RecyclerView.Adapter<DeveloperAdapter.PostViewHolder>() {
    private var onItemClickListener: ((position: Int) -> Unit)? = null


    fun setOnItemClickListener(onItemClickListener: (position: Int) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostViewHolder {
        val binding =
            DeveloperListChildBinding.inflate(LayoutInflater.from(context), parent, false)
        return PostViewHolder(binding)
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

    inner class PostViewHolder(  private val binding: DeveloperListChildBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {

                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {


                    notifyDataSetChanged()
                    onItemClickListener?.invoke(position)
                }
            }
        }
        fun OnBind(categories1: com.antino.avengers.data.pojo.getDevelopersApi.Data) {
            binding.managerName .text = categories1?.name
            binding.emailId .text=categories1?.email
            binding.devTech .text=categories1?.profile
//            binding.developerImage.text=categories1?.name

        }
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.OnBind(list!![position]!!)
    }
}