package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.models.User


class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getUsers()
        viewModel.users.observe(this) { userList ->
            setContent {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column() {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color(0xFF8f0678))
                        ) {
                            Text(
                                text = "Users",
                                style = MaterialTheme.typography.h2,
                                color = Color.White,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                        UserList(userList)
                    }

                }
            }

        }
    }
}

@Composable
fun UserList(userResult: Result<List<User>>) {
    when (userResult) {
        is Result.Success -> {
            val users = userResult.data
            LazyColumn {
                items(users) { user ->
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(7.dp)
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    Color(red = 245, 213, 244)
                                )
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column() {
                                Text(
                                    text = "${user.name.first} ${user.name.last}",
                                    style = MaterialTheme.typography.h5,
                                    modifier = Modifier.padding(6.dp)
                                )
                                Text(
                                    text = user.email,
                                    style = MaterialTheme.typography.body1,
                                    modifier = Modifier.padding(6.dp)
                                )
                            }
                            Text(user.nat)
                        }
                    }
                }
            }
        }
        is Result.Error -> {
            Text(text = "Error: ${userResult.exception.message}")
        }
        else -> {
        }
    }
}