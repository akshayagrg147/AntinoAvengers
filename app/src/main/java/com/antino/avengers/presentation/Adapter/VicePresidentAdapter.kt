package com.antino.avengers.presentation.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.antino.avengers.Utils.gone
import com.antino.avengers.data.pojo.getAllProjects.Data
import com.antino.avengers.databinding.ProjectListContainerBinding

class VicePresidentAdapter(
    private var serviceslist: List<Data?>,
    val context: Context
)  : RecyclerView.Adapter<VicePresidentAdapter.PostViewHolder>() {
    private var onItemClickListener: ((position: Int) -> Unit)? = null


    fun setOnItemClickListener(onItemClickListener: (position: Int) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ProjectListContainerBinding.inflate(LayoutInflater.from(parent.context))
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.OnBind(serviceslist[position])
    }

//    override fun getItemCount(): Int = categories1.size

    inner class PostViewHolder(  private val binding: ProjectListContainerBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {

                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {


                    notifyDataSetChanged()
                    onItemClickListener?.invoke(position)
                }
            }
        }
        fun OnBind(categories1: Data?) {
            binding.projectManagerName.text = categories1?.manager?.name
            binding.projectDiscription.text=categories1?.brief
            binding.projectName.text=categories1?.name
            binding.projectStartDate.gone()

        }
    }
    override fun getItemCount(): Int {
        return serviceslist.size
    }
    fun notify(list:List<Data?>){
        serviceslist=list
        notifyDataSetChanged()
    }

}