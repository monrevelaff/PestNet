package com.example.pestmanagementapp.ui.library

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pestmanagementapp.data.models.PestInfo


@Composable
fun PestLibraryContent(
    pests: List<PestInfo>,
    navigateToDetailInfo: (PestInfo) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(pests) { pest ->
            PestListItem(
                pest = pest,
                onNavigate = { navigateToDetailInfo(pest) }
            )
        }
    }
}