package com.maxidev.tonotedolist.ui.presentation.screen.notescreen

import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.maxidev.tonotedolist.R
import com.maxidev.tonotedolist.ui.components.RowOfButtons
import com.maxidev.tonotedolist.ui.presentation.viewmodel.NoteViewModel
import com.maxidev.tonotedolist.utils.Constants.TOAST_TEXT_SAVED

@Composable
fun NoteAdd(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    viewModel: NoteViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                stringResource(id = R.string.write_your_note),
                style = MaterialTheme.typography.titleMedium
            )
        }
        ColumnOfTextFields(
            titleValue = viewModel.state.noteTitleInput.value,
            onTitleChange = { title ->
                viewModel.onTitleChange(title)
            },
            bodyValue = viewModel.state.noteBodyInput.value,
            onBodyChange = { body ->
                viewModel.onBodyChange(body)
            }
        )
        RowOfButtons(
            buttonText = R.string.save,
            onSave = {
                viewModel.insert()
                Toast.makeText(context, TOAST_TEXT_SAVED, LENGTH_SHORT).show()
                navigateBack()
            },
            onCancel = { navigateBack() }
        )
    }
}

@Composable
private fun ColumnOfTextFields(
    modifier: Modifier = Modifier,
    titleValue: String,
    onTitleChange: (String) -> Unit,
    bodyValue: String,
    onBodyChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        OutlinedTextField(
            value = titleValue,
            onValueChange = onTitleChange,
            label = {
                Text(
                    text = stringResource(id = R.string.title),
                    style = MaterialTheme.typography.labelSmall
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }),
            modifier = Modifier
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = bodyValue,
            onValueChange = onBodyChange,
            label = {
                Text(
                    text = stringResource(id = R.string.write_your_note),
                    style = MaterialTheme.typography.labelSmall
                )
            },
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Justify),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
            }),
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        )
    }
}