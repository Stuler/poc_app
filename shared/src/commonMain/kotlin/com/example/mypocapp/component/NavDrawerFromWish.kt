package com.example.mypocapp.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

///////// Custom Navigation Drawer ///////////
@Composable
fun NavDrawerFromWish(
    isOpen: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = isOpen,
        enter = slideInHorizontally(
            animationSpec = tween(durationMillis = 300),
            initialOffsetX = { -it }
        ),
        exit = slideOutHorizontally(
            animationSpec = tween(durationMillis = 300),
            targetOffsetX = { -it }
        ),
        modifier = Modifier
            .padding(end = 200.dp, bottom = 100.dp)
    ) {
        Row(
            modifier = modifier
                .clip(
                    RoundedCornerShape(
                        topEnd = 30.dp,
                        bottomEnd = 30.dp
                    )
                )
                .background(color = Color.LightGray)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            content()
        }
    }
}