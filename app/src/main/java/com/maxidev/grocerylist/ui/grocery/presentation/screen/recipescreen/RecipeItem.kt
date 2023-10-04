package com.maxidev.grocerylist.ui.grocery.presentation.screen.recipescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maxidev.grocerylist.data.db.entities.RecipeEntity
import com.maxidev.grocerylist.ui.theme.GroceryListTheme

@Composable
fun RecipeItem(
    modifier: Modifier = Modifier,
    onDelete: () -> Unit,
    onEdit: () -> Unit,
    recipeTitle: RecipeEntity
) {
    ItemCard(
        modifier = modifier,
        onClick = { onEdit() },
        onClickIcon = { onDelete() },
        title = recipeTitle.recipeTitle
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ItemCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onClickIcon: () -> Unit,
    title: String
) {
    OutlinedCard(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // IMAGE
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Recipe: $title",
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .width(200.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = onClickIcon) {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = "Delete"
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CardPreview() {
    GroceryListTheme {
        ItemCard(onClick = {}, title = "Hello", onClickIcon = {})
    }
}