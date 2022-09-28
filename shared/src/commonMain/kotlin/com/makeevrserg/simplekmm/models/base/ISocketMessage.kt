package com.makeevrserg.simplekmm.models.base

interface ISocketMessage<T> {
    val module: Int
    val action: String
    val id: Int
    val data: T
}