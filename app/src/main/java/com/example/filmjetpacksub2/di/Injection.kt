package com.example.filmjetpacksub2.di

import com.example.filmjetpacksub2.data.Repository
import com.example.filmjetpacksub2.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(): Repository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return Repository.getInstance(remoteDataSource)
    }
}