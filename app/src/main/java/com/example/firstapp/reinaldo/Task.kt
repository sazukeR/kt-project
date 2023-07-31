package com.example.firstapp.reinaldo

data class Task(val name: String, val category: TaskCategory, var isSelected: Boolean = false)