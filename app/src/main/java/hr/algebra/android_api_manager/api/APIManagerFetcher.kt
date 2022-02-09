package hr.algebra.android_api_manager.api

import android.content.Context
import android.util.Log
import hr.algebra.android_api_manager.APIManagerReceiver
import hr.algebra.android_api_manager.framework.sendBroadcast
import hr.algebra.android_api_manager.handler.downloadImageAndStore
import hr.algebra.android_api_manager.model.Item
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIManagerFetcher(private val context: Context) {

    private var apiManagerApi: APIManagerApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiManagerApi = retrofit.create(APIManagerApi::class.java)
    }

    fun fetchItems() {
        val request = apiManagerApi.fetchItems()

        request.enqueue(object : Callback<APIManagerItem> {
            override fun onResponse(
                call: Call<APIManagerItem>,
                response: Response<APIManagerItem>
            ) {
                response.body()?.let {
                    populateItems(it)
                }
            }

            override fun onFailure(call: Call<APIManagerItem>, t: Throwable) {
                Log.d(javaClass.name, t.message, t)
            }

        })
    }

    private fun populateItems(apiManagerItems: APIManagerItem) {

        GlobalScope.launch {
            val items = mutableListOf<Item>()
            apiManagerItems.items.forEach {
                val picturePath = downloadImageAndStore(
                    context,
                    it.snippet.thumbnails.default.url,
                    it.snippet.title.replace(" ", "_")
                )
                items.add(
                    Item(
                        null,
                        it.snippet.title,
                        it.snippet.description,
                        picturePath ?: "",
                        it.id,
                        it.snippet.publishedAt,
                        false
                    )
                )
            }
            //setBooleanPreference(DATA_IMPORTED, true)
            context.sendBroadcast<APIManagerReceiver>()
        }
    }
}