package com.example.compose.presentation.list.states

import com.example.compose.domain.model.GroupModel

data class GroupState(
    val isLoading: Boolean = false,
    var Groups : List<GroupModel> = emptyList(),
    val error:String = ""
)