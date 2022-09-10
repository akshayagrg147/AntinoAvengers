package com.antino.avengers.presentation.fragments

import android.Manifest
import android.R
import android.content.Context
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.antino.avengers.Others.Utils

import com.antino.avengers.data.pojo.getReviewsApi.GetReviewsRequest
import com.antino.avengers.data.pojo.getReviewsApi.response.Data
import com.antino.avengers.databinding.FragmentDeveloperBinding
import com.antino.avengers.presentation.Adapter.DeveloperAdapter
import com.antino.avengers.presentation.Adapter.ProjectManagerAdapter
import com.antino.avengers.presentation.ViewModel.DeveloperViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel


class DeveloperFragment : Fragment() {
    private lateinit var binding: FragmentDeveloperBinding
    private lateinit var manager: DeveloperAdapter
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

/*        binding.lifecycleOwner = requireActivity()
        binding.executePendingBindings()*/
        // Inflate the layout for this fragment


        binding.fab.setOnClickListener {

            val dialog = BottomSheetDialog(requireContext())
            val view = layoutInflater.inflate(com.antino.avengers.R.layout.mail_bottom_sheet, null)
            val text_subject = view.findViewById<EditText>(com.antino.avengers.R.id.text_subject)
            val text_content = view.findViewById<EditText>(com.antino.avengers.R.id.text_content)
            val submit = view.findViewById<Button>(com.antino.avengers.R.id.submit)
            val custom = view.findViewById<Button>(com.antino.avengers.R.id.custom)
            val review = view.findViewById<Button>(com.antino.avengers.R.id.review)
            custom.backgroundTintList =
                ColorStateList.valueOf(Color.parseColor("#00adb5"))
            review.backgroundTintList= ColorStateList.valueOf(Color.parseColor("#7088E1"))
            text_subject.setText("Request for Developer(s) review")
            text_content.setText("Hi,\n We hope you are doing good. This E-mail is to request general feedback of the developer(s) working on your project. Click on the given link for feedback form")

            submit.setOnClickListener {
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
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        Utils.setUpRecyclerOneItemLayoutStaggered(con!!, binding.developerRv)
//        //manager = ProjectManagerAdapter(list, con!!)
//        binding.developerRv.adapter = manager
//        manager.setOnItemClickListener {
//            Log.d("testing", "onViewCreated: ${it}")
//        }
//        getReviewAPI()
//        setUpObserver()
//    }
//
//    private fun getReviewAPI() {
//        var getReviewsRequest = GetReviewsRequest(
//            "1"
//        )
//        developerViewModel.getReviewsApi(getReviewsRequest,"")
//    }
//
//    private fun setUpObserver() {
//        developerViewModel.getReviews.observe(viewLifecycleOwner) {
//            requireContext().toast(Gson().toJson(it))
//            var list = mutableListOf<Data?>()
//            list = it.data ?: mutableListOf()
//            setAdapter(list)
//        }
//    }
//
//    private fun setAdapter(list: MutableList<Data?>) {
//        layoutManager = GridLayoutManager(requireContext(), 2)
//        binding.developerRv.layoutManager = layoutManager
//        val developerAdapter =
//            DeveloperAdapter(list, requireContext())
//        binding.developerRv.adapter = developerAdapter
//    }
}