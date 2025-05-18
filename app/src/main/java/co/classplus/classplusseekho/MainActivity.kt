package co.classplus.classplusseekho

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import co.classplus.classplusseekho.ui.screens.*
import co.classplus.classplusseekho.ui.theme.ClassplusSeekhoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClassplusSeekhoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

sealed class BottomNavItem(val route: String, val icon: Int, val label: String, val badgeCount: Int = 0) {
    object Home : BottomNavItem("home", android.R.drawable.ic_menu_view, "Home")
    object New : BottomNavItem("new", android.R.drawable.ic_menu_recent_history, "New", badgeCount = 43)
    object Library : BottomNavItem("library", android.R.drawable.ic_menu_agenda, "My Library")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val items = listOf(BottomNavItem.Home, BottomNavItem.New, BottomNavItem.Library)
    
    Scaffold(
        bottomBar = {
            NavigationBar {
                val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
                items.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            if (item.badgeCount > 0) {
                                BadgedBox(badge = { Badge { Text(item.badgeCount.toString()) } }) {
                                    Icon(painterResource(id = item.icon), contentDescription = item.label)
                                }
                            } else {
                                Icon(painterResource(id = item.icon), contentDescription = item.label)
                            }
                        },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Home.route) {
                HomeScreen(
                    onCourseClick = { courseId ->
                        navController.navigate("course/$courseId")
                    },
                    navController = navController
                )
            }
            composable(BottomNavItem.New.route) {
                // Placeholder for New screen
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("New Screen", style = MaterialTheme.typography.headlineMedium)
                }
            }
            composable(BottomNavItem.Library.route) {
                // Placeholder for My Library screen
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("My Library", style = MaterialTheme.typography.headlineMedium)
                }
            }
            // Keep the rest of your navigation (search, category, course details) outside the bottom nav
            composable("search") {
                SearchScreen(
                    onBackClick = { navController.navigateUp() },
                    onSearchResult = { query ->
                        navController.navigate("category/$query") {
                            popUpTo(BottomNavItem.Home.route)
                        }
                    }
                )
            }
            composable(
                route = "category/{categoryName}",
                arguments = listOf(navArgument("categoryName") { type = NavType.StringType })
            ) { backStackEntry ->
                val categoryName = backStackEntry.arguments?.getString("categoryName") ?: ""
                CategoryListingScreen(
                    categoryName = categoryName,
                    onBackClick = { navController.navigateUp() },
                    onCourseClick = { courseId ->
                        navController.navigate("course/$courseId")
                    }
                )
            }
            composable(
                route = "course/{courseId}",
                arguments = listOf(navArgument("courseId") { type = NavType.StringType })
            ) { backStackEntry ->
                val courseId = backStackEntry.arguments?.getString("courseId") ?: ""
                CourseDetailsScreen(
                    courseId = courseId,
                    onBackClick = { navController.navigateUp() }
                )
            }
        }
    }
}