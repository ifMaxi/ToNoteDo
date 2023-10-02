package com.maxidev.grocerylist.ui.grocery.presentation.screen.recipescreen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maxidev.grocerylist.R
import com.maxidev.grocerylist.data.db.entities.RecipeEntity
import com.maxidev.grocerylist.ui.components.AppFloatingButton
import com.maxidev.grocerylist.ui.components.AppTopBar
import com.maxidev.grocerylist.ui.grocery.presentation.viewmodel.RecipeViewModel
import com.maxidev.grocerylist.utils.Constants.TOAST_TEXT_DELETE

@Composable
fun SecondaryScreen(
    modifier: Modifier = Modifier,
    viewModel: RecipeViewModel = hiltViewModel(),
    onNavigate: () -> Unit,
    navigateToUpdate: () -> Unit
) {
    val vm = viewModel.homeState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            AppTopBar(text = R.string.recipe)
        },
        floatingActionButton = {
            AppFloatingButton(
                onClick = { onNavigate() },
                icon = Icons.Default.Add
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        ScreenContent(
            onDelete = {
                viewModel.delete(it)
                Toast.makeText(context, TOAST_TEXT_DELETE, Toast.LENGTH_SHORT).show()
            },
            onEdit = { navigateToUpdate() },
            recipes = vm.value.listOfRecipe,
            modifier = modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun ScreenContent(
    modifier: Modifier = Modifier,
    onDelete: (RecipeEntity) -> Unit,
    onEdit: (RecipeEntity) -> Unit,
    recipes: List<RecipeEntity>
) {
    val listGridState = rememberLazyGridState()

    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        state = listGridState,
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(recipes) { recipe ->
            RecipeItem(
                onDelete = { onDelete(recipe) },
                onEdit = { onEdit(recipe) },
                recipeTitle = recipe.recipeTitle
            )
        }
    }
}