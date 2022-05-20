package com.formation.app.services.Wrapper

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryServiceWrapper @Inject constructor() {

//    suspend fun fetchCategory(): List<Category>? {
//        val query = ParseQuery.getQuery(Category::class.java)
//        query.find { objects, e ->
//            if (e == null) {
//                if (objects.isNotEmpty()) {
//                    println("CategoryViewModel : fetchCategory -> SUCCESS")
//                    return@findInBackground objects
//                } else {
//                    println("CategoryViewModel : fetchCategory -> EMPTY")
//                    return null
//                }
//            } else {
//                println("CategoryViewModel : fetchCategory -> FAILED")
//                return null
//            }
//        }
//    }

    suspend fun <T> callApiNoCatch(block: suspend () -> T): T? =
        withContext(Dispatchers.IO) {
            try {
                block()
            } catch (ex: Exception) {
                println(ex.localizedMessage)
                null
            }
        }
}