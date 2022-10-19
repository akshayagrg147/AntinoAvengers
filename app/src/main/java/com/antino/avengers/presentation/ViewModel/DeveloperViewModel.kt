package com.antino.avengers.presentation.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antino.avengers.LoginRepository
import com.antino.avengers.Utils.LoadingState
import com.antino.avengers.Utils.common.*
import com.antino.avengers.data.pojo.AskForReview.Request.AskForReviewRequest
import com.antino.avengers.data.pojo.AskForReview.Response.AskForReviewResponse
import com.antino.avengers.data.pojo.getAllProjects.GetAllProjectsResponse
import com.antino.avengers.data.pojo.getDevelopersApi.GetDevelopersRequest
import com.antino.avengers.data.pojo.getDevelopersApi.GetDevelopersResponse
import com.antino.avengers.data.pojo.getReviewsApi.GetReviewsRequest
import com.antino.avengers.data.pojo.getReviewsApi.response.GetReviewsResponse
import com.antino.avengers.data.pojo.loginApi.request.LoginRequest
import com.antino.avengers.data.pojo.loginApi.response.LoginResponse
import com.antino.avengers.presentation.Repository.DeveloperRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeveloperViewModel<T : Any?>(private val developerRepository: DeveloperRepository) : ViewModel(),
    Callback<T> {

    private val _getReviews = MutableLiveData<GetReviewsResponse>()
    val getReviews: LiveData<GetReviewsResponse>
        get() = _getReviews

    private val _getDevelopers = MutableLiveData<GetDevelopersResponse>()
    val getDevelopers: LiveData<GetDevelopersResponse>
        get() = _getDevelopers

    private val _getAllProjects = MutableLiveData<GetAllProjectsResponse>()
    val getAllProjects: LiveData<GetAllProjectsResponse>
        get() = _getAllProjects

    private val _askForReview = MutableLiveData<AskForReviewResponse>()
    val askForReview: LiveData<AskForReviewResponse>
        get() = _askForReview


    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState> get() = _loadingState

    private var apiType: String? = null

    override fun onResponse(
        call: Call<T>,
        response: Response<T>
    ) {
        try {
            if (response.isSuccessful) {
                when (apiType) {
                    get_reviews_api -> _getReviews.postValue(response.body() as GetReviewsResponse)
                    get_developers_api -> _getDevelopers.postValue(response.body() as GetDevelopersResponse)
                    get_all_projects -> _getAllProjects.postValue(response.body() as GetAllProjectsResponse)
                    ask_for_review -> _askForReview.postValue(response.body() as AskForReviewResponse)
                }

                _loadingState.postValue(LoadingState.LOADED)
            } else {
                val message = getRetrofitErrorMsg(response.errorBody())
                _loadingState.postValue(LoadingState.error(message))
            }
        } catch (e: Exception) {
            _loadingState.postValue(LoadingState.error(e.message))
        }
    }


    override fun onFailure(call: Call<T>, t: Throwable) {
        _loadingState.postValue(LoadingState.error(t.message))
    }

    fun getReviewsApi(token:String,getReviewsRequest: GetReviewsRequest, apiType: String) {
        this.apiType = apiType
        _loadingState.postValue(LoadingState.LOADING)

        viewModelScope.launch(Dispatchers.IO) {
            _getReviews.postValue(
                developerRepository.
                getReview(token,getReviewsRequest).body()
            )
        }
    }

    fun getDevelopersApi(token: String,getDevelopersRequest: GetDevelopersRequest,apiType: String) {
        this.apiType = apiType
        _loadingState.postValue(LoadingState.LOADING)

        viewModelScope.launch(Dispatchers.IO) {
            _getDevelopers.postValue(
                developerRepository.getDevelopers(token,getDevelopersRequest).body()
            )
        }
    }

    fun getAllProjects(token:String,apiType: String) {
        this.apiType = apiType
        _loadingState.postValue(LoadingState.LOADING)

        viewModelScope.launch(Dispatchers.IO) {
            _getAllProjects.postValue(
                developerRepository.getAllProjects(token).body()
            )
        }
    }

    fun askForReview(token: String,askForReviewRequest: AskForReviewRequest, apiType: String) {
        this.apiType = apiType
        _loadingState.postValue(LoadingState.LOADING)

        viewModelScope.launch(Dispatchers.IO) {
            _askForReview.postValue(
                developerRepository.askForReview(token,askForReviewRequest).body()
            )
        }
    }

}