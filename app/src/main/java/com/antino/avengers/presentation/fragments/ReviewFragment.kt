package com.antino.avengers.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.antino.avengers.R
import com.antino.avengers.Utils.common.get_reviews_api
import com.antino.avengers.Utils.toast
import com.antino.avengers.data.pojo.getReviewsApi.GetReviewsRequest
import com.antino.avengers.data.pojo.getReviewsApi.response.Data
import com.antino.avengers.databinding.FragmentDeveloperBinding
import com.antino.avengers.databinding.FragmentReviewBinding
import com.antino.avengers.presentation.Adapter.DeveloperAdapter
import com.antino.avengers.presentation.Adapter.ReviewAdapter
import com.antino.avengers.presentation.ViewModel.DeveloperViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ReviewFragment : Fragment() {

    private lateinit var binding: FragmentReviewBinding
    private val developerViewModel by viewModel<DeveloperViewModel<Any?>>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getReviewAPI()
        setUpObserver()
    }

    private fun getReviewAPI() {
        val bundle = arguments
        var getReviewsRequest = GetReviewsRequest(
            bundle!!.getString("Idpassed").toString()
        )

        developerViewModel.getReviewsApi(getReviewsRequest, get_reviews_api)
    }


    private fun setUpObserver() {
         developerViewModel.getReviews.observe(viewLifecycleOwner) {

             var list = mutableListOf<Data?>()
             list = it.data ?: mutableListOf()
             setAdapter(list)
         }
    }


    private fun setAdapter(list: MutableList<com.antino.avengers.data.pojo.getReviewsApi.response.Data?>) {
        var layoutManager = GridLayoutManager(requireContext(), 2)
        binding.developerRv.layoutManager = layoutManager
        val reviewAdapter =
            ReviewAdapter(list, requireContext())
        binding.developerRv.adapter = reviewAdapter
    }
}