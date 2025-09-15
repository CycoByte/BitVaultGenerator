package eu.bitpods.bitvault.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.bitpods.bitvault.main.ui.theme.BitVaultGeneratorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BitVaultGeneratorTheme {

                val viewModel: GeneratorViewModel = viewModel()

                val uiState by viewModel.uiStateSF.collectAsStateWithLifecycle()
                val password by viewModel.generatedPasswordSF.collectAsStateWithLifecycle()
                val passphrase by viewModel.generatedPassPhraseSF.collectAsStateWithLifecycle()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                                .verticalScroll(rememberScrollState()),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(
                                text = "Password",
                                style = MaterialTheme.typography.titleLarge
                            )
                            HorizontalDivider()
                            Text(
                                text = password
                            )
                            SelectionRowView(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                title = "use special charactes",
                                isSelected = uiState.passwordSelectedSpecialChars,
                                onToggle = viewModel::onToggleSpecialCharacters
                            )
                            SelectionRowView(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                title = "use numbers",
                                isSelected = uiState.passwordSelectedNumbers,
                                onToggle = viewModel::onPasswordToggleNumbers
                            )
                            PasswordLengthPickerView(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                minLength = uiState.minPasswordLen,
                                maxLength = uiState.maxPasswordLen,
                                currentLength = uiState.currentPasswordLen,
                                onValueChanged = viewModel::onPasswordLenChanged
                            )

                            Spacer(
                                modifier = Modifier
                            )

                            Text(
                                text = "Passphrase",
                                style = MaterialTheme.typography.titleLarge
                            )
                            HorizontalDivider()
                            Text(
                                text = passphrase
                            )
                            SelectionRowView(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                title = "use numbers",
                                isSelected = uiState.passphraseAddNumbers,
                                onToggle = viewModel::onPassphraseToggleNumbers
                            )
                            PasswordLengthPickerView(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                minLength = uiState.minPassphraseLen,
                                maxLength = uiState.maxPassphraseLen,
                                currentLength = uiState.currentPassphraseLen,
                                onValueChanged = viewModel::onPassphraseLenChanged
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SelectionRowView(
    modifier: Modifier,
    title: String,
    isSelected: Boolean,
    onToggle: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(
            modifier = Modifier
                .weight(1f)
        )
        Checkbox(
            checked = isSelected,
            onCheckedChange = {
                onToggle()
            }
        )
    }
}