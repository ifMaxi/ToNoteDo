package com.maxidev.grocerylist.ui.components

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun AppFloatingButton(
    modifier: Modifier = Modifier,
    onAdd: () -> Unit,
    icon: ImageVector
) {
    FloatingActionButton(
        onClick = { onAdd() },
        elevation = FloatingActionButtonDefaults.elevation(6.dp),
        modifier = modifier
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
    }
}