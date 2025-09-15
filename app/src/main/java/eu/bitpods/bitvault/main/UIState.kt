package eu.bitpods.bitvault.main

data class UIState(
    val minPasswordLen: Int = 5,
    val maxPasswordLen: Int = 50,
    val currentPasswordLen: Int = 20,
    val passwordSelectedSpecialChars: Boolean = true,
    val passwordSelectedNumbers: Boolean = true,
    val passphraseAddNumbers: Boolean = false,
    val minPassphraseLen: Int = 2,
    val maxPassphraseLen: Int = 20,
    val currentPassphraseLen: Int = 3
)