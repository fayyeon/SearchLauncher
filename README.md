# Search Launcher

A ultra-minimalist, search-first Android launcher written in **Kotlin** and **Jetpack Compose**.

By default, **Search Launcher** displays nothing but a pure AMOLED black screen and an elongated bottom search bar. Apps appear directly above the search bar as you search for them, putting the exact app you want right at your thumb.

---

## 🌟 Key Features

1. **Search-First Philosophy**:
   - By default, the screen is clean pure black with only the search bar at the bottom.
   - Apps only show up in real-time when you type what you're looking for.
   - Pressing **Enter** on the keyboard instantly launches the first match.
2. **Bottom-Anchored Reachability**:
   - Matching apps appear directly above the bottom search bar, keeping everything within effortless thumb reach.
3. **Pure AMOLED Black**:
   - Uncompromising battery-saving pure black (`#000000`) background with no background switching or burger menu.
4. **Elongated Full-Width Search Bar**:
   - The 3-dots menu is removed, allowing the search bar to span the full width.
5. **Clean App Icons**:
   - High-contrast app icons with generic Android robot icon fallback for apps without custom logos.
6. **Zero Animation Latency**:
   - Zero spring physics or transitions for instantaneous startup and rendering.
7. **System Font Exclusively**:
   - Honoring the device's system font preference (`FontFamily.Default`).
8. **App Shortcuts**:
   - Tap any app to launch; long-press to open **App Info** or trigger **Uninstall**.

---

## 📁 Project Structure

```
SearchLauncher/
├── app/
│   ├── build.gradle.kts
│   ├── src/main/
│   │   ├── AndroidManifest.xml
│   │   ├── java/com/searchlauncher/
│   │   │   ├── MainActivity.kt               # Package monitor & edge-to-edge
│   │   │   ├── data/
│   │   │   │   └── AppModel.kt               # App data model, launch, & uninstall
│   │   │   └── ui/
│   │   │       ├── LauncherViewModel.kt      # Querying apps, sorting, search-only filter
│   │   │       ├── LauncherScreen.kt         # Pure black UI, bottom search bar & reachability
│   │   │       ├── components/
│   │   │       │   ├── AppListItem.kt        # App icon & title item with long-press menu
│   │   │       │   └── SearchBarView.kt      # Elongated full-width search bar
│   │   │       └── theme/
│   │   │           ├── Color.kt              # Pure black AMOLED palette
│   │   │           ├── Type.kt               # System font typography
│   │   │           └── Theme.kt              # SearchLauncherTheme
│   │   └── res/
│   │       ├── values/themes.xml             # Pure black window background
│   │       └── values-night/themes.xml
```

---

## 🚀 How to Build and Run

### 1. In Android Studio
1. Open Android Studio.
2. Select **Open** and choose the `SearchLauncher` directory.
3. Allow Gradle to sync.
4. Run on an Android device or emulator running **Android 8.0+ (API 26+)**.

### 2. Via Command Line
```bash
cd SearchLauncher
./gradlew assembleDebug
```
The output APK will be generated at:
`app/build/outputs/apk/debug/app-debug.apk`

---

## ⚙️ Setting as Default Launcher

1. Open your device's **Settings**.
2. Navigate to **Apps** → **Default apps** → **Home app**.
3. Select **Search Launcher**.
