package com.maxidev.grocerylist.ui.components

import androidx.annotation.StringRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

/**
 * Reusable Top bar app.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    modifier: Modifier = Modifier,
    @StringRes text: Int
) {
    TopAppBar(
        title = { 
            Text(
                text = stringResource(id = text),
                style = MaterialTheme.typography.titleLarge
            )
        },
        scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
        modifier = modifier
    )
}