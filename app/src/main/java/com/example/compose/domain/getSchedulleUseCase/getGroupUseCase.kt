package com.example.compose.domain.getSchedulleUseCase

import com.example.compose.commmon.Resourse
import com.example.compose.data.repository.RepositoryIMPL
import com.example.compose.domain.model.GroupModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class getGroupUseCase {
    private val repository = RepositoryIMPL()
    operator fun invoke(): Flow<Resourse<List<GroupModel>>> = flow{
        try {
            emit(Resourse.Loading())
            val sched = repository.getGroups()
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