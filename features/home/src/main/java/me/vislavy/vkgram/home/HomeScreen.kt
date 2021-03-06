package me.vislavy.vkgram.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Create
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.serialization.ExperimentalSerializationApi
import me.vislavy.vkgram.core.Destinations
import me.vislavy.vkgram.core.theme.VKgramTheme
import me.vislavy.vkgram.core.views.ErrorContent
import me.vislavy.vkgram.core.views.LoadingContent
import me.vislavy.vkgram.home.models.HomeIntent
import me.vislavy.vkgram.home.models.HomeViewState
import me.vislavy.vkgram.home.ui.HomeContent
import me.vislavy.vkgram.home.ui.HomeTopBar

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel
) {
    val viewState = viewModel.viewState.collectAsState()

//    when (val state = viewState.value) {
//        is HomeViewState.Loading -> LoadingContent()
//        is HomeViewState.Error -> ErrorContent(
//            onReloadClick = {
//                viewModel.onEvent(HomeIntent.ReloadScreen)
//            }
//        )
//        is HomeViewState.Display -> Scaffold(
//            topBar = {
//                state.profile?.let {
//                    HomeTopBar(
//                        viewState = state,
//                        userModel = it,
//                        onClearSelectedConvListClick = { viewModel.onEvent(HomeIntent.ClearSelectedConvList) },
//                        onDeleteConvClick = { viewModel.onEvent(HomeIntent.DeleteSelectedConvs) },
//                        navController = navController
//                    )
//                }
//            },
//            floatingActionButton = {
//                FloatingActionButton(
//                    modifier = Modifier
//                        .padding(bottom = 16.dp)
//                        .navigationBarsWithImePadding(),
//                    onClick = { navController.navigate(Destinations.NewConversation) },
//                    backgroundColor = VKgramTheme.palette.primary
//                ) {
//                    Icon(
//                        imageVector = Icons.Rounded.Create,
//                        contentDescription = null,
//                        tint = Color.White
//                    )
//                }
//            }
//        ) { paddingValues ->
//            val modifier = Modifier.padding(paddingValues)
//            HomeContent(
//                modifier = modifier,
//                viewState = state,
//                navController = navController,
//                onConversationClick = { model ->
//                    if (!state.selectModeEnabled) {
//                        navController.navigate(
//                            route = "${Destinations.MessageHistory}/${model.properties.id}"
//                        )
//                    } else {
//                        viewModel.onEvent(HomeIntent.AddToSelectedConvList(model))
//                    }
//                },
//                onConversationLongClick = { model ->
//                    viewModel.onEvent(HomeIntent.AddToSelectedConvList(model))
//                },
//                onConversationListEnd = { conversationCount ->
//                    viewModel.onEvent(HomeIntent.IncreaseConvList(conversationCount))
//                },
//                onFriendListEnd = { friendCount ->
//                    viewModel.onEvent(HomeIntent.IncreaseFriendList(friendCount))
//                }
//            )
//        }
//    }
//
//    LaunchedEffect(viewState) {
//        if (viewState.value !is HomeViewState.Display) {
//            viewModel.onEvent(HomeIntent.EnterScreen)
//        } else {
//            viewModel.onEvent(HomeIntent.UpdateProfile)
//        }
//    }
}