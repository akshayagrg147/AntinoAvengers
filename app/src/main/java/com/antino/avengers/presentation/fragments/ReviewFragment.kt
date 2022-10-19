package com.antino.avengers.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.antino.avengers.Others.PreferenceUtils
import com.antino.avengers.R
import com.antino.avengers.Utils.common.PREF_USER_TOKEN
import com.antino.avengers.Utils.common.get_reviews_api
import com.antino.avengers.Utils.gone
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
    private var accessToken = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReviewBinding.inflate(inflater, container, false)
        accessToken = "Bearer ${PreferenceUtils.getString(PREF_USER_TOKEN)}"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.swipping.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            getReviewAPI()
            setUpObserver()
            binding.swipping.setRefreshing(false)
        })
        getReviewAPI()
        setUpObserver()
    }

    private fun getReviewAPI() {
        val bundle = arguments
        var getReviewsRequest = GetReviewsRequest(
            bundle!!.getString("pass_id").toString()
        )
        Log.d("reviewsResponse", getReviewsRequest.toString())


        developerViewModel.getReviewsApi(accessToken,getReviewsRequest, get_reviews_api)
    }


    private fun setUpObserver() {
         developerViewModel.getReviews.observe(viewLifecycleOwner) {
             Log.d("reviewsResponse", it.toString())
             binding.shimmerViewContainerheader.root.visibility=View.GONE
             if (it!= null){
                 if (!it.data.isNullOrEmpty()){
                     var list = mutableListOf<Data?>()
                     list = it.data ?: mutableListOf()
                     val data= PreferenceUtils.getDeveloperDetails()
                     binding.developerName.text= data!!.name
                     binding.developerEmail.text= data!!.email
                     binding.developerMobile.text= data!!.phone
                     binding.yearExp.text= "Exp : ${data!!.experience}"

//                     binding.developerName.text= it.data
                     setAdapter(list)
                 }else{
                     requireContext().toast("no reviews found")
                 }
             }else{
                 requireContext().toast("Something went wrong")
             }
         }
    }
    private fun setAdapter(list: MutableList<com.antino.avengers.data.pojo.getReviewsApi.response.Data?>) {
        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(context,1)
        binding.developerRv.layoutManager = mLayoutManager
        val reviewAdapter =
            ReviewAdapter(list, requireContext())
        binding.developerRv.adapter = reviewAdapter
    }
}