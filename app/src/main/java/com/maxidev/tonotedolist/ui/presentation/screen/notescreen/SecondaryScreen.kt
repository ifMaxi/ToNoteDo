package com.maxidev.tonotedolist.ui.presentation.screen.notescreen

import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
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
import com.maxidev.tonotedolist.data.db.entities.NoteEntity
import com.maxidev.tonotedolist.ui.components.AppFloatingButton
import com.maxidev.tonotedolist.ui.components.AppTopBar
import com.maxidev.tonotedolist.ui.presentation.viewmodel.NoteViewModel
import com.maxidev.tonotedolist.utils.Constants.TOAST_TEXT_DELETE

@Composable
fun SecondaryScreen(
    modifier: Modifier = Modifier,
    viewModel: NoteViewModel = hiltViewModel(),
    onNavigate: () -> Unit,
    navigateToUpdate: (NoteEntity) -> Unit
) {
    val vm = viewModel.homeState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            AppTopBar(text = R.string.note)
        },
        floatingActionButton = {
            AppFloatingButton(
                onClick = { onNavigate() },
                icon = Icons.Default.Add
            )
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        ScreenContent(
            onDelete = {
                viewModel.delete(it)
                Toast.makeText(context, TOAST_TEXT_DELETE, LENGTH_SHORT).show()
            },
            onEdit = { navigateToUpdate(it) },
            notes = vm.value.listOfNote,
            modifier = modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun ScreenContent(
    modifier: Modifier = Modifier,
    onDelete: (NoteEntity) -> Unit,
    onEdit: (NoteEntity) -> Unit,
    notes: List<NoteEntity>
) {
    val listGridState = rememberLazyGridState()

    LazyHorizontalGrid(
        modifier = modifier.fillMaxSize(),
        rows = GridCells.Adaptive(minSize = 180.dp),
        state = listGridState,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(20.dp)
    ) {
        items(notes) { note ->
            NoteItem(
                onDelete = { onDelete(note) },
                onEdit = { onEdit(note) },
                note = note,
            )
        }
    }
}