package com.example.compose.presentation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose.commmon.Resourse
import com.example.compose.domain.getSchedulleUseCase.getScheduleUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class MainViewModel : ViewModel(){

    private val _state = mutableStateOf(ScheduleState())
    private val getPrepodsUseCase by lazy { getScheduleUseCase()};
    val state: State<ScheduleState> = _state

    fun getPrepods(grNum:String){
        getPrepodsUseCase(grNum).onEach { result->
            when(result){
                is Resourse.Success->{
                    _state.value = ScheduleState(Days = result.data)
                    Log.d("EEEEEEEEEEEEEEEEEEEEE",result.data!!.TuesdayList[2].subject!!)
                }
                is Resourse.Error ->{
                    _state.value = ScheduleState(error = result.message?:"Zalupa")
                }
                is Resourse.Loading -> {
                    _state.value = ScheduleState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }
}