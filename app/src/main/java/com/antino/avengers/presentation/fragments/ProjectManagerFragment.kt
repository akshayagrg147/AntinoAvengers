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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.antino.avengers.LoginViewModel
import com.antino.avengers.Others.PreferenceUtils
import com.antino.avengers.Others.Utils
import com.antino.avengers.R
import com.antino.avengers.Utils.common.PREF_USER_TOKEN
import com.antino.avengers.Utils.common.get_all_projects
import com.antino.avengers.Utils.gone
import com.antino.avengers.Utils.toast
import com.antino.avengers.data.pojo.getAllProjects.Data
import com.antino.avengers.data.pojo.getprojectbymanager.request.ByManagerRequest
import com.antino.avengers.data.pojo.getprojectbymanager.response.getProjectManagerResponse
import com.antino.avengers.databinding.FragmentProjectManagerBinding
import com.antino.avengers.presentation.Adapter.ProjectManagerAdapter
import com.antino.avengers.presentation.Adapter.VicePresidentAdapter
import com.antino.avengers.presentation.ViewModel.DeveloperViewModel
import com.antino.avengers.presentation.activity.LoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProjectManagerFragment : Fragment() {
    private val viemodal by viewModel<LoginViewModel<Any?>>()
    private val developerViewModel by viewModel<DeveloperViewModel<Any?>>()

    var list: List<getProjectManagerResponse.Data?> = emptyList()
    private lateinit var binding: FragmentProjectManagerBinding
    private var accessToken = ""

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
        accessToken = "Bearer ${PreferenceUtils.getString(PREF_USER_TOKEN)}"
        binding.swipping.setOnRefreshListener(OnRefreshListener {
            if (IsManger() == "manager") {
                viemodal.managerId(
                    accessToken,
                    ByManagerRequest(email = PreferenceUtils.getString("manager")),
                    ""
                )
            } else {
                callAllProjectsApi()
            }
            binding.swipping.setRefreshing(false)
        })
        allProjectsObserver()
        setUpObserver()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(IsManger().isNullOrEmpty()){
            Toast.makeText(requireContext(), "Please Login again", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            PreferenceUtils.clearAllPreferences()
            requireActivity().finish()
            return
        }
        if (IsManger() == "manager") {
            viemodal.managerId(
                accessToken,
                ByManagerRequest(email = PreferenceUtils.getString("manager")),
                ""
                )
        } else {
            callAllProjectsApi()
        }


        binding.logOut.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            PreferenceUtils.clearAllPreferences()
            requireActivity().finish()

        }
    }

    private fun setUpObserver() {
        viemodal.manager_livedata.observe(requireActivity()) {
            binding.shimmerViewContainerheader.root.gone()

            if (it != null){
                if (it.status == 200) {
                    if (it.data != null && it.data.isNotEmpty()){
                        list = it.data
                    }

                    val manager = ProjectManagerAdapter(list, con!!)

                    manager.notify(it.data!!)


                    Utils.setUpRecyclerOneItemLayoutStaggered(con!!, binding.rvView)
                    Log.d("listsize:", "${list}")

                    binding.rvView.adapter = manager


                    manager.setOnItemClickListener {
                        val bundle = Bundle()
                        bundle.putString("pass_id", list[it]?.id!!.toString())
                        bundle.putString("ProjectName", list[it]?.name!!.toString())
                        bundle.putString("ProjectDate", list[it]?.brief!!.toString())

                        Navigation.findNavController(binding.root)
                            .navigate(R.id.action_pm_to_developer, bundle)

                    }
                }else{
                    requireContext().toast("Something went wrong")
                }
            }else{
                requireContext().toast("Something went wrong")
            }


        }
    }

    private fun IsManger(): String {
        val loginPref = PreferenceUtils.getLogin()
        if (loginPref != null) {
            binding.LoginName.text= loginPref.name
        }
        return loginPref!!.role ?: ""
    }



    private fun callAllProjectsApi() {
        developerViewModel.getAllProjects(accessToken,get_all_projects)
    }

    private fun allProjectsObserver() {
        developerViewModel.getAllProjects.observe(viewLifecycleOwner) {
            binding.shimmerViewContainerheader.root.gone()

            try {
                setUpAdapter(it.data)
            } catch(e: Exception) {
                requireActivity().toast("Something Went Wrong")
            }
        }
    }

    private fun setUpAdapter(list: List<Data?>?) {
        Utils.setUpRecyclerOneItemLayoutStaggered(requireContext(),binding.rvView)
        val manager = VicePresidentAdapter(list!!, con!!)
        binding.rvView.adapter = manager

        manager.setOnItemClickListener {
            val bundle = Bundle()
            bundle.putString("pass_id", list[it]?.id!!.toString())
            bundle.putString("ProjectName", list[it]?.name!!.toString())
            bundle.putString("ProjectDate", list[it]?.brief!!.toString())
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_pm_to_developer, bundle)

        }
    }

}