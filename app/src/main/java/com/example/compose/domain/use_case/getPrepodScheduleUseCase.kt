package com.example.compose.domain.use_case

import com.example.compose.commmon.Resourse
import com.example.compose.data.repository.RepositoryIMPL
import com.example.compose.domain.model.LessonModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class getPrepodScheduleUseCase {
    private val repository = RepositoryIMPL()
    operator fun invoke(urlid:String): Flow<Resourse<List<LessonModel>>> = flow{
        try {
            emit(Resourse.Loading())
            val sched = repository.getPrepSchedule(urlid)
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