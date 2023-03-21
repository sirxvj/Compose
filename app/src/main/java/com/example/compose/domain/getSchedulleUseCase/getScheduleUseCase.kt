package com.example.compose.domain.getSchedulleUseCase

import android.content.Context
import com.example.compose.commmon.Resourse
import com.example.compose.data.repository.RepositoryIMPL
import com.example.compose.domain.model.ScheduleModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class getScheduleUseCase {
    private val repository = RepositoryIMPL()
    operator fun invoke(groupNum:String): Flow<Resourse<ScheduleModel>> = flow{
        try {
            emit(Resourse.Loading())
            val sched = repository.getSchedule(groupNum)
            emit(Resourse.Success(sched))
        }
        catch (e: HttpException){
            emit(Resourse.Error(e.localizedMessage?:"Zalupa"))
        }
        catch (e: IOException){
            emit(Resourse.Error(e.localizedMessage?:"Cringe no internet conection"))
        }
    }
}