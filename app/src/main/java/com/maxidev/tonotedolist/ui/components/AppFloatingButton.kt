package com.maxidev.tonotedolist.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.maxidev.tonotedolist.R

/**
 * Reusable FAB. It will have different functionalities as appropriate.
 *
 * @param [onClick] lambda method that will receive different functionalities.
 * @param [icon] the desired icon for the button.
 */
@Composable
fun AppFloatingButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    icon: ImageVector
) {
    ExtendedFloatingActionButton(
        onClick = { onClick() },
        elevation = FloatingActionButtonDefaults.elevation(6.dp),
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.new_item)
        )
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        Icon(
            imageVector = icon,
            contentDescription = null
        )
    }
}

@Composable
fun SmallAppFloatingButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    icon: ImageVector
) {
    SmallFloatingActionButton(
        onClick = { onClick() },
        elevation = FloatingActionButtonDefaults.elevation(6.dp),
        modifier = modifier
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null
        )
    }
}