package com.maxidev.grocerylist.ui.grocery.presentation.screen.groceryscreen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.maxidev.grocerylist.R
import com.maxidev.grocerylist.data.db.entities.GroceryEntity
import com.maxidev.grocerylist.ui.components.AppDialog
import com.maxidev.grocerylist.ui.components.AppFloatingButton
import com.maxidev.grocerylist.ui.components.AppTopBar
import com.maxidev.grocerylist.ui.components.SmallAppFloatingButton
import com.maxidev.grocerylist.ui.grocery.presentation.viewmodel.GroceryViewModel
import com.maxidev.grocerylist.utils.Constants

/**
 * Main screen of the app.
 * It will show all the items contained in the database.
 * It will also have the functionality of being able to delete all items if desired.
 */
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: GroceryViewModel = hiltViewModel(),
    onNavigate: () -> Unit
) {
    val vm = viewModel.homeState.collectAsStateWithLifecycle()
    val openDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            AppTopBar(text = R.string.grocery)
        },
        floatingActionButton = {
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                SmallAppFloatingButton(
                    onClick = { openDialog.value = true },
                    icon = Icons.Default.DeleteSweep,
                    modifier = Modifier.align(Alignment.End)
                )
                AppFloatingButton(
                    onClick = { onNavigate() },
                    icon = Icons.Default.Add
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        ScreenContent(
            modifier = modifier.padding(paddingValues),
            onDelete = {
                viewModel.delete(it)
                Toast.makeText(context, Constants.TOAST_TEXT_DELETE, Toast.LENGTH_SHORT).show()
            },
//            onDeleteAll = {
//                openDialog.value = true
//            },
            lazyList = vm.value.listOfGrocery
        )
    }

    if (openDialog.value) {
        AppDialog(
            onDismissDialog = { openDialog.value = false },
            onConfirmation = {
                viewModel.deleteAll()
                openDialog.value = false
                Toast.makeText(context, Constants.TOAST_TEXT_DELETE_ALL, Toast.LENGTH_SHORT).show()
            },
            onDismiss = { openDialog.value = false },
            dialogTitle = R.string.are_you_sure,
            dialogText = R.string.delete_this
        )
    }
}

@Composable
private fun ScreenContent(
    modifier: Modifier = Modifier,
    onDelete: (GroceryEntity) -> Unit,
    //onDeleteAll: () -> Unit,
    lazyList: List<GroceryEntity>
) {
    val listState = rememberLazyListState()

    Column(
        modifier = modifier
    ) {
        //DeleteAllButton(onDeleteAll = onDeleteAll)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 15.dp),
            state = listState,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            items(lazyList) { grocery ->
                GroceryItem(
                    onDelete = { onDelete(grocery) },
                    grocery = grocery.grocery
                )
            }
        }
    }
}

//@Composable
//private fun DeleteAllButton(
//    modifier: Modifier = Modifier,
//    onDeleteAll: () -> Unit
//) {
//    Row(
//        modifier = modifier
//            .fillMaxWidth()
//            .padding(top = 6.dp, bottom = 6.dp, start = 6.dp, end = 20.dp),
//        horizontalArrangement = Arrangement.End
//    ) {
//        AppButton(
//            text = R.string.delete_all,
//            onClick = onDeleteAll,
//            modifier = Modifier.width(150.dp)
//        )
//    }
//}