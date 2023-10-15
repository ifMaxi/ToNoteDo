package com.maxidev.tonotedolist.ui.presentation.screen.notescreen

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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.maxidev.tonotedolist.data.db.entities.NoteEntity

@Composable
fun NoteItem(
    modifier: Modifier = Modifier,
    onDelete: () -> Unit,
    onEdit: () -> Unit,
    note: NoteEntity?
) {
    ItemCard(
        modifier = modifier,
        onClick = { onEdit() },
        onClickIcon = { onDelete() },
        title = note?.noteTitle,
        body = note?.noteBody
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ItemCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onClickIcon: () -> Unit,
    title: String?,
    body: String?
) {
    OutlinedCard(
        onClick = onClick,
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.outlinedCardColors(MaterialTheme.colorScheme.surfaceVariant),
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title.toString(),
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.headlineMedium,
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
            Text(
                text = body.toString(),
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .width(230.dp)
            )
        }
    }
}