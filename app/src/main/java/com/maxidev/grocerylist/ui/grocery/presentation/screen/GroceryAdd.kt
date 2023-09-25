package com.maxidev.grocerylist.ui.grocery.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.maxidev.grocerylist.R
import com.maxidev.grocerylist.ui.components.AppButton
import com.maxidev.grocerylist.ui.grocery.presentation.viewmodel.GroceryViewModel

@Composable
fun GroceryAdd(
    modifier: Modifier = Modifier,
    viewmodel: GroceryViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.place_your_item),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(25.dp))
        GroceryTextField(
            value = viewmodel.uiState.userInput.value,
            onValueChange = { input ->
                viewmodel.onValueChange(input)
            }
        )
        Spacer(modifier = Modifier.height(25.dp))
        RowOfButtons(
            onSave = {
                viewmodel.insert()
                navigateBack()
            },
            onCancel = { navigateBack() }
        )
    }
}

@Composable
private fun GroceryTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = stringResource(id = R.string.write_here),
                style = MaterialTheme.typography.labelSmall
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        modifier = modifier
            .fillMaxWidth()
    )
}

@Composable
private fun RowOfButtons(
    modifier: Modifier = Modifier,
    onSave: () -> Unit,
    onCancel: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
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
            text = R.string.save,
            onClick = onSave
        )
    }
}