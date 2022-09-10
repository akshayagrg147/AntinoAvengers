package com.antino.avengers.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.antino.avengers.LoginViewModel
import com.antino.avengers.Others.PreferenceUtils
import com.antino.avengers.Others.Utils
import com.antino.avengers.R
import com.antino.avengers.data.pojo.getprojectbymanager.request.ByManagerRequest
import com.antino.avengers.data.pojo.getprojectbymanager.response.getProjectManagerResponse
import com.antino.avengers.databinding.FragmentProjectManagerBinding
import com.antino.avengers.presentation.Adapter.ProjectManagerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProjectManagerFragment : Fragment() {
    private val viemodal by viewModel<LoginViewModel<Any?>>()
     var list: List<getProjectManagerResponse.Data?> = emptyList()
    private lateinit var binding: FragmentProjectManagerBinding
    private lateinit var manager: ProjectManagerAdapter
    private var mCallback: FragmentToActivity? = null
    var con: Context? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mCallback = context as FragmentToActivity
        con = context


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProjectManagerBinding.inflate(inflater, container, false)
/*
        binding.lifecycleOwner = requireActivity()
        binding.executePendingBindings()*/
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viemodal.managerId(
            ByManagerRequest(email =  PreferenceUtils.getString("manager")),
            "",

            )
        setUpObserver()
        Utils.setUpRecyclerOneItemLayoutStaggered(con!!, binding.rvView)
        Log.d("listsize:", "${list}")
        manager = ProjectManagerAdapter(list, con!!)
        binding.rvView.adapter = manager
        manager.setOnItemClickListener {
            Log.d("listsize:99", "${mCallback?.clicked(list[it]?.id!!)}")

            mCallback?.clicked(list[it]?.id!!)

//            Log.d("testing", "onViewCreated: ${it}")
        }
    }

    private fun setUpObserver() {
        viemodal.manager_livedata.observe(requireActivity()) {

            if (it.status == 200) {
                if (it.data != null && it.data.isNotEmpty())
                    list=it.data
                Log.d("listsize:", "${list}")

                manager.notify(it.data!!)
            }

        }
    }
    interface FragmentToActivity {

        fun clicked(data: Int)

    }
}