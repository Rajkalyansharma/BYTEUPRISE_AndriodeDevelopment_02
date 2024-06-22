package com.example.to_do_list

import java.time.Instant
import java.util.Date

object ToDoManager {
    private val todoList = mutableListOf<ToDo>()

    fun getAllToDo() : List<ToDo>{
        return todoList
    }
    fun addToDo(Title : String){
        todoList.add(ToDo(System.currentTimeMillis().toInt(),Title, Date.from(Instant.now())))
    }
    fun deleteToDo(id : Int){
        todoList.removeIf { it.id == id }
    }
}