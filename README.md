# android-progressbar-demo

## 简介

演示 ProgressBar 进度条组件的使用，显示任务进度。

## 基本原理

- ProgressBar 用于显示任务进度
- 水平进度条：`style="?android:attr/progressBarStyleHorizontal"`
- 通过 `progress` 属性设置当前进度
- 通过 `max` 属性设置最大值

## 启动和使用

### 环境要求
- Android Studio 4.0+
- JDK 11+

### 安装和运行
1. 用 Android Studio 打开此项目
2. 点击"开始"按钮观察进度变化
3. 点击"重置"按钮重置进度

## 教程

### ProgressBar 基础

```xml
<ProgressBar
    style="?android:attr/progressBarStyleHorizontal"
    android:id="@+id/progressBar"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:max="100"
    android:progress="0" />
```

### 更新进度

```kotlin
val progressBar = findViewById<ProgressBar>(R.id.progressBar)

// 设置进度
progressBar.progress = 50

// 获取当前进度
val current = progressBar.progress
```

### 模拟进度更新

```kotlin
val handler = Handler(Looper.getMainLooper())

val updateProgress = object : Runnable {
    override fun run() {
        if (progress < 100) {
            progress += 10
            progressBar.progress = progress
            handler.postDelayed(this, 500)
        }
    }
}

handler.post(updateProgress)
```

### 注意事项

- 记得在 Activity 销毁时移除 Handler 回调
- 使用 Handler 在主线程更新 UI
- 进度值不能超过 max 设置的值
