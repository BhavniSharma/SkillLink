package com.example.skilllinkk

import com.example.skilllinkk.model.Category
import com.example.skilllinkk.model.Mentor
import com.example.skilllinkk.model.Skill

object DummyData {

    val categories = listOf(

        Category("All"),
        Category("Programming"),
        Category("Design"),
        Category("Photography"),
        Category("Music"),
        Category("Communication")

    )

    val skills = listOf(

        Skill(
            id = "1",
            title = "Android Development",
            description = "Learn Kotlin & Jetpack Compose",
            category = "Programming",
            difficulty = "Beginner",
            mentor = "Rahul Sharma",
            mentorEmail = "rahul@example.com",
            learners = 120,
            rating = 4.9,
            createdBy = "demo1",
            createdAt = System.currentTimeMillis()
        ),

        Skill(
            id = "2",
            title = "Graphic Design",
            description = "Adobe Photoshop & Illustrator",
            category = "Design",
            difficulty = "Intermediate",
            mentor = "Priya Singh",
            mentorEmail = "priya@example.com",
            learners = 98,
            rating = 4.8,
            createdBy = "demo2",
            createdAt = System.currentTimeMillis()
        ),

        Skill(
            id = "3",
            title = "Video Editing",
            description = "Premiere Pro Masterclass",
            category = "Photography",
            difficulty = "Intermediate",
            mentor = "Aman Verma",
            mentorEmail = "aman@example.com",
            learners = 75,
            rating = 4.7,
            createdBy = "demo3",
            createdAt = System.currentTimeMillis()
        ),

        Skill(
            id = "4",
            title = "Public Speaking",
            description = "Confidence & Communication",
            category = "Communication",
            difficulty = "Beginner",
            mentor = "Neha Kapoor",
            mentorEmail = "neha@example.com",
            learners = 60,
            rating = 4.9,
            createdBy = "demo4",
            createdAt = System.currentTimeMillis()
        )

    )

    val mentors = listOf(

        Mentor(
            name = "Rahul Sharma",
            skill = "Android Developer",
            rating = "4.9"
        ),

        Mentor(
            name = "Priya Singh",
            skill = "UI/UX Designer",
            rating = "4.8"
        ),

        Mentor(
            name = "Aman Verma",
            skill = "Video Editor",
            rating = "4.9"
        ),

        Mentor(
            name = "Neha Kapoor",
            skill = "Communication Coach",
            rating = "4.9"
        )

    )

}