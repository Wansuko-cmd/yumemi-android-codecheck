dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Android Engineer CodeCheck"
include("android", "android:app")
include("core", "core:domain")
include("utils")
