package com.example.s3solution.ui

data class Task(
    val id: Int,             // Unique identifier for the task
    val name: String,        // Name of the task
    val description: String, // Description of the task
    val isCompleted: Boolean = false // Completion status
)