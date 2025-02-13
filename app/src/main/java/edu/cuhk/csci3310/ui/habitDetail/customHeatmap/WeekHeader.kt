package edu.cuhk.csci3310.ui.habitDetail.customHeatmap

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun WeekHeader(dayOfWeek: DayOfWeek) {
    Box(
        modifier = Modifier
            .height(28.dp)
            .padding(horizontal = 8.dp),
    ) {
        Text(
            text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
            modifier = Modifier.align(Alignment.Center),
            style = MaterialTheme.typography.labelMedium,
        )
    }
}
