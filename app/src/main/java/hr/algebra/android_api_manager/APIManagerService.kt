package hr.algebra.android_api_manager

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService
import hr.algebra.android_api_manager.framework.sendBroadcast
import hr.algebra.android_api_manager.framework.setBooleanPreference

private const val JOB_ID = 1
class APIManagerService : JobIntentService() {
    override fun onHandleWork(intent: Intent) {
        //Fake work
        Thread.sleep(6000)
        setBooleanPreference(DATA_IMPORTED, true)
        sendBroadcast<APIManagerReceiver>()
    }
    companion object{
        fun enqueue(context: Context, intent: Intent){
            enqueueWork(context, APIManagerService::class.java, JOB_ID, intent)
        }
    }
}