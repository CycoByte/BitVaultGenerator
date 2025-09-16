# Simple Password Generator
 
- Generate random password
- Generate random passphrases

## Import
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
    implementation("com.github.CycoByte:BitVaultGenerator:vX.Y.Z")
}
```
## Use example
- passphrase
```
PassphraseGenerator.generate(
    numbers = true,
    words = 3
)
```
- password
```
PasswordGenerator.generate(
    numbers = true,
    specialCharacters = true,
    maxLength = 20
)
```

Release
- `git pull origin main`
- `git tag -a v1.2.3 -m "Release version demo"` replace version with correct version number following release versioning guidelines
- `git push origin v1.2.3` replace version with correct version number following release versioning guidelines