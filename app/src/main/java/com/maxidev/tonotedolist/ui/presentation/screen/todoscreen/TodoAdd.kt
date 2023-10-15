package com.maxidev.tonotedolist.ui.presentation.screen.todoscreen

import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.maxidev.grocerylist.R
import com.maxidev.tonotedolist.ui.components.RowOfButtons
import com.maxidev.tonotedolist.ui.presentation.viewmodel.TodoViewModel
import com.maxidev.tonotedolist.utils.Constants.TOAST_TEXT_SAVED

/**
 * Component that will be the screen for adding an item to the app's database.
 *
 * @param [viewmodel] bridge that contains the business logic.
 * @param [navigateBack] navigate to the previous screen.
 */
@Composable
fun TodoAdd(
    modifier: Modifier = Modifier,
    viewmodel: TodoViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val context = LocalContext.current

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
        TodoTextField(
            value = viewmodel.uiState.userInput.value,
            onValueChange = { input ->
                viewmodel.onValueChange(input)
            }
        )
        Spacer(modifier = Modifier.height(25.dp))
        RowOfButtons(
            buttonText = R.string.save,
            onSave = {
                viewmodel.insert()
                Toast.makeText(context, TOAST_TEXT_SAVED, LENGTH_SHORT).show()
                navigateBack()
            },
            onCancel = { navigateBack() }
        )
    }
}

@Composable
private fun TodoTextField(
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