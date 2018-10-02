package com.gpetuhov.android.samplekovenant

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import nl.komponents.kovenant.task
import nl.komponents.kovenant.then
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    private val TAG = "Kovenant"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView.setOnClickListener {
            // This runs in background
            task {
                Thread.sleep(1000)
                1 + 1
            // Tasks can be chained
            } then {
                Thread.sleep(1000)
                it * 2
            // This runs on success
            } success {
                Log.d(TAG, "Result is $it")
            // This runs on error
            } fail {
                Log.d(TAG, it.message)
            // This runs both on success and on error
            } always {
                Log.d(TAG, "End of task")
                // This is needed to show toast on main thread
                runOnUiThread { toast("Task complete") }
            }
        }
    }
}
