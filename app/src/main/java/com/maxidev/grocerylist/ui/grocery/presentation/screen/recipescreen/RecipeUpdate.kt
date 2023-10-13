package com.maxidev.grocerylist.ui.grocery.presentation.screen.recipescreen

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.maxidev.grocerylist.R
import com.maxidev.grocerylist.data.db.entities.RecipeEntity
import com.maxidev.grocerylist.ui.components.RowOfButtons
import com.maxidev.grocerylist.ui.grocery.presentation.viewmodel.RecipeViewModel
import com.maxidev.grocerylist.utils.Constants

@Composable
fun RecipeUpdate(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    viewModel: RecipeViewModel = hiltViewModel(),
    id: Long,
    title: String?,
    body: String?
) {
    val context = LocalContext.current
    var editTitle by remember { mutableStateOf(title) }
    var editBody by remember { mutableStateOf(body) }

    LaunchedEffect(Unit) {
        viewModel.byId(id)
    }

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
                stringResource(id = R.string.edit_your_recipe),
                style = MaterialTheme.typography.titleMedium
            )
        }
        ColumnOfTextFields(
            titleValue = editTitle ?: "",
            onTitleChange = { editTitle = it},
            bodyValue = editBody ?: "",
            onBodyChange = { editBody = it }
        )
        RowOfButtons(
            buttonText = R.string.update,
            onSave = {
                val content = RecipeEntity(
                    id = id,
                    recipeTitle = editTitle ?: "",
                    recipeBody = editBody ?: ""
                )

                viewModel.update(content)
                Toast.makeText(context, Constants.TOAST_TEXT_UPDATE, Toast.LENGTH_SHORT).show()
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
                    text = stringResource(id = R.string.write_your_recipe),
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
                .height(560.dp)
        )
    }
}