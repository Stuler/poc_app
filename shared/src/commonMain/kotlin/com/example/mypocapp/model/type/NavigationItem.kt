package com.example.mypocapp.model.type

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val index: Int,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)
