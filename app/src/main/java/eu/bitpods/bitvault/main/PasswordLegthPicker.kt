package eu.bitpods.bitvault.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastRoundToInt

@Composable
fun PasswordLengthPickerView(
    modifier: Modifier,
    minLength: Int,
    maxLength: Int,
    currentLength: Int,
    onValueChanged: (Int) -> Unit,
) {
    var sliderPosition by remember { mutableIntStateOf(0) }

    val inputRange = remember(minLength, maxLength) { minLength.toFloat()..maxLength.toFloat() }

    LaunchedEffect(currentLength) {
        sliderPosition = currentLength
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "length",
            style = MaterialTheme.typography.labelLarge
        )
        Slider(
            modifier = Modifier
                .fillMaxWidth(),
            value = sliderPosition.toFloat(),
            onValueChange = { sliderPosition = it.fastRoundToInt() },
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.primary,
                activeTrackColor = MaterialTheme.colorScheme.secondary,
                inactiveTrackColor = MaterialTheme.colorScheme.surfaceVariant,
                activeTickColor = MaterialTheme.colorScheme.secondary,
                disabledThumbColor = Color.Gray
            ),
            valueRange = inputRange,
            onValueChangeFinished = {
                onValueChanged(sliderPosition)
            },
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                modifier = Modifier,
                text = minLength.toString(),
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = currentLength.toString(),
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = maxLength.toString(),
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}