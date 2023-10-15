package com.maxidev.tonotedolist.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maxidev.grocerylist.R

@Composable
internal fun RowOfButtons(
    modifier: Modifier = Modifier,
    @StringRes buttonText: Int,
    onSave: () -> Unit,
    onCancel: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        AppButton(
            modifier = modifier,
            text = R.string.cancel,
            onClick = onCancel
        )
        Spacer(modifier = Modifier.width(25.dp))
        AppButton(
            modifier = modifier,
            text = buttonText,
            onClick = onSave
        )
    }
}