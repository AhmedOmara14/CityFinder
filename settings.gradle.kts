pluginManagement {
    repositories {
        gradlePluginPortal() // For plugins from the Gradle Plugin Portal
        google()             // For Google-specific libraries and plugins (like AGP, Hilt, KSP)
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "CityFinder"
include(":app")
 