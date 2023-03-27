package com.example.compose.presentation

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.compose.commmon.Resourse
import com.example.compose.commmon.constants.ADDEDGROUPS
import com.example.compose.data.ScheduleDatabase
import com.example.compose.domain.use_case.getCurrentWeekUseCase
import com.example.compose.domain.use_case.getGroupUseCase
import com.example.compose.domain.use_case.getScheduleUseCase
import com.example.compose.presentation.list.states.CurrentWeekState
import com.example.compose.presentation.list.states.GroupState
import com.example.compose.presentation.list.states.ScheduleState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class MainViewModel : ViewModel(){


    private val _state = mutableStateOf(ScheduleState())
    private val _wState = mutableStateOf(CurrentWeekState())
    private val _grState = mutableStateOf(GroupState())

    private val getScheaduleUseCase by lazy { getScheduleUseCase()};
    private val getCurrentWeekUseCase by lazy { getCurrentWeekUseCase()};
    private val GetGroupUseCase by lazy { getGroupUseCase() }

    @SuppressLint("StaticFieldLeak")
    lateinit var context : Context
    val headertext = mutableStateOf("None")
    val state: State<ScheduleState> = _state
    val weekState : State<CurrentWeekState>  = _wState
    val groupState : State<GroupState>  = _grState

    fun addGroups(group : String){
        val prefs = context.getSharedPreferences(ADDEDGROUPS,Context.MODE_PRIVATE)
        val list = prefs.getStringSet(ADDEDGROUPS, emptySet())?.toMutableSet()
        list?.add(group)
        prefs.edit().putStringSet(ADDEDGROUPS,list).apply()
    }
    fun getGroups() : List<String>{
        val prefs = context.getSharedPreferences(ADDEDGROUPS,Context.MODE_PRIVATE)
        val list = prefs.getStringSet(ADDEDGROUPS, emptySet())
       if(list!=null) return list.toList().sorted()
        else return emptyList()
    }

    fun getGroup(){
        GetGroupUseCase().onEach { result->
            when(result){
                is Resourse.Success->{
                    _grState.value = GroupState(Groups = result.data?: emptyList())
                }
                is Resourse.Error ->{
                    _grState.value = GroupState(error = result.message?:"Zalupa")
                }
                is Resourse.Loading -> {
                    _grState.value = GroupState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }
    fun getScheadule(grNum:String){
        val db = Room.databaseBuilder(
            context,
            ScheduleDatabase::class.java, "schedule-database"
        ).build()
        val schedDao = db.scheduleDao()
        getScheaduleUseCase(grNum).onEach { result->
            when(result){
                is Resourse.Success->{
                    _state.value = ScheduleState(Days = result.data)
                        //    _state.value.Days?.let { schedDao.insertAll(it)}

                    val list = schedDao.getAll()
                }
                is Resourse.Error ->{
                    _state.value = ScheduleState(error = result.message?:"Govno")
                    _state.value = ScheduleState(Days = schedDao.findByNumb(grNum))
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
                    _wState.value = CurrentWeekState(week = result.data)
                }
                is Resourse.Error ->{
                    _wState.value = CurrentWeekState(error = result.message?:"Penis")
                }
                is Resourse.Loading -> {
                    _wState.value = CurrentWeekState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }

}