package com.paulvickers.fit4life.presentation.workout_days.day_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import com.paulvickers.fit4life.data.models.ExerciseTitle
import com.paulvickers.fit4life.data.models.Set
import com.paulvickers.fit4life.data.models.WorkoutDay
import com.paulvickers.fit4life.data.models.WorkoutWeek
import com.paulvickers.fit4life.presentation.sets.sets_list.SetViewModel
import com.paulvickers.fit4life.presentation.shared_components.F4LButton
import com.paulvickers.fit4life.presentation.shared_components.TopBarText
import com.paulvickers.fit4life.ui.theme.F4LBlack
import com.paulvickers.fit4life.ui.theme.F4LDarkGrey
import com.paulvickers.fit4life.ui.theme.F4LLightOrange
import com.paulvickers.fit4life.ui.theme.bungeeInlineFamily
import com.ramcosta.composedestinations.annotation.Destination


@OptIn(ExperimentalMaterialApi::class, androidx.compose.foundation.ExperimentalFoundationApi::class)
@Destination
@Composable
fun WorkoutDayScreen(
    workoutTitleId: Int,
    workoutTitleTitle: String,
    viewModel: WorkoutDayViewModel = hiltViewModel(),
    weekViewModel: WeekViewModel = hiltViewModel(),
    setViewModel: SetViewModel = hiltViewModel()
) {
    viewModel.getWeeks(workoutTitleId)
    val weeks by viewModel.weeks.collectAsState()
    val selectedWeek by viewModel.selectedWeek.collectAsState()
    val exerciseTitles by viewModel.exerciseTitles.collectAsState()
    val sets by viewModel.sets.collectAsState()
    val minDayId by viewModel.minDayId.collectAsState()
//    viewModel.getDays(selectedWeek)
    val days by viewModel.days.collectAsState()
    val selectedDay by viewModel.selectedDay.collectAsState()
    lateinit var day: WorkoutDay
    lateinit var sett: Set
    lateinit var exercise: ExerciseTitle
    lateinit var week: WorkoutWeek
    var openWeightDialog by rememberSaveable { mutableStateOf(false) }
    var openRepDialog by rememberSaveable { mutableStateOf(false) }
    var openAddExerciseDialog by rememberSaveable { mutableStateOf(false) }
    var dropdownMenuExpanded by rememberSaveable { mutableStateOf(false) }
    var selectedIndex by rememberSaveable { mutableStateOf(0) }

//    viewModel.getExerciseTitles()
    var weekState by rememberSaveable { mutableStateOf("") }
    weekState = weeks.firstOrNull()?.week ?: ""
    var weekIdState by rememberSaveable { mutableStateOf(weeks.firstOrNull()?.id ?: 10) }
    var dayState by rememberSaveable { mutableStateOf("") }
    var weightState by rememberSaveable { mutableStateOf("") }
    var repState by rememberSaveable { mutableStateOf("") }
    var numOfSetsState by rememberSaveable { mutableStateOf("") }
    var exerciseState by rememberSaveable { mutableStateOf("") }
    var expandedState by rememberSaveable { mutableStateOf(false) }
//    dayState = days.firstOrNull()?.day ?: ""
//    var weekId by rememberSaveable { mutableStateOf(weeks.first().id) }

//    var weekId by rememberSaveable { mutableStateOf(weeks.first().id) }
//    viewModel.getDays(weekId!!)
    var dayId by rememberSaveable { mutableStateOf(1) }
    var exerciseId = 1

    Scaffold(
        topBar = {
            Column {
                TopBarText(
                    text = workoutTitleTitle
                )

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    weeks.forEach {
                        item {
                            F4LNavButton(
                                selected = selectedWeek == it.id,
                                onClick = {
                                    viewModel.setSelectedWeek(it.id ?: 0)
                                    viewModel.getDays()
                                },
                                text = it.week
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                }

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    days.forEach {
                        item {
                            F4LNavButton(
                                selected = selectedDay == it.id,
                                onClick = {
                                    viewModel.setSelectedDay(it.id ?: 0)
                                },
                                text = it.day
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                }
            }
        },
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            LazyColumn(
                modifier = Modifier
//                    .fillMaxSize()
                    .fillMaxWidth()
                    .padding(it)
                    .weight(0.8f)
            ) {

//                viewModel.getSets()
//                viewModel.getSets(1)

                viewModel.getSets(selectedDay)

                val groupedItems = sets.groupBy { it.exerciseForSetsId }

                groupedItems.forEach { (exerciseForSetsId, set) ->
                    item {
                        Text(
                            text = exerciseTitles[exerciseForSetsId - 1].title,
                            color = F4LLightOrange,
                            fontSize = 20.sp,
                            fontFamily = bungeeInlineFamily,
                            modifier = Modifier
                                .padding(16.dp)
                                .background(F4LBlack)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                    items(set) {
                        val isCircuit = exerciseTitles[it.exerciseForSetsId - 1].isCircuit == 1
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                if (isCircuit)
                                    TextFieldBoxes(
                                        text = "Exercise",
                                        value = exerciseTitles[it.exerciseId - 1].title,
                                        widthDivision = if (isCircuit) 4 else 5,
                                    )
                                else {
                                    TextFieldBoxes(
                                        text = "Set",
                                        value = it.setNum.toString(),
                                        widthDivision = if (isCircuit) 4 else 5
                                    )
                                }
                                TextFieldBoxes(
                                    text = "Weight",
                                    value = it.weight.toString(),
                                    onClick = {
                                        weightState = it.weight.toString()
                                        sett = it
                                        openWeightDialog = true
                                    },
                                    widthDivision = if (isCircuit) 4 else 5
                                )
                                TextFieldBoxes(
                                    text = when (it.isRepsDistTime) {
                                        1 -> "Reps"
                                        2 -> "Distance"
                                        else -> "Time"
                                    },
                                    value = it.repsDistTime.toString(),
                                    onClick = {
                                        repState = it.repsDistTime.toString()
                                        sett = it
                                        openRepDialog = true
                                    },
                                    widthDivision = if (isCircuit) 4 else 5
                                )
                                if (exerciseTitles[it.exerciseForSetsId - 1].isCircuit == 0) TextFieldBoxes(
                                    text = "",
                                    value = "",
                                    checkedState = it.isCompleted == 1,
                                    onCheckedChange = { viewModel.updateSet(it) },
                                    isChecked = true,
                                    widthDivision = if (isCircuit) 4 else 5
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                    if (exerciseTitles[set.first().exerciseForSetsId - 1].isCircuit == 1) {
                        item {
                            Box(modifier = Modifier.fillMaxWidth()) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    F4LButton(
                                        "ROUND COMPLETED",
                                    onClick = {
                                        viewModel.updateRound(set.last())
                                    }
                                    )
                                    RoundCount(
                                        currentRound = set.last().isCompleted,
                                        numberOfRounds = set.last().setNum
                                    )
                                }
                            }
                        }
                    }
                }

            }
            if (openWeightDialog) {
                F4LDialog(
                    dismiss = { openWeightDialog = false },
                    save = {
                        openWeightDialog = false
                        if (weightState.isNotBlank() && weightState.isDigitsOnly())
                            viewModel.updateWeight(sett, weightState.toInt())
                    },
                    weightValue = weightState,
                    onWeightValueChange = {
                        weightState = it
                    },
                    text = "Weight"
                )
            }

            if (openRepDialog) {
                F4LDialog(
                    dismiss = { openRepDialog = false },
                    save = {
                        openRepDialog = false
                        if (repState.isNotBlank() && repState.isDigitsOnly())
                            viewModel.updateReps(sett, repState.toInt())
                    },
                    weightValue = repState,
                    onWeightValueChange = {
                        repState = it
                    },
                    text = "Reps"
                )
            }

            F4LButton(
                text = "ADD EXERCISE",
                onClick = {
                    openAddExerciseDialog = true
                    exerciseState = ""
                }
            )
            if (openAddExerciseDialog) {
                AddExerciseDialog(
                    dismiss = { openAddExerciseDialog = false },
                    save = {
                        openAddExerciseDialog = false
                        viewModel.addSet(
                            numberOfSets = numOfSetsState.toInt(),
                            exerciseId = selectedIndex,
                            isRepsDistTime = 1,
                            exerciseForSetsId = selectedIndex + 1,
                            dayId = dayId
                        )
                    },
                    numOfSets = numOfSetsState,
                    onNumOfSetsValueChange = {
                        numOfSetsState = it
                    },
                    dropDown = {
                        MyDropdownMenuLayout(
                            menuItems = exerciseTitles,
                            menuExpandedState = dropdownMenuExpanded,
                            selectedIndex = selectedIndex,
                            updateMenuExpandStatus = { dropdownMenuExpanded = true },
                            onDismissMenuView = { dropdownMenuExpanded = false },
                            onMenuItemClick = { index ->
                                selectedIndex = index
                                dropdownMenuExpanded = false
                            }
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun F4LDialog(
    dismiss: () -> Unit,
    save: () -> Unit,
    weightValue: String,
    onWeightValueChange: (String) -> Unit,
    text: String
) {
    AlertDialog(
        onDismissRequest = dismiss,
        confirmButton = {
            TextButton(onClick = save)
            { Text(text = "Save") }
        },
        dismissButton = {
            TextButton(onClick = dismiss)
            { Text(text = "Cancel") }
        },
        title = { Text(text = "Update $text", color = F4LLightOrange) },
        text = {
            TextField(
                value = weightValue,
                onValueChange = { onWeightValueChange(it) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { save() }
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = F4LDarkGrey,
                    textColor = F4LLightOrange
                )
            )
        },
        shape = RoundedCornerShape(12)
    )
}

@Composable
fun AddExerciseDialog(
    dismiss: () -> Unit,
    save: () -> Unit,
    numOfSets: String,
    onNumOfSetsValueChange: (String) -> Unit,
    dropDown: @Composable () -> Unit
) {
    AlertDialog(
        onDismissRequest = dismiss,
        confirmButton = {
            TextButton(onClick = save)
            { Text(text = "Save") }
        },
        dismissButton = {
            TextButton(onClick = dismiss)
            { Text(text = "Cancel") }
        },
        title = {
            Text(
                text = "Add Exercise",
                color = F4LLightOrange
            )
        },
        text = {
            Column {
                Spacer(modifier = Modifier.height(8.dp))
                dropDown()
                TextField(
                    value = numOfSets,
                    onValueChange = { onNumOfSetsValueChange(it) },
                    label = { Text(text = "Number of sets") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { save() }
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = F4LDarkGrey,
                        textColor = F4LLightOrange
                    )
                )
            }
        },
        shape = RoundedCornerShape(12)
    )
}

@Composable
fun DropDown(
    dismiss: () -> Unit,
    list: List<ExerciseTitle>,
    expanded: () -> Unit,
    expandedValue: Boolean,
    onItemChange: (String) -> Unit,
    currentValue: String

) {
//    val list = listOf("one", "two", "three", "four", "five")
//    val expanded = remember { mutableStateOf(false) }
//    val currentValue = remember { mutableStateOf(list[0]) }

    Box(modifier = Modifier.fillMaxWidth()) {

        Row(modifier = Modifier
            .clickable {
                expanded()
            }
            .align(Alignment.Center)) {
            Text(text = currentValue)
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = null)

            DropdownMenu(expanded = expandedValue, onDismissRequest = {
                dismiss()
            }) {

                list.forEach {
                    DropdownMenuItem(
                        onClick = {
                            onItemChange(currentValue)
                            expanded()
                        }
                    ) {
                        Text(text = it.title)
                    }
                }
            }
        }
    }
}

@Composable
fun MyDropdownMenuLayout(
    menuItems: List<ExerciseTitle>,
    menuExpandedState: Boolean,
    selectedIndex: Int,
    updateMenuExpandStatus: () -> Unit,
    onDismissMenuView: () -> Unit,
    onMenuItemClick: (Int) -> Unit,
) {
    Column {
        Text(text = "")
        Box(
            modifier = Modifier
                .border(0.5.dp, MaterialTheme.colors.onSurface.copy(alpha = 0.5f))
                .clickable(
                    onClick = {
                        updateMenuExpandStatus()
                    },
                ),

            ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                val (label, iconView) = createRefs()

                Text(
                    text = menuItems[selectedIndex].title,
                    modifier = Modifier
                        .constrainAs(label) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(iconView.start)
                            width = Dimension.fillToConstraints
                        }
                )

                val displayIcon: Painter = painterResource(
                    id = android.R.drawable.arrow_down_float
                )

                Icon(
                    modifier = Modifier
                        .size(18.dp, 18.dp)
                        .constrainAs(iconView) {
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                            end.linkTo(parent.end)
                        },
                    tint = MaterialTheme.colors.onSurface,
                    painter = displayIcon,
                    contentDescription = null,
                )

                DropdownMenu(
                    modifier = Modifier
                        .background(MaterialTheme.colors.surface),
                    expanded = menuExpandedState,
                    onDismissRequest = { onDismissMenuView() },
                ) {
                    menuItems.forEachIndexed { index, title ->
                        DropdownMenuItem(
                            onClick = {
                                onMenuItemClick(index)
                            }) {
                            Text(text = title.title)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun F4LNavButton(selected: Boolean, onClick: () -> Unit, text: String) {

    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.padding(5.dp),
//        elevation = ButtonDefaults.elevation(5.dp),
        border = BorderStroke(
            1.dp,
            F4LLightOrange
        ),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = if (selected) F4LLightOrange else F4LBlack
        ),
        shape = RoundedCornerShape(25),
    ) {
        Text(
            text = text,
            color = if (selected) F4LBlack else F4LLightOrange
        )
    }
}

@Composable
fun TextFieldBoxes(
    text: String,
    value: String,
    checkedState: Boolean = false,
    onCheckedChange: () -> Unit = {},
    isChecked: Boolean = false,
    onClick: () -> Unit = {},
    widthDivision: Int,
) {
    Column(
//        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        Box(
            modifier = Modifier
                .border(
                    border = BorderStroke(
                        1.dp,
                        F4LLightOrange
                    ),
                    shape = RoundedCornerShape(25),
                )
                .padding(4.dp)
                .height(48.dp)
                .width(screenWidth / widthDivision)
                .clickable { onClick() },
            contentAlignment = Alignment.Center,
        ) {
            if (isChecked)
                Checkbox(
                    checked = checkedState,
                    onCheckedChange = { onCheckedChange() },
                    colors = CheckboxDefaults.colors(uncheckedColor = F4LLightOrange)
                )
            else
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = text,
                        color = F4LLightOrange,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = value,
                        color = F4LLightOrange,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
        }
    }
}

@Composable
fun RoundCount(currentRound: Int, numberOfRounds: Int) {
    Text(text = "$currentRound/$numberOfRounds", color = F4LLightOrange, fontSize = 20.sp, fontWeight = FontWeight.Bold)
}

@Composable
fun CircuitLayout() {

}

@Preview
@Composable
fun PrevCircuitLayout() {

}

//@OptIn(ExperimentalMaterialApi::class)
//@Preview(showBackground = true)
//@Composable
//fun ListItemPrev() {
//    Row(
//        horizontalArrangement = Arrangement.SpaceEvenly,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        TextFieldBoxes(text = "Set", "1")
//        TextFieldBoxes(text = "Weight", "50")
//    }
//}

@Preview(showBackground = true)
@Composable
fun WorkoutDayScreenPrev() {
    BottomAppBar() {

        BottomNavigationItem(
            selected = true,
            onClick = { /*TODO*/ },
            icon = { Text(text = "DAY 1") },
        )

    }
}

@Preview
@Composable
fun CircuitCheckboxPrev() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            RoundCount(0,5)
        }
    }
}