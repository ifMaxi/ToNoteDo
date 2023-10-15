package com.maxidev.tonotedolist.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

/**
 * Reusable button, it will contain different functionalities according to need.
 *
 * @param [text] the button text.
 * @param [onClick] lambda method that will receive different functionalities.
 */
@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    @StringRes text: Int,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        elevation = ButtonDefaults.buttonElevation(8.dp),
        modifier = modifier.width(140.dp)
    ) {
        Text(
            text = stringResource(id = text),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}