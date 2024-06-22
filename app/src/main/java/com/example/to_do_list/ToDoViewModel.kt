package com.example.to_do_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class ToDoViewModel : ViewModel() {
    private var _toDoList = MutableLiveData<List<ToDo>>()
    val toDoList : LiveData<List<ToDo>> = _toDoList


    fun getAllToDo(){
        _toDoList.value = ToDoManager.getAllToDo().reversed()
    }
    fun addToDo(Title : String){
        ToDoManager.addToDo(Title)
        getAllToDo()
    }
    fun deleteToDo(id : Int){
        ToDoManager.deleteToDo(id)
        getAllToDo()
    }
}