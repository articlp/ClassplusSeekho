package co.classplus.classplusseekho.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseDetailsScreen(
    courseId: String,
    onBackClick: () -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    // In a real app, you would fetch these details based on courseId
    val courseDetails = remember {
        CourseDetails(
            id = courseId,
            title = "Complete Android Development",
            instructor = "John Doe",
            thumbnailUrl = "https://example.com/android.jpg",
            duration = "20 hours",
            rating = 4.5f,
            price = "₹999",
            description = "Master Android app development with Kotlin and Jetpack Compose. Learn to build modern, production-ready Android applications.",
            totalLectures = 150,
            totalStudents = 10000,
            lastUpdated = "March 2024"
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Course Details") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Share functionality */ }) {
                        Icon(Icons.Default.Share, contentDescription = "Share")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    AsyncImage(
                        model = courseDetails.thumbnailUrl,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    // Play button overlay
                    IconButton(
                        onClick = {
                            val videoUrl = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"
                            val encodedUrl = java.net.URLEncoder.encode(videoUrl, "UTF-8")
                            navController.navigate("video/$encodedUrl")
                        },
                        modifier = Modifier
                            .size(64.dp)
                            .align(Alignment.Center)
                    ) {
                        Icon(
                            Icons.Default.CheckCircle,
                            contentDescription = "Play Preview",
                            modifier = Modifier.size(64.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = courseDetails.title,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = "By ${courseDetails.instructor}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        CourseStatItem(
                            value = courseDetails.totalLectures.toString(),
                            label = "Lectures"
                        )
                        CourseStatItem(
                            value = courseDetails.duration,
                            label = "Duration"
                        )
                        CourseStatItem(
                            value = "${courseDetails.rating}",
                            label = "Rating"
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Text(
                        text = "About this course",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = courseDetails.description,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    Button(
                        onClick = { /* Enroll functionality */ },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Enroll Now - ${courseDetails.price}")
                    }
                }
            }
        }
    }
}

@Composable
private fun CourseStatItem(
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

data class CourseDetails(
    val id: String,
    val title: String,
    val instructor: String,
    val thumbnailUrl: String,
    val duration: String,
    val rating: Float,
    val price: String,
    val description: String,
    val totalLectures: Int,
    val totalStudents: Int,
    val lastUpdated: String
) 