package me.vislavy.vkgram.core.mappers

import me.vislavy.vkgram.api.data.ConversationByIdResponse
import me.vislavy.vkgram.api.data.ConversationData
import me.vislavy.vkgram.api.data.ConversationType
import me.vislavy.vkgram.core.ConversationModel
import javax.inject.Inject
import kotlin.math.abs

class ConversationDataMapper @Inject constructor() {

    fun map(input: ConversationData): List<ConversationModel> {
        val conversations = mutableListOf<ConversationModel>()

        input.conversations.forEach { conversationData ->
            var conversation = with(conversationData) {
                ConversationModel(
                    properties = conversation.properties,
                    lastMessage = lastMessage,
                    lastMessageLocalId = conversation.lastMessageLocalId,
                    lastReadMessageLocalId = conversation.lastReadMessageLocalId,
                    unreadMessageCount = conversation.unreadMessageCount,
                    pushSettings = conversation.pushSettings,
                    sortId = conversation.sortId
                )
            }

            when (conversation.properties.type) {
                ConversationType.User -> {
                    val userIndex = input.users.binarySearch {
                        it.id.compareTo(conversation.properties.id)
                    }
                    val user = input.users[userIndex]
                    conversation = conversation.copy(
                        title = "${user.firstName} ${user.lastName}",
                        photo = user.photoUrl
                    )
                }
                ConversationType.Group -> {
                    val groupIndex = input.groups.binarySearch {
                        it.id.compareTo(abs(conversation.properties.id))
                    }
                    val group = input.groups[groupIndex]
                    conversation = conversation.copy(
                        title = group.name,
                        photo = group.photo
                    )
                }
                ConversationType.Chat -> {
                    var lastMessageAuthor = ""
                    conversationData.lastMessage?.let { lastMessage ->
                        val lastMessageAuthorIndex = input.users.binarySearch { user ->
                            user.id.compareTo(lastMessage.userId)
                        }
                        lastMessageAuthor = "${input.users[abs(lastMessageAuthorIndex)].firstName}: "
                    }
                    conversation = conversation.copy(
                        title = conversationData.conversation.chatProperties?.title.toString(),
                        photo = conversationData.conversation.chatProperties?.photo?.photo200.toString(),
                        memberCount = conversationData.conversation.chatProperties?.memberCount ?: 0,
                        lastMessageAuthor = lastMessageAuthor
                    )
                }
            }

            conversations.add(conversation)
        }

        return conversations
    }

    fun map(input: ConversationByIdResponse): List<ConversationModel> {
        val conversations = mutableListOf<ConversationModel>()

        input.items.forEach { conversationModel ->
            var conversation = with(conversationModel) {
                ConversationModel(
                    properties = properties,
                    lastMessageLocalId = lastMessageLocalId,
                    lastReadMessageLocalId = lastReadMessageLocalId,
                    unreadMessageCount = unreadMessageCount,
                    pushSettings = pushSettings,
                    sortId = sortId
                )
            }

            when (conversation.properties.type) {
                ConversationType.User -> {
                    val userIndex = input.profiles.binarySearch {
                        it.id.compareTo(conversation.properties.id)
                    }
                    val user = input.profiles[userIndex]
                    conversation = conversation.copy(
                        title = "${user.firstName} ${user.lastName}",
                        photo = user.photoUrl
                    )
                }
                ConversationType.Group -> {
                    val groupIndex = input.groups.binarySearch {
                        it.id.compareTo(abs(conversation.properties.id))
                    }
                    val group = input.groups[groupIndex]
                    conversation = conversation.copy(
                        title = group.name,
                        photo = group.photo
                    )
                }
                ConversationType.Chat -> {
                    conversation = conversation.copy(
                        title = conversationModel.chatProperties?.title.toString(),
                        photo = conversationModel.chatProperties?.photo?.photo200.toString()
                    )
                }
            }

            conversations.add(conversation)
        }

        return conversations
    }
}