package edu.cuhk.csci3310.ui.formUtils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MyCheckbox(
    info: ToggleableInfo,
    onSelected: (ToggleableInfo) -> Unit
) {
    Row(
        modifier =
        Modifier
            .clickable {
                onSelected(info)
            }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = info.toggled, onCheckedChange = { onSelected(info) })
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = info.text)
    }
}