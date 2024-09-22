package com.example.tabletenniszones

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import android.widget.Button

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val startButton = findViewById<Button>(R.id.start_button)
        startButton.setOnClickListener {
            // Переход к основной активности
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Закрываем IntroActivity, чтобы пользователь не вернулся назад на вступительный экран
        }
    }
}
