package com.example.compose.presentation

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose.commmon.Resourse
import com.example.compose.commmon.constants.ADDEDGROUPS
import com.example.compose.domain.getSchedulleUseCase.getCurrentWeekUseCase
import com.example.compose.domain.getSchedulleUseCase.getGroupUseCase
import com.example.compose.domain.getSchedulleUseCase.getScheduleUseCase
import com.example.compose.presentation.list.states.CurrentWeekState
import com.example.compose.presentation.list.states.GroupState
import com.example.compose.presentation.list.states.ScheduleState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class MainViewModel : ViewModel(){


    private val _state = mutableStateOf(ScheduleState())
    private val w_state = mutableStateOf(CurrentWeekState())
    private val gr_state = mutableStateOf(GroupState())

    private val getScheaduleUseCase by lazy { getScheduleUseCase()};
    private val getCurrentWeekUseCase by lazy { getCurrentWeekUseCase()};
    private val GetGroupUseCase by lazy { getGroupUseCase() }

    lateinit var context : Context
    val state: State<ScheduleState> = _state
    val weekState : State<CurrentWeekState>  = w_state
    val groupState : State<GroupState>  = gr_state

    fun AddGroup(group : String){
        val prefs = context.getSharedPreferences(ADDEDGROUPS,Context.MODE_PRIVATE)
        val list = prefs.getStringSet(ADDEDGROUPS, emptySet())?.toMutableSet()
        list?.add(group)
        prefs.edit().putStringSet(ADDEDGROUPS,list).apply()
    }
    fun GetGroups() : List<String>{
        val prefs = context.getSharedPreferences(ADDEDGROUPS,Context.MODE_PRIVATE)
        val list = prefs.getStringSet(ADDEDGROUPS, emptySet())
       if(list!=null) return list.toList()
        else return emptyList()
    }

    fun getGroup(){
        GetGroupUseCase().onEach { result->
            when(result){
                is Resourse.Success->{
                    gr_state.value = GroupState(Groups = result.data?: emptyList())
                }
                is Resourse.Error ->{
                    gr_state.value = GroupState(error = result.message?:"Zalupa")
                }
                is Resourse.Loading -> {
                    gr_state.value = GroupState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }
    fun getScheadule(grNum:String){
        getScheaduleUseCase(grNum).onEach { result->
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