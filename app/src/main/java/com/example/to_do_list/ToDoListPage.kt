package com.example.to_do_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun ToDoListPage(viewModel: ToDoViewModel) {
    val todolist by viewModel.toDoList.observeAsState()
    var inputText by remember {
        mutableStateOf("")
    }


    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(5.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding()
                .padding(top = 40.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedTextField(value = inputText, onValueChange = {
                inputText = it
            })
            Button(onClick = {
                viewModel.addToDo(inputText)
                inputText = ""
            }) {
                Text(text = "Ad")
            }
        }

        todolist?.let {
            LazyColumn(modifier = Modifier.padding()) {
                itemsIndexed(it){index: Int, item:ToDo->
                    ToDoItem(item = item, onDelete = {
                        viewModel.deleteToDo(item.id)
                    })
                }
            }
        }?: Text(
            modifier = Modifier.fillMaxWidth().padding(top = 15.dp),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = Color.Red,
            text ="No Data Found"
        )
    }
}

@Composable
fun ToDoItem(item : ToDo , onDelete : () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = SimpleDateFormat("hh:mm:aa, dd/mm", Locale.ENGLISH).format(item.createdAt), fontSize = 13.sp, color = Color.Gray)
            Text(text = item.title.toString(), fontSize = 20.sp, color = Color.White)
        }
        IconButton(onClick = { onDelete() }) {
            Icon(painter = painterResource(id = R.drawable.delete_button_svgrepo_com), contentDescription ="Delete", tint = Color.White)
        }
    }
}