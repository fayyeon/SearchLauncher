package com.searchlauncher.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.searchlauncher.R
import com.searchlauncher.service.LockScreenService
import com.searchlauncher.ui.components.AppListItem
import com.searchlauncher.ui.components.LauncherBottomBar

/**
 * Main Screen for Search Launcher:
 * - Pure AMOLED Black background.
 * - Shows nothing by default except the elongated bottom search bar.
 * - Matching apps appear directly above the search bar when searched.
 * - Enter key launches the top matching app immediately.
 */
@Composable
fun LauncherScreen(
    viewModel: LauncherViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val filteredApps by viewModel.filteredApps.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    val listState = rememberLazyListState()

    // Scroll to top when search query updates
    LaunchedEffect(searchQuery) {
        if (searchQuery.isNotEmpty()) {
            listState.scrollToItem(0)
        }
    }

    // Back button clears search
    BackHandler(enabled = searchQuery.isNotEmpty()) {
        viewModel.clearSearch()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        LockScreenService.lockScreen(context)
                    }
                )
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            // Main content area
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onDoubleTap = {
                                LockScreenService.lockScreen(context)
                            }
                        )
                    }
            ) {
                if (isLoading) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                } else if (searchQuery.isNotEmpty() && filteredApps.isEmpty()) {
                    // Empty search result state
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.no_apps_found),
                            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
                            fontFamily = FontFamily.Default,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center
                        )
                    }
                } else if (searchQuery.isNotEmpty()) {
                    // Search results anchored directly above the search bar
                    LazyColumn(
                        state = listState,
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Bottom,
                        contentPadding = PaddingValues(bottom = 12.dp)
                    ) {
                        items(
                            items = filteredApps,
                            key = { "${it.packageName}/${it.activityName}" }
                        ) { app ->
                            AppListItem(app = app)
                        }
                    }
                }
                // When searchQuery is empty: Pure black, no apps shown!
            }

            // Elongated bottom search bar (full width)
            LauncherBottomBar(
                query = searchQuery,
                onQueryChange = viewModel::onSearchQueryChanged,
                onSearchAction = {
                    viewModel.launchFirstMatch(context)
                },
                modifier = Modifier
                    .navigationBarsPadding()
                    .imePadding()
            )
        }
    }
}
