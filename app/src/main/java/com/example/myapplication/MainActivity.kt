package com.example.tabletenniszones

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private val zoneNames = mutableListOf<String>()
    private var isMainScreen = false  // Флаг для отслеживания текущего экрана

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Отображаем стартовый экран
        showStartScreen()
    }

    private fun showStartScreen() {
        // Устанавливаем макет для стартового экрана
        setContentView(R.layout.start_screen_layout)

        // Ищем кнопку "Начать" на стартовом экране
        val startButton = findViewById<Button>(R.id.start_button)

        // Устанавливаем обработчик нажатия на кнопку
        startButton.setOnClickListener {
            // Переход на основной экран с ViewPager
            showMainScreen()
        }
    }

    private fun showMainScreen() {
        // Анимация исчезновения стартового экрана
        val startScreen = findViewById<View>(R.id.start_screen_container)
        val fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)
        startScreen.startAnimation(fadeOut)

        // Устанавливаем основной макет с ViewPager через небольшую задержку, чтобы завершить анимацию
        fadeOut.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}

            override fun onAnimationEnd(animation: Animation) {
                setContentView(R.layout.activity_main)

                // Инициализация ViewPager и адаптера
                viewPager = findViewById(R.id.view_pager)
                val layouts = listOf(R.layout.zone1_layout, R.layout.zone2_layout)
                extractZoneNames(layouts)
                val adapter = ZonePagerAdapter(layouts)
                viewPager.adapter = adapter

                // Устанавливаем флаг для основного экрана
                isMainScreen = true
                invalidateOptionsMenu()

                // Анимация появления первого слайда
                val mainScreen = findViewById<View>(R.id.view_pager)
                val fadeIn = AnimationUtils.loadAnimation(this@MainActivity, R.anim.fade_in)
                mainScreen.startAnimation(fadeIn)
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
    }



    // Извлекаем текстовые значения из макетов
    private fun extractZoneNames(layouts: List<Int>) {
        for (layoutRes in layouts) {
            val view = layoutInflater.inflate(layoutRes, null) // Загружаем макет
            val textView = view.findViewById<TextView>(R.id.zone_name_text_view) // Ищем текстовое поле
            zoneNames.add(textView.text.toString()) // Добавляем текст в список
        }
    }

    // Создаем меню и добавляем в него динамически названия зон
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (isMainScreen) {  // Проверяем, находимся ли мы на основном экране
            menuInflater.inflate(R.menu.main_menu, menu)
            zoneNames.forEachIndexed { index, zoneName ->
                menu?.add(0, index, Menu.NONE, zoneName) // Добавляем название зоны в меню
            }
            return true
        }
        return false  // Возвращаем false, если мы не на основном экране
    }

    // Обрабатываем нажатие на элементы меню
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (isMainScreen) {
            viewPager.currentItem = item.itemId // Переходим на выбранную зону
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
