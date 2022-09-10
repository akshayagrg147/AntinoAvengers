package com.antino.avengers.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.antino.avengers.Others.Utils
import com.antino.avengers.ProjectManagersModel
import com.antino.avengers.R
import com.antino.avengers.Utils.common.get_developers_api
import com.antino.avengers.Utils.common.get_reviews_api
import com.antino.avengers.Utils.toast
import com.antino.avengers.data.pojo.getDevelopersApi.GetDevelopersRequest
import com.antino.avengers.data.pojo.getReviewsApi.GetReviewsRequest
import com.antino.avengers.data.pojo.getReviewsApi.response.Data
import com.antino.avengers.databinding.FragmentDeveloperBinding
import com.antino.avengers.presentation.Adapter.DeveloperAdapter
import com.antino.avengers.presentation.Adapter.ProjectManagerAdapter
import com.antino.avengers.presentation.ViewModel.DeveloperViewModel
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeveloperFragment : Fragment() {
    private lateinit var binding: FragmentDeveloperBinding
    private lateinit var manager: ProjectManagerAdapter
    private val developerViewModel by viewModel<DeveloperViewModel<Any?>>()
    private lateinit var layoutManager: RecyclerView.LayoutManager

    var con: Context? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        con = context
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeveloperBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getReviewAPI()
        getDevelopersApi()
        setUpObserver()
    }

    private fun getReviewAPI() {
        var getReviewsRequest = GetReviewsRequest(
            "1"
        )
        developerViewModel.getReviewsApi(getReviewsRequest, get_reviews_api)
    }

    private fun setUpObserver() {
        developerViewModel.getReviews.observe(viewLifecycleOwner) {
            requireContext().toast(Gson().toJson(it))
            var list = mutableListOf<Data?>()
            list = it.data ?: mutableListOf()
            //setAdapter(list)
        }
        developerViewModel.getDevelopers.observe(viewLifecycleOwner) {
            try {
                var list = mutableListOf<com.antino.avengers.data.pojo.getDevelopersApi.Data?>()
                list = it.data?.toMutableList() ?: mutableListOf()
                setAdapter(list)
            } catch(e: Exception) {
                requireActivity().toast("Something Went Wrong")
            }
        }
    }

    private fun setAdapter(list: MutableList<com.antino.avengers.data.pojo.getDevelopersApi.Data?>) {
        layoutManager = GridLayoutManager(requireContext(), 2)
        binding.developerRv.layoutManager = layoutManager
        val developerAdapter =
            DeveloperAdapter(list, requireContext())
        binding.developerRv.adapter = developerAdapter
    }

    private fun getDevelopersApi() {
        var getDevelopersRequest = GetDevelopersRequest("1")
        developerViewModel.getDevelopersApi(getDevelopersRequest, get_developers_api)
    }

}