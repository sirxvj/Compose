package com.example.compose.domain.use_case

import com.example.compose.commmon.Resourse
import com.example.compose.data.repository.RepositoryIMPL
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class getCurrentWeekUseCase {
    private val repository = RepositoryIMPL()
    operator fun invoke(): Flow<Resourse<Int>> = flow{
        try {
            emit(Resourse.Loading())
            val sched = repository.getCurrentWeek()
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