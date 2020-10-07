package com.example.cats.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
//
//class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(ArticleListViewModel::class.java)) {
//            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "articles").build()
//            @Suppress("UNCHECKED_CAST")
//            return ArticleListViewModel(db.articleDao()) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//
//    }
//}