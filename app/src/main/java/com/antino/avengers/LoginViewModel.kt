package com.antino.avengers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antino.avengers.Utils.LoadingState
import com.antino.avengers.Utils.common.getRetrofitErrorMsg
import com.antino.avengers.Utils.common.sign_in_api
import com.antino.avengers.data.pojo.loginApi.request.LoginRequest
import com.antino.avengers.data.pojo.loginApi.response.LoginResponse

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel<T : Any?>(private val loginRepository: LoginRepository) : ViewModel(),
    Callback<T> {

    companion object {
        val TAG: String = LoginViewModel::class.java.simpleName
    }
    private val _dataAddUser = MutableLiveData<LoginResponse>()
    val dataAddUser: LiveData<LoginResponse>
    get() = _dataAddUser

    private var apiType: String? = null

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState> get() = _loadingState

    fun loginApi(loginRequest: LoginRequest, apiType: String) {
        this.apiType = apiType
        _loadingState.postValue(LoadingState.LOADING)

        viewModelScope.launch(Dispatchers.IO) {
            _dataAddUser.postValue(
                loginRepository.getLoginDetails(loginRequest).body()
            )
        }
    }


    override fun onFailure(call: Call<T>, t: Throwable) {
        _loadingState.postValue(LoadingState.error(t.message))
    }

    override fun onResponse(
        call: Call<T>,
        response: Response<T>
    ) {
        try {
            if (response.isSuccessful) {
                when (apiType) {
                    sign_in_api -> _dataAddUser.postValue(response.body() as LoginResponse)
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

    /* fun getNextCalenderDates(date: String, travelDate: String) {
         var dateObjArrayList = mutableListOf<StageData>()
         val calendar: Calendar = Calendar.getInstance()
         val arrayOfData = date.split("-").map { it.toInt() }
         calendar.set(arrayOfData[2], arrayOfData[1].minus(1), arrayOfData[0])
         var i = 0
         val days = 7
         val formatter = SimpleDateFormat(DATE_FORMAT_DD_MMM, Locale.ENGLISH)
         while (i < days) {
             i++
             dateObjArrayList.add(StageData(formatter.format(calendar.time), true, false, "DATES"))
             calendar.add(Calendar.DAY_OF_MONTH, 1)
         }


         if (dateObjArrayList.any { it.title == inputFormatToOutput(travelDate,
                 DATE_FORMAT_D_M_Y, DATE_FORMAT_DD_MMM
             ) }) {
             val selectedDate = StageData("${inputFormatToOutput(travelDate,
                 DATE_FORMAT_D_M_Y, DATE_FORMAT_DD_MMM
             )}", true, true, "DATES")

             dateObjArrayList.forEachIndexed { index, stageData ->
                 if(selectedDate.title == stageData.title)
                 {
                     dateObjArrayList[index] = selectedDate
                 }
             }
         }
         *//*else {
            val selectedDate = StageData(dateObjArrayList[0].title, true, true, "DATES")
            dateObjArrayList[0] = selectedDate
        }*//*
        dates.postValue(dateObjArrayList)
    }*/
}