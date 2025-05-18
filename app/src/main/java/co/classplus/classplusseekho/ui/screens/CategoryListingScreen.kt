package co.classplus.classplusseekho.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.classplus.classplusseekho.ui.components.CourseCard
import co.classplus.classplusseekho.ui.components.CourseInfo
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryListingScreen(
    categoryName: String,
    onBackClick: () -> Unit,
    onCourseClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    // Mock data for courses in this category
    val courses = remember {
        listOf(
            CourseInfo(
                id = "1",
                title = "Advanced $categoryName Masterclass",
                instructor = "John Expert",
                thumbnailUrl = "https://example.com/course1.jpg",
                duration = "25h",
                rating = 4.7f,
                price = "₹2499"
            ),
            CourseInfo(
                id = "2",
                title = "Complete $categoryName Bootcamp 2024",
                instructor = "Sarah Pro",
                thumbnailUrl = "https://example.com/course2.jpg",
                duration = "30h",
                rating = 4.9f,
                price = "₹3999"
            ),
            CourseInfo(
                id = "3",
                title = "$categoryName for Beginners",
                instructor = "Mike Teacher",
                thumbnailUrl = "https://example.com/course3.jpg",
                duration = "15h",
                rating = 4.5f,
                price = "₹1499"
            ),
            CourseInfo(
                id = "4",
                title = "Professional $categoryName Projects",
                instructor = "Emily Code",
                thumbnailUrl = "https://example.com/course4.jpg",
                duration = "20h",
                rating = 4.6f,
                price = "₹2999"
            )
        )
    }

    var showFilterDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                title = {
                    Text(
                        categoryName,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                actions = {
                    IconButton(onClick = { showFilterDialog = true }) {
                        Icon(Icons.Default.List, contentDescription = "Sort")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Category Stats
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        "${courses.size} Courses",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "in $categoryName",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                
                ElevatedFilterChip(
                    selected = false,
                    onClick = { showFilterDialog = true },
                    label = { Text("Sort by") },
                    leadingIcon = {
                        Icon(
                            Icons.Default.List,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                )
            }

            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(courses) { course ->
                    CourseCard(
                        courseInfo = course,
                        onClick = { onCourseClick(course.id) }
                    )
                }
            }
        }
    }

    if (showFilterDialog) {
        AlertDialog(
            onDismissRequest = { showFilterDialog = false },
            title = { Text("Sort Courses") },
            text = {
                Column {
                    ListItem(
                        headlineContent = { Text("Most Popular") },
                        leadingContent = {
                            RadioButton(
                                selected = true,
                                onClick = { showFilterDialog = false }
                            )
                        }
                    )
                    ListItem(
                        headlineContent = { Text("Price: Low to High") },
                        leadingContent = {
                            RadioButton(
                                selected = false,
                                onClick = { showFilterDialog = false }
                            )
                        }
                    )
                    ListItem(
                        headlineContent = { Text("Price: High to Low") },
                        leadingContent = {
                            RadioButton(
                                selected = false,
                                onClick = { showFilterDialog = false }
                            )
                        }
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = { showFilterDialog = false }) {
                    Text("Apply")
                }
            },
            dismissButton = {
                TextButton(onClick = { showFilterDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
} 