package ru.vyapps.vkgram.home

import android.app.Activity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Create
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ru.vyapps.vkgram.core.Destinations
import ru.vyapps.vkgram.core.LocalProfile
import ru.vyapps.vkgram.core.theme.VKgramTheme
import ru.vyapps.vkgram.core.views.ErrorContent
import ru.vyapps.vkgram.core.views.LoadingContent
import ru.vyapps.vkgram.home.models.HomeEvent
import ru.vyapps.vkgram.home.models.HomeViewState
import ru.vyapps.vkgram.home.views.HomeContent
import ru.vyapps.vkgram.home.views.HomeTopBar

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel
) {
    val context = (LocalContext.current as Activity)
    WindowCompat.setDecorFitsSystemWindows(context.window, true)
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(VKgramTheme.palette.background)

    val viewState = viewModel.viewState.collectAsState()

    Scaffold(
        topBar = {
            HomeTopBar(
                userModel = LocalProfile.current,
                navController = navController
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Destinations.NewConversation)
                },
                modifier = Modifier.padding(bottom = 16.dp),
                backgroundColor = VKgramTheme.palette.secondary
            ) {
                Icon(
                    imageVector = Icons.Rounded.Create,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    ) { padding ->
        val modifier = Modifier.padding(padding)
        when (val state = viewState.value) {
            is HomeViewState.Loading -> LoadingContent(modifier)
            is HomeViewState.Error -> ErrorContent(
                modifier = modifier,
                onReloadClick = {
                    viewModel.onEvent(HomeEvent.ReloadScreen)
                }
            )
            is HomeViewState.Display -> HomeContent(
                modifier = modifier,
                viewState = state,
                navController = navController,
                onConversationListEnd = { conversationCount ->
                    viewModel.onEvent(HomeEvent.ConversationListEnd(conversationCount))
                },
                onFriendListEnd = { friendCount ->
                    viewModel.onEvent(HomeEvent.FriendListEnd(friendCount))
                }
            )
            else -> throw NotImplementedError("Unexpected home state")
        }
    }

    LaunchedEffect(viewState) {
        if (viewModel.viewState.value !is HomeViewState.Display) {
            viewModel.onEvent(HomeEvent.EnterScreen)
        }
    }
}