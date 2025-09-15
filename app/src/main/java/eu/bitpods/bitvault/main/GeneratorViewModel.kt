package eu.bitpods.bitvault.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.bitpods.bitvault.passwordgenerator.PassphraseGenerator
import eu.bitpods.bitvault.passwordgenerator.PasswordGenerator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class GeneratorViewModel: ViewModel() {

    private val uiStateMSF = MutableStateFlow(UIState())
    val uiStateSF = uiStateMSF.asStateFlow()

    val generatedPasswordSF: StateFlow<String> = uiStateMSF
        .debounce(300)
        .mapLatest { state ->
            PasswordGenerator.generate(
                numbers = state.passwordSelectedNumbers,
                specialCharacters = state.passwordSelectedSpecialChars,
                maxLength = state.currentPasswordLen
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = ""
        )

    val generatedPassPhraseSF: StateFlow<String> = uiStateMSF
        .debounce(300)
        .mapLatest { state ->
            PassphraseGenerator.generate(
                numbers = state.passphraseAddNumbers,
                words = state.currentPassphraseLen
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = ""
        )

    fun onPasswordLenChanged(len: Int) {
        uiStateMSF.update { it.copy(currentPasswordLen = len) }
    }

    fun onToggleSpecialCharacters() {
        uiStateMSF.update { it.copy(passwordSelectedSpecialChars = !it.passwordSelectedSpecialChars) }
    }

    fun onPasswordToggleNumbers() {
        uiStateMSF.update { it.copy(passwordSelectedNumbers = !it.passwordSelectedNumbers) }
    }

    fun onPassphraseToggleNumbers() {
        uiStateMSF.update { it.copy(passphraseAddNumbers = !it.passphraseAddNumbers) }
    }

    fun onPassphraseLenChanged(len: Int) {
        uiStateMSF.update { it.copy(currentPassphraseLen = len) }
    }
}