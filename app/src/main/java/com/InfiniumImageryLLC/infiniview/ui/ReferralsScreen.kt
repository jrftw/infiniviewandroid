package com.InfiniumImageryLLC.infiniview.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.InfiniumImageryLLC.infiniview.viewmodel.ReferralsViewModel

@Composable
fun ReferralsScreen(vm: ReferralsViewModel = viewModel()) {
    val referralCount by vm.referralCount.observeAsState(0)
    val alertMessage by vm.alertMessage.observeAsState()

    var selectedCount by remember { mutableStateOf<Int?>(null) }
    var usernames by remember { mutableStateOf<List<String>>(emptyList()) }

    Scaffold(
        topBar = { AppTopBar("Referrals") }
    ) { padding ->
        Surface(modifier = Modifier.padding(padding)) {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Text("You have referred $referralCount creator(s).")

                var referralNumText by remember { mutableStateOf("") }
                OutlinedTextField(
                    value = referralNumText,
                    onValueChange = { referralNumText = it },
                    label = { Text("Number of new referrals") }
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = {
                    selectedCount = referralNumText.toIntOrNull()
                    if (selectedCount == null) {
                        vm.alertMessage.value = "Please enter a valid number"
                    } else {
                        usernames = List(selectedCount!!) { "" }
                    }
                }) {
                    Text("Set Referral Count")
                }

                Spacer(modifier = Modifier.height(10.dp))

                if (selectedCount != null && selectedCount!! > 0) {
                    usernames.forEachIndexed { i, u ->
                        var name by remember { mutableStateOf(u) }
                        OutlinedTextField(
                            value = name,
                            onValueChange = { newVal ->
                                val arr = usernames.toMutableList()
                                arr[i] = newVal
                                usernames = arr
                            },
                            label = { Text("Username for referral ${i+1}") },
                            modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Button(onClick = {
                        if (usernames.any { it.isBlank() }) {
                            vm.alertMessage.value = "Please fill all usernames."
                        } else {
                            vm.addReferrals(usernames)
                        }
                    }) {
                        Text("Add Referrals")
                    }
                }

                if (alertMessage != null) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(alertMessage!!, color = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}
