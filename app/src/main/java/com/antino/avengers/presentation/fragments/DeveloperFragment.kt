package com.antino.avengers.presentation.fragments

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.antino.avengers.Others.PreferenceUtils
import com.antino.avengers.R
import com.antino.avengers.Utils.common.*
import com.antino.avengers.Utils.gone
import com.antino.avengers.Utils.toast
import com.antino.avengers.Utils.visible
import com.antino.avengers.data.pojo.AskForReview.Request.AskForReviewRequest
import com.antino.avengers.data.pojo.getDevelopersApi.GetDevelopersRequest
import com.antino.avengers.databinding.FragmentDeveloperBinding
import com.antino.avengers.presentation.Adapter.DeveloperAdapter
import com.antino.avengers.presentation.Adapter.ProjectManagerAdapter
import com.antino.avengers.presentation.ViewModel.DeveloperViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeveloperFragment : Fragment() {
    private lateinit var binding: FragmentDeveloperBinding
//    private lateinit var manager: ProjectManagerAdapter
    private val developerViewModel by viewModel<DeveloperViewModel<Any?>>()
    private lateinit var layoutManager: RecyclerView.LayoutManager
    var id = ""
    var con: Context? = null
    private var accessToken = ""
    override fun onAttach(context: Context) {
        super.onAttach(context)
        con = context
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeveloperBinding.inflate(inflater, container, false)
        accessToken = "Bearer ${PreferenceUtils.getString(PREF_USER_TOKEN)}"
        binding.swipping.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            setUpObserver()
            binding.swipping.setRefreshing(false)
        })
        setUpObserver()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //getReviewAPI()
        this.arguments?.let {
            id = it.getString("pass_id") ?: ""
            getDevelopersApi(requireContext(),it.getString("pass_id") ?: "")
            Log.d("devdetail:", "${it.getString("ProjectName")}")

            binding.projectName.text= it.getString("ProjectName")
            binding.startDate.text=it.getString("ProjectDate")
        }


    }


    private fun setUpObserver() {
        developerViewModel.getDevelopers.observe(viewLifecycleOwner) {
            try {
                binding.fab.visible()
                binding.shimmerViewContainerheader.root.visibility=View.GONE
                var list = mutableListOf<com.antino.avengers.data.pojo.getDevelopersApi.Data?>()
                list = it.data?.toMutableList() ?: mutableListOf()
                val data= it
                setAdapter(list)

                binding.fab.setOnClickListener {

                    val dialog = BottomSheetDialog(requireContext())
                    val view = layoutInflater.inflate(com.antino.avengers.R.layout.mail_bottom_sheet, null)
                    val text_subject = view.findViewById<EditText>(com.antino.avengers.R.id.text_subject)
                    val text_content = view.findViewById<EditText>(com.antino.avengers.R.id.text_content)
                    val submit = view.findViewById<Button>(com.antino.avengers.R.id.submit)
                    val custom = view.findViewById<Button>(com.antino.avengers.R.id.custom)
                    val review = view.findViewById<Button>(com.antino.avengers.R.id.review)
                    val lastReviewText = view.findViewById<TextView>(com.antino.avengers.R.id.last_review)
                    custom.backgroundTintList =
                        ColorStateList.valueOf(Color.parseColor("#00adb5"))
                    review.backgroundTintList= ColorStateList.valueOf(Color.parseColor("#7088E1"))
                    text_subject.setText("Request for Developer(s) review")
                    text_content.setText("Hi,\n We hope you are doing good. This E-mail is to request general feedback of the developer(s) working on your project. Click on the given link for feedback form")
                    if (!data.latestRequest.isNullOrEmpty()){
                        lastReviewText.visible()

                        if (data.latestRequest.contains("T")){
                            val text= data.latestRequest.split("T")
                            lastReviewText.text= "Last Review Request: ${text[0]}"
                        }else{
                            lastReviewText.text= data.latestRequest
                        }
                    }else{
                        lastReviewText.gone()
                    }
                    submit.setOnClickListener {
                        callAskForReviewApi(accessToken,text_content.text.toString(), text_subject.text.toString())
                        requireContext().toast("Email Sent")
                        dialog.dismiss()
                    }
                    custom.setOnClickListener{
                        custom.backgroundTintList =
                            ColorStateList.valueOf(Color.parseColor("#7088E1"))
                        review.backgroundTintList= ColorStateList.valueOf(Color.parseColor("#00adb5"))
                        text_subject.setText("")
                        text_content.setText("")

                    }
                    review.setOnClickListener{
                        custom.backgroundTintList =
                            ColorStateList.valueOf(Color.parseColor("#00adb5"))
                        review.backgroundTintList= ColorStateList.valueOf(Color.parseColor("#7088E1"))
                        text_subject.setText("Request for Developer(s) review")
                        text_content.setText("Hi,\n We hope you are doing good. This E-mail is to request general feedback of the developer(s) working on your project. Click on the given link for feedback form")

                    }
                    dialog.setCancelable(true)
                    dialog.setContentView(view)

                    dialog.show()
                }

            } catch(e: Exception) {
                requireActivity().toast("Something Went Wrong")
            }
        }

        developerViewModel.askForReview.observe(viewLifecycleOwner) {
            try {
                if(it.status == 200) {
                    requireActivity().toast(it.data)
                }
                /*else {
                    requireActivity().toast(it.data)
                }*/
            } catch (e: Exception){
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

        developerAdapter.setOnItemClickListener {
            val bundle = Bundle()
            bundle.putString("pass_id", list[it]?.id!!.toString())
            Log.d("devdetail:", "${list[it]}")
            PreferenceUtils.putDeveloperDetails(list[it]!!)
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_developer_to_review, bundle)
        }
    }

    private fun getDevelopersApi(requireContext: Context, toString: String) {
        var getDevelopersRequest = GetDevelopersRequest(toString)
        developerViewModel.getDevelopersApi(accessToken,getDevelopersRequest, get_developers_api)
    }
    interface DevelopertoActivity {

        fun clicked(data: Int)

    }

    private fun callAskForReviewApi(token:String,content:String, subject: String) {
        val request = AskForReviewRequest(
            content = content,
            subject = subject,
            clientId = id,
            clientEmail = null
        )
        developerViewModel.askForReview(token,request, ask_for_review)
    }

}