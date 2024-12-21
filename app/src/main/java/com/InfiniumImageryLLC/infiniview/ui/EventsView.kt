package com.InfiniumImageryLLC.infiniview.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.InfiniumImageryLLC.infiniview.models.Event
import com.InfiniumImageryLLC.infiniview.models.EventLink
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request

@Composable
fun EventsView() {
    var showdownEvents by remember { mutableStateOf<List<Event>>(emptyList()) }
    var currentEvents by remember { mutableStateOf<List<Event>>(emptyList()) }
    var completedEvents by remember { mutableStateOf<List<Event>>(emptyList()) }
    var upcomingEvents by remember { mutableStateOf<List<Event>>(emptyList()) }

    var loading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        fetchEvents(
            onDone = { showdowns, currents, completeds, upcomings ->
                showdownEvents = showdowns
                currentEvents = currents
                completedEvents = completeds
                upcomingEvents = upcomings
                loading = false
            },
            onError = {
                errorMessage = it
                loading = false
            }
        )
    }

    if (loading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
            CircularProgressIndicator(color = Color.White)
        }
    } else if (errorMessage != null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
            Text(text = errorMessage!!, color = Color.White)
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            item {
                Text("LIVE Creator Showdown", color = Color.White)
            }
            items(showdownEvents) { event ->
                EventRowSummary(event)
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text("Current Events", color = Color.White)
            }
            items(currentEvents) { event ->
                EventRowSummary(event)
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text("Completed Events", color = Color.White)
            }
            items(completedEvents) { event ->
                EventRowSummary(event)
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text("Upcoming Events", color = Color.White)
            }
            items(upcomingEvents) { event ->
                EventRowSummary(event)
            }
        }
    }
}

suspend fun fetchEvents(
    onDone: (
        showdowns: List<Event>,
        currents: List<Event>,
        completeds: List<Event>,
        upcomings: List<Event>
    ) -> Unit,
    onError: (String) -> Unit
) {
    try {
        val showdownURL = "https://jrftw.github.io/iviewevents/showdown.json"
        val upcomingURL = "https://jrftw.github.io/iviewevents/upcoming.json"
        val currentURL = "https://jrftw.github.io/iviewevents/current.json"
        val completedURL = "https://jrftw.github.io/iviewevents/completed.json"

        val showdowns = fetchEventList(showdownURL)
        val upcomings = fetchEventList(upcomingURL)
        val currents = fetchEventList(currentURL)
        val completeds = fetchEventList(completedURL)

        onDone(showdowns, currents, completeds, upcomings)
    } catch (e: Exception) {
        onError("Failed to load events: ${e.message}")
    }
}

suspend fun fetchEventList(urlString: String): List<Event> {
    return withContext(Dispatchers.IO) {
        val client = OkHttpClient()
        val request = Request.Builder().url(urlString).build()
        val response = client.newCall(request).execute()
        val data = response.body?.string() ?: ""
        if (data.isNotEmpty()) {
            Json.decodeFromString(data)
        } else emptyList()
    }
}

@Composable
fun EventRowSummary(event: Event) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(text = event.title, color = Color.White)
        Text(text = "${event.startDate} - ${event.endDate}", color = Color.Gray)
    }
}

// Optional detail if you want a separate screen
@Composable
fun EventDetailView(event: Event) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        event.imageURL?.let { url ->
            AsyncImage(
                model = url,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(200.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = event.title, color = Color.White)
        Text(text = "${event.startDate} - ${event.endDate}", color = Color.Gray)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = event.description, color = Color.White)
        event.links?.forEach { link ->
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = link.title, color = Color.Cyan)
        }
    }
}
