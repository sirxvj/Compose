package com.example.compose.presentation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose.commmon.Resourse
import com.example.compose.domain.getSchedulleUseCase.getCurrentWeekUseCase
import com.example.compose.domain.getSchedulleUseCase.getScheduleUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class MainViewModel : ViewModel(){

    private val _state = mutableStateOf(ScheduleState())
    private val w_state = mutableStateOf(CurrentWeekState())
    private val getPrepodsUseCase by lazy { getScheduleUseCase()};
    private val getCurrentWeekUseCase by lazy { getCurrentWeekUseCase()};
    val state: State<ScheduleState> = _state
    val weekState : State<CurrentWeekState>  = w_state

    fun getPrepods(grNum:String){
        getPrepodsUseCase(grNum).onEach { result->
            when(result){
                is Resourse.Success->{
                    _state.value = ScheduleState(Days = result.data)
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
    fun getCurrentWeek(){
        getCurrentWeekUseCase().onEach { result->
            when(result){
                is Resourse.Success->{
                    w_state.value = CurrentWeekState(week = result.data)
                }
                is Resourse.Error ->{
                    w_state.value = CurrentWeekState(error = result.message?:"Penis")
                }
                is Resourse.Loading -> {
                    w_state.value = CurrentWeekState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }

}