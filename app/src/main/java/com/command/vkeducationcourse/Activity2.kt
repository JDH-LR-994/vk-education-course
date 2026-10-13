package com.command.vkeducationcourse

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.command.vkeducationcourse.ui.theme.VkEducationCourseTheme

class Activity2 : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val text = intent.getStringExtra(TEXT_EXTRA) ?: error("Start activity without using `newIntent`")
        setContent {
            VkEducationCourseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = text
                        )
                    }
                }
            }
        }
    }


    companion object {
        fun newIntent(context: Context, text: String) =
            Intent(context, Activity2::class.java).apply {
                putExtra(TEXT_EXTRA, text)
            }

        const val TEXT_EXTRA = "TEXT_EXTRA"
    }
}