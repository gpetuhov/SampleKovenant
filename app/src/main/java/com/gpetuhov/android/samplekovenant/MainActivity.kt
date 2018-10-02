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
            task {
                Thread.sleep(1000)
                1 + 1
            } then {
                Thread.sleep(1000)
                it * 2
            } success {
                Log.d(TAG, "Result is $it")
            } fail {
                Log.d(TAG, it.message)
            } always {
                Log.d(TAG, "End of task")
                runOnUiThread { toast("Task complete") }
            }
        }
    }
}
