package ru.vyapps.vkgram

import android.app.Activity
import android.content.Context
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.EntryPointAccessors
import ru.vyapps.vkgram.core.Destinations
import ru.vyapps.vkgram.home.HomeScreen
import ru.vyapps.vkgram.home.HomeViewModel
import ru.vyapps.vkgram.login.LoginScreen
import ru.vyapps.vkgram.message_history.MessageHistoryViewModel
import ru.vyapps.vkgram.message_history.MessageHistoryScreen
import ru.vyapps.vkgram.profile.ProfileScreen
import ru.vyapps.vkgram.profile.ProfileViewModel

@Composable
fun homeViewModel(accessToken: String): HomeViewModel {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        MainActivity.ViewModelFactoryProvider::class.java
    ).provideHomeViewModelFactory()

    return viewModel(
        factory = HomeViewModel.provideFactory(factory, accessToken)
    )
}

@Composable
fun messageHistoryViewModel(
    conversationId: Long,
    accessToken: String
): MessageHistoryViewModel {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        MainActivity.ViewModelFactoryProvider::class.java
    ).provideMessageHistoryViewModelFactory()

    return viewModel(
        factory = MessageHistoryViewModel.provideFactory(
            factory = factory,
            conversationId = conversationId,
            accessToken = accessToken
        )
    )
}

@Composable
fun profileViewModel(accessToken: String): ProfileViewModel {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        MainActivity.ViewModelFactoryProvider::class.java
    ).provideProfileViewModelFactory()

    return viewModel(factory = ProfileViewModel.provideFactory(factory, accessToken))
}

@Composable
fun accessToken(): String {
    val activity = (LocalContext.current as Activity)
    val preferences = activity.getPreferences(Context.MODE_PRIVATE)
    val token = preferences.getString(activity.getString(R.string.token_pref_key), null)
    return if (token.isNullOrBlank()) "" else token
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalCoilApi
@Composable
fun NavGraph(startDestination: String) {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(
            route = Destinations.LOGIN_SCREEN,
            exitTransition = {_, _ ->
                fadeOut(animationSpec = tween(400))
            }
        ) {
            LoginScreen(navController)
        }

        composable(
            route = Destinations.HOME_SCREEN,
            enterTransition = { _, _ ->
                null
            },
            exitTransition = { _, _ ->
                null
            }
        ) {
            HomeScreen(navController, homeViewModel(accessToken()))
        }

        composable(
            route = "${Destinations.MESSAGE_HISTORY_SCREEN}/{conversationType}/{conversationId}",
            arguments = listOf(navArgument("conversationId") { type = NavType.LongType }),
            enterTransition = { _, _ ->
                slideInHorizontally(
                    initialOffsetX = { 200 },
                    animationSpec = tween(200)
                ) + fadeIn(animationSpec = tween(500))
            },
            popExitTransition = { _, _ ->
                slideOutHorizontally(
                    targetOffsetX = { 200 },
                    animationSpec = tween(200)
                ) + fadeOut(animationSpec = tween(500))
            }
        ) { backStackEntry ->
            backStackEntry.arguments?.let { args ->
//                val conversationType = args.getString("conversationType", "user")
                val conversationId = args.getLong("conversationId", 386070111)
                MessageHistoryScreen(navController, messageHistoryViewModel(conversationId, accessToken()))
            }
        }

        composable(
            route = Destinations.PROFILE_SCREEN,
            enterTransition = { _, _ ->
                fadeIn(animationSpec = tween(500))
            },
            popExitTransition = { _, _ ->
                fadeOut(animationSpec = tween(500))
            }
        ) {
            ProfileScreen(navController, profileViewModel(accessToken()))
        }
    }
}