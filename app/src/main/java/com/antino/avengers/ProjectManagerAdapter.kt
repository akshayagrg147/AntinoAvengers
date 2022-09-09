package com.antino.avengers

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.antino.avengers.databinding.ManagerItemsBinding



class ProjectManagerAdapter(
    private var serviceslist: List<ProjectManagersModel>,
    val context: Context
) : RecyclerView.Adapter<ProjectManagerAdapter.PostViewHolder>() {
    private var onItemClickListener: ((position: Int) -> Unit)? = null


    fun setOnItemClickListener(onItemClickListener: (position: Int) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ManagerItemsBinding.inflate(LayoutInflater.from(parent.context))
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.OnBind(serviceslist[position])
    }

//    override fun getItemCount(): Int = categories1.size

    inner class PostViewHolder(  private val binding: ManagerItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {

                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {


                    notifyDataSetChanged()
                    onItemClickListener?.invoke(position)
                }
            }
        }
        fun OnBind(categories1: ProjectManagersModel) {
            binding.serviceName.text = categories1.name

        }
    }
    override fun getItemCount(): Int {
        return serviceslist.size
    }

}