package com.maxidev.grocerylist.ui.grocery.presentation.screen.groceryscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maxidev.grocerylist.ui.theme.GroceryListTheme

/**
 * Composable component that will be displayed on the screen as text, storing the content itself.
 * In turn, the text of the completed task can be deleted and crossed out as appropriate.
 *
 * @param [onDelete] lambda method that will have the functionality of deleting the item.
 */
@Composable
fun GroceryItem(
    modifier: Modifier = Modifier,
    onDelete: () -> Unit,
    grocery: String
) {
    val checkedState = rememberSaveable { mutableStateOf(false) }
    // The ideal would be to repeat bodyLarge with a different decoration
    // but so that it is not too long and for simplicity use bodySmall
    // with a decorator and copying the same parameters
    val lineThroughTextStyle = if (checkedState.value) {
        MaterialTheme.typography.bodySmall
    } else {
        MaterialTheme.typography.bodyLarge
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checkedState.value,
            onCheckedChange = { checkedState.value = it }
        )
        Text(
            text = grocery,
            style = lineThroughTextStyle,
            modifier = Modifier
                .width(250.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Box {
            IconButton(onClick = { onDelete() }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete"
                )
            }
        }
    }
}

@Preview
@Composable
fun GroceryItemPreview() {
    GroceryListTheme {
        GroceryItem(onDelete = {}, grocery = "Hello")
    }
}