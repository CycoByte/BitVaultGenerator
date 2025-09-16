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
    implementation("com.github.CycoByte:BitVaultGenerator:main-SNAPSHOT")
}
```

Release
- `git pull origin main`
- `git tag -a v1.2.3 -m "Release version demo"` replace version with correct version number following release versioning guidelines
- `git push origin v1.2.3` replace version with correct version number following release versioning guidelines