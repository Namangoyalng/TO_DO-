package com.example.to_doapp

data class Todo (
    val content : String,
    var checked_status : Boolean = false
)