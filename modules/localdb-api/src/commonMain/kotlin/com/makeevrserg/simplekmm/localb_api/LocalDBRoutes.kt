package com.makeevrserg.simplekmm.localb_api

object LocalDBRoutes {
    const val BASE_URL = "http://192.168.1.3:8080/"
    const val Files = "${BASE_URL}files"
    fun staticPath(path: String) = "${BASE_URL}$path"
    fun filePath(id:Int) = "${BASE_URL}files/$id"
}
