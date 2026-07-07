package com.example.skilllinkk.model

data class Skill(

    val id: String = "",

    val title: String = "",

    val description: String = "",

    val category: String = "",

    val difficulty: String = "Beginner",
    
    val mentor: String = "",

    val mentorEmail: String = "",

    val learners: Int = 0,

    val rating: Double = 0.0,

    val createdBy: String = "",

    val createdAt: Long = System.currentTimeMillis()

)