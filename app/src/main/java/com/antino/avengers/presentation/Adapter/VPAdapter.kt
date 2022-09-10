package com.antino.avengers.presentation.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.antino.avengers.ProjectManagersModel
import com.antino.avengers.databinding.ManagerItemsBinding
import com.antino.avengers.databinding.VpItemsBinding


class VPAdapter(
    private var serviceslist: List<ProjectManagersModel>,
    val context: Context
) : RecyclerView.Adapter<VPAdapter.PostViewHolder>() {
    private var onItemClickListener: ((position: Int) -> Unit)? = null


    fun setOnItemClickListener(onItemClickListener: (position: Int) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = VpItemsBinding.inflate(LayoutInflater.from(parent.context))
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.OnBind(serviceslist[position])
    }

//    override fun getItemCount(): Int = categories1.size

    inner class PostViewHolder(  private val binding: VpItemsBinding) : RecyclerView.ViewHolder(binding.root) {
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