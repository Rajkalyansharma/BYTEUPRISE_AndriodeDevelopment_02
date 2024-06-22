package com.example.to_do_list

import java.time.Instant
import java.util.Date

data class ToDo(
    var  id: Int,
    var title: String,
    var createdAt: Date
)

fun getFakeToDo(): List<ToDo>{
    return listOf<ToDo>(
        ToDo(1, "My First ToDo", Date.from(Instant.now())),
        ToDo(2, "Second ToDo", Date.from(Instant.now())),
        ToDo(3, "Third ToDo", Date.from(Instant.now())),
        ToDo(4, "This Is My Fourth ToDo to Create A Ui", Date.from(Instant.now()))
    );
}