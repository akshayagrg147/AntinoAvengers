package com.antino.avengers.presentation.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.antino.avengers.ProjectManagersModel
import com.antino.avengers.Utils.gone
import com.antino.avengers.Utils.toast
import com.antino.avengers.Utils.visible
import com.antino.avengers.data.pojo.getprojectbymanager.response.getProjectManagerResponse
import com.antino.avengers.databinding.ManagerItemsBinding
import com.antino.avengers.databinding.ProjectListContainerBinding
import com.bumptech.glide.Glide


class ProjectManagerAdapter(
    private var serviceslist: List<getProjectManagerResponse.Data?>,
    val context: Context
) : RecyclerView.Adapter<ProjectManagerAdapter.PostViewHolder>() {
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

                    Log.d("listsize:11", "${position}")

                    notifyDataSetChanged()
                    onItemClickListener?.invoke(position)
                }
            }
        }
        fun OnBind(categories1: getProjectManagerResponse.Data?) {
            binding.projectManagerName.text = categories1?.manager?.name
            binding.projectDiscription.text=categories1?.brief
            binding.projectName.text=categories1?.name
            binding.projectStartDate.gone()
//            var imange= categories1?.manager?.image
//            if (!imange.isNullOrEmpty()){
//                if (imange.contains("http")){
//                    val temp=  imange.split("http")[0].replace("http", "https")
//                    var temp2= "${temp[0]}+${temp[1]}"
//                    imange= temp2
//                }
//            }
//            Glide.with(context).load(imange).into(binding.iconPic);



        }
    }
    override fun getItemCount(): Int {
        return serviceslist.size
    }
    fun notify(list:List<getProjectManagerResponse.Data?>){
        serviceslist=list
        notifyDataSetChanged()
    }

}