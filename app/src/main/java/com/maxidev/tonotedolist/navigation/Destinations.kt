package com.maxidev.tonotedolist.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notes
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.ui.graphics.vector.ImageVector
import com.maxidev.tonotedolist.R

/**
 * This class will contain the destinations of the app with their respective routes.
 *
 * @param [route] will define the destination of the screen
 */
sealed class Destinations(
    val route: String,
    @StringRes val resourceId: Int,
    val icons: ImageVector
) {
    data object ToDoMain: Destinations(
        route = "todo_main",
        resourceId = R.string.todo,
        icons = Icons.Filled.List
    )
    data object ToDoAdd: Destinations(
        route = "todo_add",
        resourceId = R.string.todo_add,
        icons = Icons.Filled.QuestionMark
    )
    data object NoteMain: Destinations(
        route = "note_main",
        resourceId = R.string.note,
        icons = Icons.Filled.Notes
    )
    data object NoteAdd: Destinations(
        route = "note_add",
        resourceId = R.string.note_add,
        icons = Icons.Filled.QuestionMark
    )
    data object NoteUpdate: Destinations(
        route = "note_update",
        resourceId = R.string.note_update,
        icons = Icons.Filled.QuestionMark
    )
}