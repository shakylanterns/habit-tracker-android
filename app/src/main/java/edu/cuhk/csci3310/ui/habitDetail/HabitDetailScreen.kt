package edu.cuhk.csci3310.ui.habitDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import edu.cuhk.csci3310.ui.habitDetail.customHeatmap.Level
import edu.cuhk.csci3310.ui.habitDetail.customHeatmap.MyHeatMapCalendar
import java.time.LocalDate

@Composable
fun HabitDetailScreen(viewModel: HabitDetailViewModel = hiltViewModel()) {
    val habit = viewModel.habit.collectAsState(initial = null)
    val groups = viewModel.groups.collectAsState(initial = listOf())
    val heatMap = viewModel.heatMap.collectAsState(initial = mutableMapOf<LocalDate, Level>())

    if (habit.value == null || groups.value == null) {
        return
    }

    val records = viewModel.records.collectAsState()
    val state = rememberUseCaseState(visible = false)

    LaunchedEffect(key1 = true, block = {
        viewModel.uiChannel.collect { event ->
            when (event) {
                is HabitDetailScreenUiEvent.ShowAddToGroupDialog -> {
                    state.show()
                }
            }
        }
    })


    Column(modifier = Modifier.fillMaxSize()) {
        HabitDescription(habit = habit.value!!)
        Spacer(modifier = Modifier.height(16.dp))
        HabitDayEntries(habit = habit.value!!, records = records.value,
            changeStatus = { index, it ->
                viewModel.onEvent(
                    HabitDetailEvent.ChangeRecord(
                        index = index,
                        newStatus = it
                    )
                )
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        HabitGroupListing(
            groups = groups.value!!, state = state,
            onCreateDialog = {
                viewModel.onEvent(HabitDetailEvent.AddToGroupDialog)
            },
            onSelect = { newlyAdded, newlyRemoved ->
                viewModel.onEvent(
                    HabitDetailEvent.AddToGroup(
                        newlyAdded,
                        newlyRemoved
                    )
                )
            },
        )
        Spacer(modifier = Modifier.height(16.dp))
        MyHeatMapCalendar(heatMap = heatMap.value)
    }
}
