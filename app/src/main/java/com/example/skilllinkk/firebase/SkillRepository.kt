package com.example.skilllinkk.firebase

import com.example.skilllinkk.model.Skill
import com.google.firebase.firestore.FirebaseFirestore

object SkillRepository {

    private val db = FirebaseFirestore.getInstance()

    fun addSkill(
        skill: Skill,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {

        val document = db.collection("skills").document()

        val newSkill = skill.copy(
            id = document.id
        )

        document
            .set(newSkill)
            .addOnSuccessListener {

                onSuccess()

            }
            .addOnFailureListener {

                onFailure(it)

            }

    }

    fun getSkills(
        onResult: (List<Skill>) -> Unit
    ) {

        db.collection("skills")
            .orderBy("createdAt")
            .get()
            .addOnSuccessListener { result ->

                val skills = result.toObjects(Skill::class.java)

                onResult(skills)

            }

    }

    fun deleteSkill(
        skillId: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {

        db.collection("skills")
            .document(skillId)
            .delete()
            .addOnSuccessListener {

                onSuccess()

            }
            .addOnFailureListener {

                onFailure(it)

            }

    }

    fun updateSkill(
        skill: Skill,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {

        db.collection("skills")
            .document(skill.id)
            .set(skill)
            .addOnSuccessListener {

                onSuccess()

            }
            .addOnFailureListener {

                onFailure(it)

            }

    }

}