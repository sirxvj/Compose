package com.example.compose.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.compose.commmon.Resourse
import com.example.compose.commmon.constants.ADDEDGROUPS
import com.example.compose.commmon.constants.ADDEDPREPS
import com.example.compose.commmon.constants.CURRENTWEEK
import com.example.compose.data.db.database
import com.example.compose.domain.model.LessonModel
import com.example.compose.domain.model.PrepodModel
import com.example.compose.domain.use_case.*
import com.example.compose.presentation.states.CurrentWeekState
import com.example.compose.presentation.states.GroupState
import com.example.compose.presentation.states.PrepodsState
import com.example.compose.presentation.states.ScheduleState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class MainViewModel : ViewModel() {


    private val _state = mutableStateOf(ScheduleState())
    private val _wState = mutableStateOf(CurrentWeekState())
    private val _grState = mutableStateOf(GroupState())
    private val _prState = mutableStateOf(PrepodsState())

    private val getScheaduleUseCase by lazy { getScheduleUseCase() };
    private val getCurrentWeekUseCase by lazy { getCurrentWeekUseCase() };
    private val GetPrepodScheduleUseCase by lazy{ getPrepodScheduleUseCase() }
    private val GetGroupUseCase by lazy { getGroupUseCase() }
    private val GetPrepodUseCase by lazy{getPrepodUseCase()}

    @SuppressLint("StaticFieldLeak")
    lateinit var context: Context
    val headertext = mutableStateOf("None")
    val state: State<ScheduleState> = _state
    val weekState: State<CurrentWeekState> = _wState
    val groupState: State<GroupState> = _grState
    val prepState:State<PrepodsState> = _prState


    fun getURLIDbyPrepodfio(fio:String):String{
        val prefs = context.getSharedPreferences(fio, Context.MODE_PRIVATE)
        val urlid = prefs.getString(fio,"")
        return urlid?: ""
    }
    fun addPrepods(prepod: PrepodModel) {
        val prefs = context.getSharedPreferences(ADDEDPREPS, Context.MODE_PRIVATE)
        val list = prefs.getStringSet(ADDEDPREPS, emptySet())?.toMutableSet()
        list?.add(prepod.fio)
        prefs.edit().putStringSet(ADDEDPREPS, list).apply()
        prefs.edit().putString(prepod.fio,prepod.urlId).apply()
    }
    fun getPrepods(): List<String> {
        val prefs = context.getSharedPreferences(ADDEDPREPS, Context.MODE_PRIVATE)
        val list = prefs.getStringSet(ADDEDPREPS, emptySet())
        return list?.toList()?.sorted() ?: emptyList()
    }
    fun addGroups(group: String) {
        val prefs = context.getSharedPreferences(ADDEDGROUPS, Context.MODE_PRIVATE)
        val list = prefs.getStringSet(ADDEDGROUPS, emptySet())?.toMutableSet()
        list?.add(group)
        prefs.edit().putStringSet(ADDEDGROUPS, list).apply()
    }

    fun getGroups(): List<String> {
        val prefs = context.getSharedPreferences(ADDEDGROUPS, Context.MODE_PRIVATE)
        val list = prefs.getStringSet(ADDEDGROUPS, emptySet())
        if (list != null) return list.toList().sorted()
        else return emptyList()
    }
    fun getPrepod() {
        GetPrepodUseCase().onEach { result ->
            when (result) {
                is Resourse.Success -> {
                    _prState.value = PrepodsState(preps = result.data ?: emptyList())
                }
                is Resourse.Error -> {
                    _prState.value = PrepodsState(error = result.message ?: "Zalupa")
                }
                is Resourse.Loading -> {
                    _prState.value = PrepodsState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }
    fun getGroup() {
        GetGroupUseCase().onEach { result ->
            when (result) {
                is Resourse.Success -> {
                    _grState.value = GroupState(Groups = result.data ?: emptyList())
                }
                is Resourse.Error -> {
                    _grState.value = GroupState(error = result.message ?: "Zalupa")
                }
                is Resourse.Loading -> {
                    _grState.value = GroupState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }

    fun getScheadule(grNum: String) {
        val db = Room.databaseBuilder(
            context,
            database::class.java, "database-name"
        ).allowMainThreadQueries().build()
        getScheaduleUseCase(grNum).onEach { result ->
            when (result) {
                is Resourse.Success -> {
                    _state.value = ScheduleState(Days = result.data)
                    val scheduleDao = db.scheduleDao()
                    if (scheduleDao.getAll(grNum).isEmpty()) {
                        for (n in _state.value.Days ?: emptyList()) {
                            scheduleDao.insertAll(n)
                        }
                    }
                }
                is Resourse.Error -> {
                    val scheduleDao = db.scheduleDao()
                    val schedules: List<LessonModel> = scheduleDao.getAll(grNum)
                    if (schedules.isNotEmpty())
                        _state.value = ScheduleState(Days = schedules)
                    else
                        _state.value = ScheduleState(error = result.message ?: "Govno")
                    //_state.value = ScheduleState(Days = schedDao.findByNumb(grNum))
                }
                is Resourse.Loading -> {
                    _state.value = ScheduleState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }

    fun getPrepodScheadule(urlid: String) {
        val db = Room.databaseBuilder(
            context,
            database::class.java, "database-name"
        ).allowMainThreadQueries().build()
        GetPrepodScheduleUseCase(urlid).onEach { result ->
            when (result) {
                is Resourse.Success -> {
                    _state.value = ScheduleState(Days = result.data)
                    val scheduleDao = db.scheduleDao()
                    if (scheduleDao.getAll(urlid).isEmpty()) {
                        for (n in _state.value.Days ?: emptyList()) {
                            scheduleDao.insertAll(n)
                        }
                    }
                }
                is Resourse.Error -> {
                    val scheduleDao = db.scheduleDao()
                    val schedules: List<LessonModel> = scheduleDao.getAll(urlid)
                    if (schedules.isNotEmpty())
                        _state.value = ScheduleState(Days = schedules)
                    else
                        _state.value = ScheduleState(error = result.message ?: "Govno")
                    //_state.value = ScheduleState(Days = schedDao.findByNumb(grNum))
                }
                is Resourse.Loading -> {
                    _state.value = ScheduleState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }

    fun getCurrentWeek() {
        val prefs = context.getSharedPreferences(CURRENTWEEK, Context.MODE_PRIVATE)
        getCurrentWeekUseCase().onEach { result ->
            when (result) {
                is Resourse.Success -> {
                    _wState.value = CurrentWeekState(week = result.data)
                    prefs.edit().putInt(CURRENTWEEK, _wState.value.week ?: 0).apply()
                }
                is Resourse.Error -> {
                    val wek = prefs.getInt(CURRENTWEEK, Context.MODE_PRIVATE)
                    if (wek == 0)
                        _wState.value = CurrentWeekState(error = result.message ?: "Penis")
                    else
                        _wState.value = CurrentWeekState(week = wek)
                }
                is Resourse.Loading -> {
                    _wState.value = CurrentWeekState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }

}