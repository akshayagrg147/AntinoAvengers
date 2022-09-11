package com.antino.avengers.presentation.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

import com.antino.avengers.LoginViewModel
import com.antino.avengers.Others.PreferenceUtils
import com.antino.avengers.Others.Utils
import com.antino.avengers.R
import com.antino.avengers.data.pojo.getprojectbymanager.request.ByManagerRequest
import com.antino.avengers.data.pojo.getprojectbymanager.response.getProjectManagerResponse
import com.antino.avengers.databinding.FragmentProjectManagerBinding
import com.antino.avengers.presentation.Adapter.ProjectManagerAdapter
import com.antino.avengers.presentation.activity.HomeActivity
import com.antino.avengers.presentation.activity.LoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProjectManagerFragment : Fragment() {
    private val viemodal by viewModel<LoginViewModel<Any?>>()
    var list: List<getProjectManagerResponse.Data?> = emptyList()
    private lateinit var binding: FragmentProjectManagerBinding
    private lateinit var manager: ProjectManagerAdapter

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
        binding = FragmentProjectManagerBinding.inflate(inflater, container, false)
/*
        binding.lifecycleOwner = requireActivity()
        binding.executePendingBindings()*/
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(IsManger().isNullOrEmpty()){
            Toast.makeText(requireContext(), "Please Login again", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            PreferenceUtils.clearAllPreferences()
            return
        }
        if (IsManger() == "manager") {
            viemodal.managerId(
                ByManagerRequest(email = PreferenceUtils.getString("manager")),
                "",
                )

            setUpObserver()
            Utils.setUpRecyclerOneItemLayoutStaggered(con!!, binding.rvView)
            Log.d("listsize:", "${list}")
            manager = ProjectManagerAdapter(list, con!!)
            binding.rvView.adapter = manager

            manager.setOnItemClickListener {
                val bundle = Bundle()
                bundle.putString("pass_id", list[it]?.id!!.toString())
                Navigation.findNavController(binding.root)
                    .navigate(R.id.action_pm_to_developer, bundle)

            }
        } else {
            viemodal.managerId(
                ByManagerRequest(email = PreferenceUtils.getString("manager")),
                "",

                )
        }


    }

    private fun setUpObserver() {
        viemodal.manager_livedata.observe(requireActivity()) {

            if (it.status == 200) {
                if (it.data != null && it.data.isNotEmpty())
                    list = it.data
                Log.d("listsize:", "${list}")

                manager.notify(it.data!!)
            }

        }
    }

    private fun IsManger(): String {
        val loginPref = PreferenceUtils.getLogin()
        return loginPref!!.role ?: ""
    }


}