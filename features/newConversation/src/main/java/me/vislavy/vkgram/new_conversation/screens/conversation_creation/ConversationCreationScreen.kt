package me.vislavy.vkgram.new_conversation.screens.conversation_creation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import me.vislavy.vkgram.core.theme.VKgramTheme
import me.vislavy.vkgram.core.views.ErrorContent
import me.vislavy.vkgram.core.views.LoadingContent
import me.vislavy.vkgram.new_conversation.UserModel
import me.vislavy.vkgram.new_conversation.screens.conversation_creation.models.ConversationCreationEvent
import me.vislavy.vkgram.new_conversation.screens.conversation_creation.models.ConversationCreationViewState
import me.vislavy.vkgram.new_conversation.screens.conversation_creation.views.ConversationCreationContent
import me.vislavy.vkgram.new_conversation.screens.conversation_creation.views.ConversationCreationTopBar

@Composable
fun ConversationCreationScreen(
    members: List<UserModel>,
    navController: NavController,
    viewModel: ConversationCreationViewModel
) {
//    val gallerySheetState = rememberModalBottomSheetState(
//        initialValue = ModalBottomSheetValue.Hidden
//    )
//    ModalBottomSheetLayout(
//        sheetContent = {
//        },
//        sheetState = gallerySheetState,
//        sheetBackgroundColor = Color.Transparent
//    ) {
//        Scaffold(
//            topBar = {
//                ConversationCreationTopBar(navController = navController)
//            },
//            floatingActionButton = {
//                FloatingActionButton(
//                    onClick = {
//                        viewModel.onEvent(ConversationCreationEvent.CreateConversation)
//                    },
//                    modifier = Modifier
//                        .padding(bottom = 16.dp)
//                        .navigationBarsWithImePadding(),
//                    backgroundColor = VKgramTheme.palette.primary
//                ) {
//                    Icon(
//                        imageVector = Icons.Rounded.Done,
//                        contentDescription = null,
//                        tint = Color.White
//                    )
//                }
//            }
//        ) {
//            val viewState = viewModel.viewState.collectAsState()
//
//            when (val state = viewState.value) {
//                is ConversationCreationViewState.Loading -> LoadingContent()
//                is ConversationCreationViewState.Error -> ErrorContent(
//                    onReloadClick = {
//                        navController.popBackStack()
//                    }
//                )
//                is ConversationCreationViewState.Display -> ConversationCreationContent(
//                    viewState = state,
//                    gallerySheetState = gallerySheetState,
//                    onMemberRemoveClick = {
//                        viewModel.onEvent(ConversationCreationEvent.RemoveMember(it))
//                    }
//                )
//            }
//
//            LaunchedEffect(viewState) {
//                viewModel.onEvent(ConversationCreationEvent.EnterScreen(members))
//            }
//        }
//    }
}