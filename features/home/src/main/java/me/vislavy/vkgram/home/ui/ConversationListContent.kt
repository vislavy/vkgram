package me.vislavy.vkgram.home.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
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
import kotlinx.serialization.ExperimentalSerializationApi
import me.vislavy.vkgram.core.ConversationModel
import me.vislavy.vkgram.core.theme.MainTheme
import me.vislavy.vkgram.core.theme.VKgramTheme
import me.vislavy.vkgram.home.models.HomeViewState
import me.vislavy.vkgram.api.data.Message
import java.util.*

@ExperimentalFoundationApi
@ExperimentalSerializationApi
@ExperimentalAnimationApi
@Composable
fun ConversationListContent(
    modifier: Modifier = Modifier,
    viewState: HomeViewState.Display,
    onConversationClick: (ConversationModel) -> Unit,
    onConversationLongClick: (ConversationModel) -> Unit,
    onListEnd: (Int) -> Unit
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = VKgramTheme.palette.background
    ) {
        Column {
            Spacer(Modifier.height(16.dp))

            LazyColumn {
                itemsIndexed(viewState.conversations) { index, conversation ->
                    ConversationItem(
                        model = conversation,
                        onClick = { model ->
                            onConversationClick(model)
                        },
                        onLongClick = { model ->
                            onConversationLongClick(model)
                        },
                        isSelect = viewState.selectedConversations.contains(conversation)
                    )

                    if (index == (viewState.conversations.size - 1)) {
                        onListEnd(viewState.conversations.size)
                    }
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalSerializationApi
@ExperimentalAnimationApi
@Preview
@Composable
fun ConversationListContent_Preview() {
    MainTheme {
        ConversationListContent(
            viewState = HomeViewState.Display(
                conversations = listOf(
                    ConversationModel(
                        title = "It's Sample",
                        unreadMessageCount = 2,
                        lastMessage = Message(
                            id = 1,
                            userId = 1,
                            ConversationId = 1,
                            text = "Sample message",
                            attachments = emptyList(),
                            date = Date(),
                            out = true
                        ),
                    ),
                    ConversationModel(
                        title = "It's Sample 2",
                        unreadMessageCount = 2,
                        lastMessage = Message(
                            id = 1,
                            userId = 1,
                            ConversationId = 1,
                            text = "Sample message 2",
                            attachments = emptyList(),
                            date = Date(),
                            out = true
                        ),
                    ),
                ),
                friends = emptyList()
            ),
            onListEnd = { },
            onConversationClick = { },
            onConversationLongClick = { }
        )
    }
}