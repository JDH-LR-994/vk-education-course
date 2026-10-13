package com.command.vkeducationcourse

import android.content.Intent
import android.content.Intent.ACTION_DIAL
import android.content.Intent.ACTION_SEND
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.net.toUri
import com.command.vkeducationcourse.ui.theme.VkEducationCourseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VkEducationCourseTheme {
                MainScreen(
                    onOpenSecondActivity = { text ->
                        startActivity(Activity2.newIntent(this, text))
                    },
                    onCall = { phoneNumber ->
                        val uri = "tel:$phoneNumber".toUri()
                        startActivity(Intent(ACTION_DIAL, uri))
                    },
                    onShare = { text ->
                        val intent = Intent(ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_TEXT, text)
                        }
                        startActivity(Intent.createChooser(intent, "Поделиться через…"))
                    }
                )
            }
        }
    }
}

@Composable
fun MainScreen(
    onOpenSecondActivity: (String) -> Unit,
    onCall: (String) -> Unit,
    onShare: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var text by rememberSaveable { mutableStateOf("") }

    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Введите текст") }
            )
            Button(
                onClick = { onOpenSecondActivity(text) }
            ) {
                Text("Открыть вторую Activity")
            }
            Button(
                onClick = { onCall(text) }
            ) {
                Text("Позвонить другу")
            }
            Button(
                onClick = { onShare(text) }
            ) {
                Text("Поделиться текстом")
            }
        }
    }
}
