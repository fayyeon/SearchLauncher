package com.searchlauncher.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val PureBlackColorScheme = darkColorScheme(
    primary = PureWhite,
    onPrimary = PureBlack,
    surface = PureBlack,
    onSurface = PureWhite,
    surfaceContainer = PureDarkSurfaceContainer,
    surfaceContainerHigh = PureDarkSearchBar,
    surfaceContainerHighest = PureDarkSurfaceContainerHighest,
    onSurfaceVariant = Color(0xFFA0A0A0),
    background = PureBlack,
    onBackground = PureWhite
)

@Composable
fun SearchLauncherTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = PureBlackColorScheme,
        typography = SystemTypography,
        content = content
    )
}
