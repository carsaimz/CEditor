# CEditor - Code Editor

A modern, multi-language code editor for Android built with native Android APIs.

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)

![Build Status](https://github.com/carsaimz/CEditor/actions/workflows/android_build.yml/badge.svg)
![Release Status](https://github.com/carsaimz/CEditor/actions/workflows/android_release.yml/badge.svg)
[![GitHub release](https://img.shields.io/github/v/release/carsaimz/CEditor?style=for-the-badge)](https://github.com/carsaimz/CEditor/releases/latest)
[![GitHub pre-release](https://img.shields.io/github/v/release/carsaimz/CEditor?include_prereleases&style=for-the-badge&label=Pre-Release)](https://github.com/carsaimz/CEditor/releases)

## Features

- **Code Editor**: Sora Editor with syntax highlighting for Java, Kotlin, and more
- **File Manager**: Browse, open, edit, and manage files on your device
- **Multi-Provider AI Assistant**: Integrated AI chat with multiple provider support
- **Language Switching**: English and Portuguese with dynamic switching in settings
- **Dark/Light Theme**: Material Design with customizable themes
- **Permissions Support**: Handles Android 11+ scoped storage and legacy permissions

## AI Providers

CEditor supports multiple AI providers with different pricing tiers:

| Provider | Type | Free Tier | Notes |
|----------|------|-----------|-------|
| Gemini (Google) | Free/Free-tier | Yes (15 RPM) | Best free option |
| OpenAI | Paid | Yes ($5 free credit) | GPT-3.5/4 |
| Anthropic | Paid | Yes ($5 free credit) | Claude |
| Groq | Free | Yes (30 RPM) | Fast inference |
| Mistral | Paid | Yes (€2 free credit) | Open-source models |
| Ollama (Local) | Free | Always free | Self-hosted, no API key needed |

### Priority Order (as configured in settings)

1. **Free providers**: Groq, Ollama (Local)
2. **Free-tier providers**: Gemini, OpenAI, Anthropic, Mistral
3. **Paid premium**: Any provider with full API key

## Download

| Type | Status | Link |
|------|--------|------|
| Debug APK | Auto-built | [Latest Pre-Release](https://github.com/carsaimz/CEditor/releases) |
| Release APK | Manual trigger | [Latest Release](https://github.com/carsaimz/CEditor/releases/latest) |

## CI/CD Pipeline

### Build Workflow (Automatic)
- Triggers on every push to `main`
- Auto-increments version number
- Creates a pre-release with debug APK + AAB
- No signing required (debug only)

### Release Workflow (Manual)
- Manual trigger with version input
- Creates signed release APK + AAB
- Creates an official GitHub release with tag

## Build Instructions

```bash
# Debug build (unsigned)
./gradlew assembleDebug

# Release build (requires keystore secrets)
./gradlew assembleRelease
```

## Project Structure

```
app/src/main/
├── java/com/ceditor/
│   ├── MainActivity.java      # File manager with RecyclerView
│   ├── PermissionActivity.java # Permission request (launcher)
│   ├── EditorActivity.java    # Code editor with Sora Editor
│   ├── SettingsActivity.java  # Settings with language/theme/AI
│   └── ai/
│       ├── AIProviderManager.java  # Multi-provider AI management
│       ├── AIChatHelper.java       # AI chat UI integration
│       └── AIProvidersActivity.java # Provider configuration UI
├── res/
│   ├── layout/               # All XML layouts
│   ├── values/               # English strings
│   ├── values-pt/            # Portuguese strings
│   ├── drawable/             # Vector drawables
│   └── drawable-xhdpi/       # PNG icons (file types, folders)
└── AndroidManifest.xml       # App manifest
```

## Technologies

- **Sora Editor 0.24.4**: Code editing with syntax highlighting
- **OkHttp 3.14.9**: HTTP client for AI API calls
- **Glide 4.12.0**: Image loading
- **Gson 2.8.7**: JSON parsing
- **Material Components 1.6.1**: UI components
- **AndroidX**: Modern Android libraries

## License

MIT License - see LICENSE file for details.
