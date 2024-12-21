package com.InfiniumImageryLLC.InfiniView.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.InfiniumImageryLLC.InfiniView.models.Announcement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request

@Composable
fun AnnouncementsView() {
    var announcements by remember { mutableStateOf<List<Announcement>>(emptyList()) }
    var loading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        // Example URL
        val url = "https://jrftw.github.io/InfiniView-Announcements/Announcements.json"
        try {
            val data = withContext(Dispatchers.IO) {
                val client = OkHttpClient()
                val request = Request.Builder().url(url).build()
                val response = client.newCall(request).execute()
                response.body?.string() ?: throw Exception("No data received.")
            }
            val decoded = Json.decodeFromString<List<Announcement>>(data)
            announcements = decoded
        } catch (e: Exception) {
            errorMessage = "Failed to load announcements: ${e.message}"
        } finally {
            loading = false
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black
    ) {
        when {
            loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color.White)
                }
            }
            errorMessage != null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(errorMessage!!, color = Color.White)
                }
            }
            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    items(announcements) { announcement ->
                        Column(modifier = Modifier.padding(vertical = 8.dp)) {
                            Text(
                                text = announcement.title,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Text(
                                text = "${announcement.date} • ${announcement.time}",
                                style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = announcement.description,
                                style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
                            )

                            announcement.image?.takeIf { it.isNotEmpty() }?.let { imageUrl ->
                                Spacer(modifier = Modifier.height(8.dp))
                                AsyncImage(
                                    model = imageUrl,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .heightIn(min = 100.dp, max = 300.dp)
                                )
                            }
                            announcement.link?.takeIf { it.isNotEmpty() }?.let { linkUrl ->
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Learn More: $linkUrl",
                                    color = Color.Cyan
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))
                        }
                    }
                }
            }
        }
    }
}
