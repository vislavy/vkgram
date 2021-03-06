package me.vislavy.vkgram.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import me.vislavy.vkgram.core.Destinations
import me.vislavy.vkgram.core.theme.MainTheme
import me.vislavy.vkgram.core.theme.VKgramTheme
import me.vislavy.vkgram.home.models.HomeViewState

@Composable
fun FriendListContent(
    modifier: Modifier = Modifier,
    viewState: HomeViewState.Display,
    navController: NavController,
    onListEnd: (Int) -> Unit
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = VKgramTheme.palette.background
    ) {
        Column {
            Spacer(Modifier.height(16.dp))

            LazyColumn {
                itemsIndexed(viewState.friends) { i, friend ->
                    FriendItem(
                        model = friend,
                        onClick = { navController.navigate("${Destinations.Profile}/${friend.id}") }
                    )

                    if (i == (viewState.friends.size - 1)) {
                        onListEnd(viewState.friends.size)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun FriendListContent_Preview() {
    MainTheme {
        FriendListContent(
            viewState = HomeViewState.Display(),
            navController = rememberNavController(),
            onListEnd = { }
        )
    }
}