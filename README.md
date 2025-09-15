# Simple Password Generator
 
- Generate random password
- Generate random passphrases

## Use
Add jitpack in settings.gradle.kts
```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```
Add the dependency
```
dependencies {
    implementation("com.github.CycoByte:BitVaultGenerator:Tag")
}
```
