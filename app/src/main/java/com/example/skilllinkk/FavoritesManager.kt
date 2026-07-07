package com.example.skilllinkk

import androidx.compose.runtime.mutableStateListOf
import com.example.skilllinkk.firebase.FirebaseAuthManager
import com.example.skilllinkk.firebase.FirestoreManager
import com.example.skilllinkk.model.Skill

object FavoritesManager {

    val favoriteSkills = mutableStateListOf<Skill>()


    fun loadFavorites(
        onSuccess: (() -> Unit)? = null,
        onFailure: ((Exception) -> Unit)? = null
    ) {

        val uid =
            FirebaseAuthManager.auth.currentUser?.uid

        if (uid == null) {

            favoriteSkills.clear()

            onFailure?.invoke(
                Exception("User not logged in")
            )

            return
        }


        FirestoreManager.db
            .collection("users")
            .document(uid)
            .collection("favorites")
            .get()
            .addOnSuccessListener { documents ->

                favoriteSkills.clear()

                documents.forEach { document ->

                    val skill =
                        document.toObject(
                            Skill::class.java
                        )

                    if (
                        favoriteSkills.none {
                            it.id == skill.id
                        }
                    ) {

                        favoriteSkills.add(skill)

                    }

                }

                onSuccess?.invoke()

            }
            .addOnFailureListener { exception ->

                onFailure?.invoke(exception)

            }

    }


    fun addSkill(
        skill: Skill,
        onSuccess: (() -> Unit)? = null,
        onFailure: ((Exception) -> Unit)? = null
    ) {

        val uid =
            FirebaseAuthManager.auth.currentUser?.uid

        if (uid == null) {

            onFailure?.invoke(
                Exception("User not logged in")
            )

            return
        }


        val skillId = skill.id

        if (skillId.isBlank()) {

            onFailure?.invoke(
                Exception("Invalid skill ID")
            )

            return
        }


        FirestoreManager.db
            .collection("users")
            .document(uid)
            .collection("favorites")
            .document(skillId)
            .set(skill)
            .addOnSuccessListener {

                if (
                    favoriteSkills.none {
                        it.id == skill.id
                    }
                ) {

                    favoriteSkills.add(skill)

                }

                onSuccess?.invoke()

            }
            .addOnFailureListener { exception ->

                onFailure?.invoke(exception)

            }

    }


    fun removeSkill(
        skill: Skill,
        onSuccess: (() -> Unit)? = null,
        onFailure: ((Exception) -> Unit)? = null
    ) {

        val uid =
            FirebaseAuthManager.auth.currentUser?.uid

        if (uid == null) {

            onFailure?.invoke(
                Exception("User not logged in")
            )

            return
        }


        if (skill.id.isBlank()) {

            onFailure?.invoke(
                Exception("Invalid skill ID")
            )

            return
        }


        FirestoreManager.db
            .collection("users")
            .document(uid)
            .collection("favorites")
            .document(skill.id)
            .delete()
            .addOnSuccessListener {

                favoriteSkills.removeAll {
                    it.id == skill.id
                }

                onSuccess?.invoke()

            }
            .addOnFailureListener { exception ->

                onFailure?.invoke(exception)

            }

    }


    fun isFavorite(
        skill: Skill
    ): Boolean {

        return favoriteSkills.any {
            it.id == skill.id
        }

    }


    fun clearFavorites() {

        favoriteSkills.clear()

    }

}