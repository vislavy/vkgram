package ru.vyapps.vkgram.home.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.vyapps.vkgram.core.repositories.UserRepo
import ru.vyapps.vkgram.core.repositories.UserRepoImpl
import ru.vyapps.vkgram.home.repositories.*

@Suppress("UNUSED")
@Module
@InstallIn(SingletonComponent::class)
interface ConversationsBindModule {

    @Binds
    fun bindConversationRepo(impl: ConversationRepoImpl): ConversationRepo

    @Binds
    fun bindLongPollServerRepo(impl: LongPollServerRepoImpl): LongPollServerRepo
}