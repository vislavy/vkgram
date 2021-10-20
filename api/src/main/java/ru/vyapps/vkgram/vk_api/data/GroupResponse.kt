package ru.vyapps.vkgram.vk_api.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroupResponse(
    val response: List<Group>
)

@Serializable
data class Group(
    val id: Int,
    val name: String,
    @SerialName("screen_name") val screenName: String,
    @SerialName("is_closed") val isClosed: Int,
    @SerialName("photo_50") val photo50Url: String,
    @SerialName("photo_100") val photo100Url: String,
    @SerialName("photo_200") val photo200Url: String
)
