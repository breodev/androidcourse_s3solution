package com.example.s3solution

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController, taskViewModel: TaskViewModel, taskId: Int) {
    val task = taskViewModel.getTaskById(taskId)
    val context = LocalContext.current

    if (task != null) {
        var name by remember { mutableStateOf(task.name) }
        var description by remember { mutableStateOf(task.description) }

        Scaffold(
            topBar = {
                TopAppBar(title = { Text("Task Details") })
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Task Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Task Description") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = {
                        if (name.isNotBlank()) {
                            val updatedTask = task.copy(name = name.trim(), description = description.trim())
                            taskViewModel.updateTask(updatedTask)
                            Toast.makeText(context, "Task Updated", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        } else {
                            Toast.makeText(context, "Task name cannot be empty", Toast.LENGTH_SHORT).show()
                        }
                    }) {
                        Text("Update")
                    }
                    Button(onClick = {
                        taskViewModel.deleteTask(taskId)
                        Toast.makeText(context, "Task Deleted", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    }) {
                        Text("Delete")
                    }
                }
            }
        }
    } else {
        // Handle null task (task not found)
        Text("Task not found", modifier = Modifier.padding(16.dp))
    }
}