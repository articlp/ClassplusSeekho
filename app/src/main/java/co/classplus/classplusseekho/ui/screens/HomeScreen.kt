package co.classplus.classplusseekho.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.classplus.classplusseekho.ui.components.*
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onCourseClick: (String) -> Unit = {},
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    val context = LocalContext.current
    // All categories (for bottom sheet)
    val allCategories = remember {
        listOf(
            CategoryItem("Business", Icons.Default.Star, Color(0xFF4FC3F7)),
            CategoryItem("Mobile Tricks", Icons.Default.MailOutline, Color(0xFF9575CD)),
            CategoryItem("Devotion", Icons.Default.AddCircle, Color(0xFFFFB74D)),
            CategoryItem("Share Market", Icons.Default.Check, Color(0xFFBA68C8)),
            CategoryItem("Instagram", Icons.Default.Call, Color(0xFFF06292)),
            CategoryItem("PartTime", Icons.Default.AccountCircle, Color(0xFF81C784)),
            CategoryItem("Sarkari Kaam", Icons.Default.Face, Color(0xFFFF8A65)),
            CategoryItem("View All", Icons.Default.Phone, Color(0xFF90A4AE)),
            CategoryItem("Self-Growth", Icons.Default.Face, Color(0xFFFFEB3B)),
            CategoryItem("English", Icons.Default.AccountCircle, Color(0xFFFFC107)),
            CategoryItem("Photography", Icons.Default.MailOutline, Color(0xFF80DEEA)),
            CategoryItem("Astrology", Icons.Default.Home, Color(0xFFAB47BC)),
            CategoryItem("Gaming", Icons.Default.FavoriteBorder, Color(0xFFEF5350)),
            CategoryItem("Youtube", Icons.Default.PlayArrow, Color(0xFFD32F2F)),
            CategoryItem("Finance", Icons.Default.Person, Color(0xFF388E3C)),
            CategoryItem("Wellness", Icons.Default.Favorite, Color(0xFF8BC34A)),
            CategoryItem("Career", Icons.Default.ShoppingCart, Color(0xFF1976D2)),
            CategoryItem("Editing", Icons.Default.Edit, Color(0xFF7E57C2)),
            CategoryItem("Success", Icons.Default.ThumbUp, Color(0xFFFFA000)),
            CategoryItem("Health", Icons.Default.Favorite, Color(0xFFE57373)),
            CategoryItem("Crime", Icons.Default.ShoppingCart, Color(0xFFB71C1C)),
            CategoryItem("Horror", Icons.Default.ShoppingCart, Color(0xFF616161)),
            CategoryItem("Food", Icons.Default.ShoppingCart, Color(0xFFFF7043)),
            CategoryItem("Agriculture", Icons.Default.ShoppingCart, Color(0xFF388E3C)),
            CategoryItem("Marketing", Icons.Default.ShoppingCart, Color(0xFF0288D1)),
            CategoryItem("Automobile", Icons.Default.ShoppingCart, Color(0xFF607D8B)),
            CategoryItem("Startups", Icons.Default.ShoppingCart, Color(0xFF43A047)),
            CategoryItem("Dating", Icons.Default.FavoriteBorder, Color(0xFFE91E63)),
            CategoryItem("History", Icons.Default.ShoppingCart, Color(0xFF6D4C41)),
            CategoryItem("AI", Icons.Default.ShoppingCart, Color(0xFFFFC107)),
            CategoryItem("Beauty", Icons.Default.Face, Color(0xFFF06292)),
            CategoryItem("Govt Job", Icons.Default.ShoppingCart, Color(0xFF607D8B)),
            CategoryItem("Exam Prep", Icons.Default.ShoppingCart, Color(0xFF1976D2)),
            CategoryItem("Computer", Icons.Default.ShoppingCart, Color(0xFF0288D1)),
            CategoryItem("Coding", Icons.Default.ShoppingCart, Color(0xFF43A047)),
            CategoryItem("Life Hacks", Icons.Default.ShoppingCart, Color(0xFFFFEB3B)),
            CategoryItem("Facebook", Icons.Default.ShoppingCart, Color(0xFF1976D2)),
            CategoryItem("Tech", Icons.Default.ShoppingCart, Color(0xFF607D8B)),
            CategoryItem("Fitness & Gym", Icons.Default.ShoppingCart, Color(0xFF43A047)),
            CategoryItem("Motivation", Icons.Default.ShoppingCart, Color(0xFFFFA000)),
            CategoryItem("Sports", Icons.Default.ShoppingCart, Color(0xFF0288D1)),
            CategoryItem("Commodity", Icons.Default.ShoppingCart, Color(0xFF8D6E63)),
            CategoryItem("Yoga", Icons.Default.ShoppingCart, Color(0xFF81C784)),
            CategoryItem("Real Estate", Icons.Default.Home, Color(0xFF795548)),
            CategoryItem("Communicatio", Icons.Default.ShoppingCart, Color(0xFF607D8B)),
        )
    }
    val categories = allCategories.take(8).toList()
    var showAllCategories by remember { mutableStateOf(false) }
    // Mock data for each section
    val topVideos = listOf(
        CourseInfo("1", "5 MISTAKES OF INTRADAY TRADE", "Stock Market Trades", "", "5m", 4.5f, "Free"),
        CourseInfo("2", "NIFTY DIRECTION TOOL USE SEEKHO", "Stock Market Trades", "", "4m", 4.7f, "Free"),
        CourseInfo("3", "MERA SAHI SAMAY KAB AYEGA?", "Devotion", "", "6m", 4.8f, "Free"),
        CourseInfo("4", "DUNIYA KA ANT KAB HOGA?", "Devotion", "", "3m", 4.2f, "Free"),
        CourseInfo("5", "FREE TOLL TAX-NEW RULES 2025", "Govt. Tricks", "", "7m", 4.6f, "Free"),
        CourseInfo("6", "TRAIN LATE HONE PAR FULL REFUND", "Mobile Tricks", "", "8m", 4.9f, "Free")
    )
    val business = listOf(
        CourseInfo("7", "ECOMMERCE SELLER KI 5 GALTIYA", "Business", "", "5m", 4.5f, "Free"),
        CourseInfo("8", "LEGGINGS SE BANAYI 500CR KI COMPANY", "Women Boss Business", "", "4m", 4.7f, "Free"),
        CourseInfo("9", "INDIAN DROPSHIPPING PROFIT KAM KYUN?", "Shop Ship Pro Guide", "", "6m", 4.8f, "Free")
    )
    val mobileTricks = listOf(
        CourseInfo("10", "TRAIN LATE HONE PAR FULL REFUND", "Mobile Tricks", "", "5m", 4.5f, "Free"),
        CourseInfo("11", "GET PENSION WITHOUT JOB", "Mobile Tricks", "", "4m", 4.7f, "Free"),
        CourseInfo("12", "TRAIN LOST LUGGAGE IN 2 DAYS", "Mobile Tricks", "", "6m", 4.8f, "Free")
    )
    val devotion = listOf(
        CourseInfo("13", "DUNIYA KA ANT KAB HOGA?", "Devotion", "", "3m", 4.2f, "Free"),
        CourseInfo("14", "DIL AUR DIMAAG - KISKI SUNE?", "Devotion", "", "8m", 4.9f, "Free")
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF181818)),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        // Top bar
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // PLUS badge
                Box(
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.small)
                        .background(
                            Brush.horizontalGradient(
                                listOf(Color(0xFFFFD600), Color(0xFFFFA000))
                            )
                        )
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text("PLUS", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                }
                // Profile icon
                IconButton(onClick = {
                    Toast.makeText(context, "Profile clicked", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(
                        Icons.Default.AccountCircle,
                        contentDescription = "Profile",
                        tint = Color(0xFFFFD600),
                        modifier = Modifier
                            .size(36.dp)
                            .clip(MaterialTheme.shapes.extraLarge)
                    )
                }
            }
        }
        // Search bar
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clickable { navController.navigate("search") }
            ) {
                SearchBar(
                    query = searchQuery,
                    onQueryChange = { searchQuery = it },
                    onSearch = { navController.navigate("search") },
                    hint = "Mudra Loan"
                )
            }
        }
        // Categories
        item {
            CategoryGrid(
                categories = categories,
                onViewAll = { showAllCategories = true }
            )
        }
        // Top Videos section
        item {
            SectionWithGrid(
                title = "Top Videos",
                data = topVideos,
                onCardClick = onCourseClick,
                onViewAll = { /* TODO: Implement view all */ }
            )
        }
        // Business section
        item {
            SectionWithGrid(
                title = "Business",
                data = business,
                onCardClick = onCourseClick,
                onViewAll = { /* TODO: Implement view all */ }
            )
        }
        // Mobile Tricks section
        item {
            SectionWithGrid(
                title = "Mobile Tricks",
                data = mobileTricks,
                onCardClick = onCourseClick,
                onViewAll = { /* TODO: Implement view all */ }
            )
        }
        // Devotion section
        item {
            SectionWithGrid(
                title = "Devotion",
                data = devotion,
                onCardClick = onCourseClick,
                onViewAll = { /* TODO: Implement view all */ }
            )
        }
    }

    // Debug log for bottoms sheet state
    LaunchedEffect(showAllCategories) {
        println("Bottom sheet visibility: $showAllCategories")
    }
    
    // Modal Bottom Sheet for all categories
    if (showAllCategories) {
        Toast.makeText(context, "Showing bottom sheet", Toast.LENGTH_SHORT).show()
        ModalBottomSheet(
            onDismissRequest = { 
                showAllCategories = false 
                Toast.makeText(context, "Bottom sheet dismissed", Toast.LENGTH_SHORT).show()
            },
            containerColor = Color(0xFF181818),
            dragHandle = null
        ) {
            Box(Modifier.fillMaxWidth()) {
                IconButton(
                    onClick = { 
                        showAllCategories = false 
                        Toast.makeText(context, "Bottom sheet closed", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                ) {
                    Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White)
                }
            }
            Spacer(Modifier.height(8.dp))
            Text(
                "All Categories",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.heightIn(max = 600.dp)
            ) {
                items(allCategories) { cat ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .clickable {
                                Toast.makeText(context, cat.name, Toast.LENGTH_SHORT).show()
                                showAllCategories = false
                            }
                    ) {
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .background(cat.color, shape = MaterialTheme.shapes.large),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(cat.icon, contentDescription = cat.name, tint = Color.White, modifier = Modifier.size(32.dp))
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(cat.name, color = Color.White, fontSize = 13.sp)
                    }
                }
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
fun SectionWithGrid(
    title: String,
    data: List<CourseInfo>,
    onCardClick: (String) -> Unit,
    onViewAll: (() -> Unit)? = null
) {
    Column(Modifier.fillMaxWidth()) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text(title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
            if (onViewAll != null) {
                TextButton(onClick = onViewAll) {
                    Text("View all", color = Color(0xFFFFD600), fontSize = 14.sp)
                }
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 4.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.heightIn(max = 300.dp)
        ) {
            items(data) { course ->
                SeekhoStyleCourseCard(courseInfo = course, onClick = { onCardClick(course.id) })
            }
        }
    }
}

data class CategoryItem(val name: String, val icon: androidx.compose.ui.graphics.vector.ImageVector, val color: Color)

@Composable
fun CategoryGrid(categories: List<CategoryItem>, onViewAll: (() -> Unit)? = null) {
    val context = LocalContext.current
    Column(Modifier.padding(horizontal = 8.dp)) {
        for (row in 0 until 2) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (col in 0 until 4) {
                    val index = row * 4 + col
                    if (index < categories.size) {
                        val cat = categories[index]
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .clickable { 
                                    if (cat.name == "View All" && onViewAll != null) {
                                        Toast.makeText(context, "Opening all categories...", Toast.LENGTH_SHORT).show()
                                        onViewAll()
                                    } else {
                                        Toast.makeText(context, "Selected category: ${cat.name}", Toast.LENGTH_SHORT).show()
                                    }
                                }
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(56.dp)
                                    .background(cat.color, shape = MaterialTheme.shapes.large),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(cat.icon, contentDescription = cat.name, tint = Color.White, modifier = Modifier.size(32.dp))
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(cat.name, color = Color.White, fontSize = 13.sp)
                        }
                    } else {
                        Spacer(modifier = Modifier.size(56.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun SeekhoStyleCourseCard(courseInfo: CourseInfo, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color(0xFF232323)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(Modifier.fillMaxSize()) {
            // Colorful background for category
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(32.dp)
                    .background(Color(0xFF9575CD)),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = courseInfo.instructor,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(start = 12.dp)
                )
            }
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(top = 36.dp, start = 12.dp, end = 12.dp, bottom = 12.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = courseInfo.title,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = courseInfo.duration,
                        color = Color(0xFFB0BEC5),
                        fontSize = 12.sp
                    )
                    Text(
                        text = courseInfo.price,
                        color = Color(0xFFFFD600),
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }
            }
        }
    }
} 