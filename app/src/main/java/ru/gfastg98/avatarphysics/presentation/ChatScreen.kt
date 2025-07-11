package ru.gfastg98.avatarphysics.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.gfastg98.avatarphysics.presentation.common.AvatarView
import ru.gfastg98.avatarphysics.presentation.common.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen() = Scaffold { paddings ->
    val users = remember {
        listOf(
            User(0, "user1", 0f),
            User(1, "user2", 0.1f),
            User(2, "user3", 0.3f),
            User(3, "user4", 0.4f),
            User(4, "user5", 0.7f),
            User(5, "user6", 1f)
        )
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(paddings)
    ) {

        AvatarView(
            Modifier
                .fillMaxWidth()
                .height(TopAppBarDefaults.LargeAppBarExpandedHeight)
                .background(Color.White),
            users
        )
        Column(
            Modifier.weight(1f)
        ) {

        }
    }
}