package com.antino.avengers.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.antino.avengers.Others.Utils
import com.antino.avengers.ProjectManagersModel
import com.antino.avengers.R
import com.antino.avengers.Utils.common.get_all_projects
import com.antino.avengers.Utils.toast
import com.antino.avengers.data.pojo.getAllProjects.Data
import com.antino.avengers.databinding.FragmentProjectManagerBinding
import com.antino.avengers.databinding.FragmentVPBinding
import com.antino.avengers.presentation.Adapter.DeveloperAdapter
import com.antino.avengers.presentation.Adapter.ProjectManagerAdapter
import com.antino.avengers.presentation.Adapter.VPAdapter
import com.antino.avengers.presentation.Adapter.VicePresidentAdapter
import com.antino.avengers.presentation.ViewModel.DeveloperViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class VPFragment: Fragment() {
    private val developerViewModel by viewModel<DeveloperViewModel<Any?>>()

    private lateinit var binding: FragmentVPBinding
    private lateinit var manager: VPAdapter
    var con: Context? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        con = context
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        callAllProjectsApi()
        setUpObserver()
    }

    private fun callAllProjectsApi() {
        developerViewModel.getAllProjects(get_all_projects)
    }

    private fun setUpObserver() {
        developerViewModel.getAllProjects.observe(viewLifecycleOwner) {
            try {
                setUpAdapter(it.data)
            } catch(e: Exception) {
                requireActivity().toast("Something Went Wrong")
            }
        }
    }

    private fun setUpAdapter(list: List<Data?>?) {
        Utils.setUpRecyclerOneItemLayoutStaggered(requireContext(),binding.recyclerView)
        val manager = VicePresidentAdapter(list!!, con!!)
        binding.recyclerView.adapter = manager
    }
}