package com.maxidev.grocerylist.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maxidev.grocerylist.R
import com.maxidev.grocerylist.ui.theme.GroceryListTheme

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    @StringRes text: Int,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        elevation = ButtonDefaults.buttonElevation(8.dp),
        modifier = modifier.width(120.dp)
    ) {
        Text(
            text = stringResource(id = text),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun ButtonPreview() {
    GroceryListTheme {
        AppButton(
            text = R.string.cancel,
            onClick = {}
        )
    }
}