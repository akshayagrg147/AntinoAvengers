package com.antino.avengers.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.antino.avengers.Others.Utils
import com.antino.avengers.ProjectManagersModel
import com.antino.avengers.R
import com.antino.avengers.databinding.FragmentProjectManagerBinding
import com.antino.avengers.databinding.FragmentVPBinding
import com.antino.avengers.presentation.Adapter.ProjectManagerAdapter
import com.antino.avengers.presentation.Adapter.VPAdapter

class VPFragment: Fragment() {

    val list:ArrayList<ProjectManagersModel> = ArrayList()
    private lateinit var binding: FragmentVPBinding
    private lateinit var manager: VPAdapter
    var con: Context? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        con = context
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        list.add(ProjectManagersModel(name = "Akshay"))
        list.add(ProjectManagersModel(name = "Saundarya"))
        list.add(ProjectManagersModel(name = "Ankit"))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVPBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = requireActivity()
        binding.executePendingBindings()
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Utils.setUpRecyclerOneItemLayoutStaggered(con!!, binding.recyclerView)
        manager = VPAdapter(list, con!!)
        binding.recyclerView.adapter = manager
        manager.setOnItemClickListener {

            Log.d("testing", "onViewCreated: ${it}")
        }
    }
}