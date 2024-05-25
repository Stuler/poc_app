package com.example.mypocapp.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pocapp_sharingRes.MR
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun TopBar(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .background(Color.Green) // Barva pozadí rámečku
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(
                MR.images.logo
            ),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp),
            alignment = Alignment.Center
        )
    }
        Text(text = stringResource(MR.strings.welcome_text))
}