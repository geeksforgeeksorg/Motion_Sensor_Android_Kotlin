package org.geeksforgeeks.motionsensor

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.geeksforgeeks.motionsensor.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel.sensorData.observe(this) { (sides, upDown) ->
            binding.textView.apply {
                rotationX = upDown * 3f
                rotationY = sides * 3f
                rotation = -sides
                translationX = sides * -10
                translationY = upDown * 10
            }

            val backgroundColor = if (upDown.toInt() == 0 && sides.toInt() == 0) Color.GREEN else Color.RED
            val textColor = if (upDown.toInt() == 0 && sides.toInt() == 0) Color.BLACK else Color.WHITE
            binding.textView.setBackgroundColor(backgroundColor)
            binding.textView.setTextColor(textColor)
            binding.textView.text = buildString {
                append("up/down ")
                append(upDown.toInt())
                append("\n")
                append("left/right ")
                append(sides.toInt())
            }
        }
    }
}