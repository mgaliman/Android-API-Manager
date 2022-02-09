package hr.algebra.android_api_manager.api

import retrofit2.Call
import retrofit2.http.GET

const val API_URL = "https://youtube.googleapis.com/youtube/v3/"

interface APIManagerApi {
    @GET("videos?part=snippet%2CcontentDetails%2Cstatistics&id=A3N1KnL3r9U%2CKC5EQsbzGLk&key=AIzaSyBLni7RrbbyN4se2ZcO6Lnf2ZEA4JkuqwU")
    fun fetchItems() : Call<APIManagerItem>
}