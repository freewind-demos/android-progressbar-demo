package com.example.demo

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * ProgressBar 演示 Activity
 * 展示如何在 Android 中显示进度条
 */
class MainActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var buttonStart: Button
    private lateinit var buttonReset: Button
    private lateinit var textView: TextView

    private var progress = 0
    private val handler = Handler(Looper.getMainLooper())

    // 用于模拟进度更新的 Runnable
    private val updateProgress = object : Runnable {
        override fun run() {
            if (progress < 100) {
                progress += 10
                progressBar.progress = progress
                textView.text = "进度: $progress%"
                handler.postDelayed(this, 500) // 每500毫秒更新一次
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 初始化组件
        progressBar = findViewById(R.id.progressBar)
        buttonStart = findViewById(R.id.buttonStart)
        buttonReset = findViewById(R.id.buttonReset)
        textView = findViewById(R.id.textView)

        // 设置开始按钮点击事件
        buttonStart.setOnClickListener {
            if (progress >= 100) {
                progress = 0
            }
            handler.post(updateProgress)
        }

        // 设置重置按钮点击事件
        buttonReset.setOnClickListener {
            handler.removeCallbacks(updateProgress)
            progress = 0
            progressBar.progress = 0
            textView.text = "进度: 0%"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Activity 销毁时移除所有回调，避免内存泄漏
        handler.removeCallbacks(updateProgress)
    }
}
