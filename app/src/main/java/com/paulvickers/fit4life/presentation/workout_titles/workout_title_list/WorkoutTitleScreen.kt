package com.paulvickers.fit4life.presentation.workout_titles.workout_title_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import com.paulvickers.fit4life.R
import com.paulvickers.fit4life.data.models.WorkoutTitle
import com.paulvickers.fit4life.presentation.destinations.AddTitleScreenDestination
import com.paulvickers.fit4life.presentation.destinations.WorkoutDayScreenDestination
import com.paulvickers.fit4life.presentation.shared_components.DestinationCard
import com.paulvickers.fit4life.presentation.shared_components.DialogWindow
import com.paulvickers.fit4life.presentation.shared_components.F4LButton
import com.paulvickers.fit4life.presentation.shared_components.TopBarText
import com.paulvickers.fit4life.ui.theme.F4LBlack
import com.paulvickers.fit4life.ui.theme.F4LDarkGrey
import com.paulvickers.fit4life.ui.theme.F4LLightOrange
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterialApi::class)
@Destination()
@Composable
fun WorkoutTitleScreen(
    navigator: DestinationsNavigator,
    viewModel: WorkoutTitleViewModel = hiltViewModel()
) {
    val allWorkoutTitles by viewModel.allWorkoutTitles.collectAsState()
    val workoutTitleId by viewModel.maxWorkoutId.collectAsState()
//    val maxWorkoutId by viewModel.maxWorkoutId.collectAsState()
    lateinit var workoutTitle: WorkoutTitle
    var openDialog by remember { mutableStateOf(false) }
    var openAddWorkoutDialog by remember { mutableStateOf(false) }
    var titleValueState by rememberSaveable { mutableStateOf("") }
    var numWeeksState by rememberSaveable { mutableStateOf("") }
    var numDaysState by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            Box(
                contentAlignment = Alignment.CenterStart
            ) {
                F4LButton(
                    leftIcon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Home",
                            modifier = Modifier
                                .clickable {
                                    navigator.popBackStack()
                                },
                        )
                    },
                )
                TopBarText(text = "Workouts")
            }
        }

//        bottomBar = {
//            BottomBarButton()
//            Card(
//                modifier = Modifier
//                    .padding(10.dp),
//                shape = RoundedCornerShape(12.dp)
//            ) {
//                BottomAppBar(
//                    modifier = Modifier.clickable {
//                        navigator.navigate(
//                            AddTitleScreenDestination(workoutTitleId = -1, workoutTitleTitle = "")
//                        )
//                    },
//                    content = {
//                        Text(
//                            modifier = Modifier.fillMaxWidth(),
//                            text = "Add Workout",
//                            textAlign = TextAlign.Center,
//                            style = MaterialTheme.typography.subtitle1,
//                        )
//                    })
//            }
//        }
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .weight(0.8f),
//                contentPadding = PaddingValues(bottom = 68.dp)
            ) {
                items(allWorkoutTitles) {
                    DestinationCardWithIcons(
                        text = it.title,
                        textClicked = {
                            navigator.navigate(
                                WorkoutDayScreenDestination(
                                    it.id ?: -1,
                                    it.title
                                )
                            )
                        },
                        editClicked = {
                            navigator.navigate(
                                AddTitleScreenDestination(
                                    workoutTitleId = it.id ?: -1,
                                    workoutTitleTitle = it.title
                                )
                            )
                        },
                        deleteClicked = {
                            openDialog = true
                            workoutTitle = it
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
//                    Card(
//                        Modifier
//                            .padding(horizontal = 10.dp, vertical = 5.dp),
//                        shape = RoundedCornerShape(12.dp)
////                        border = BorderStroke(width = 0.5.dp, color = Color.LightGray)
//                    ) {
//                        ListItem(
//                            modifier = Modifier.clickable {
//                                navigator.navigate(
//                                    WorkoutDayScreenDestination(
//                                        it.id ?: -1,
//                                        it.title
//                                    )
//                                )
//                            },
////                            icon = {
////                                IconButton(
////                                    onClick = {
////                                        navigator.navigate(
////                                            AddTitleScreenDestination(
////                                                workoutTitleId = it.id ?: -1,
////                                                workoutTitleTitle = it.title
////                                            )
////                                        )
////                                    }
////                                ) {
////                                    Icon(
////                                        imageVector = Icons.Default.Edit,
////                                        contentDescription = "Edit Workout",
////                                        tint = MaterialTheme.colors.onSurface
////                                    )
////                                }
////                            },
//                            text =
//                            {
//                                Text(
//                                    modifier = Modifier.fillMaxWidth(),
//                                    text = it.title,
//                                    textAlign = TextAlign.Center,
//                                    style = MaterialTheme.typography.body2,
//                                )
//                            },
////                            trailing = {
////                                IconButton(
////                                    onClick = {
////                                        openDialog = true
////                                        workoutTitle = it
//////                                        scope.launch {
//////                                            val result = scaffoldState.snackbarHostState.showSnackbar(
//////                                                message = "Are you sure you want to delete workout",
//////                                                actionLabel = "Delete",
//////                                            )
//////                                            if (result == SnackbarResult.ActionPerformed) { // if clicked on snackbar
//////                                                viewModel.onEvent(WorkoutTitleEvent.DeleteWorkoutTitles(it))
//////                                            }
//////                                        }
////                                    }
////                                ) {
////                                    Icon(
////                                        imageVector = Icons.Default.Delete,
////                                        contentDescription = "Delete Workout",
////                                        tint = MaterialTheme.colors.onSurface
////                                    )
////                                }
////                            }
//                        )
//                    }
                }

            }

            if (openDialog) {
                DialogWindow(
                    dismiss = { openDialog = false },
                    delete = {
                        viewModel.deleteWorkoutTitle(workoutTitle)
                        openDialog = false
                    },
                    titleToDelete = "workout"
                )
            }

            if (openAddWorkoutDialog) {
                AddWorkoutDialog(
                    dismiss = { openAddWorkoutDialog = false },
                    save = {
//                        openAddWorkoutDialog = false
                        if (titleValueState.isNotBlank() && numWeeksState.isDigitsOnly() && numWeeksState.toInt() > 0 && numDaysState.isDigitsOnly() && numDaysState.toInt() > 0) {
                            viewModel.saveWorkoutTitle(titleValueState, numWeeksState, numDaysState)
                            navigator.navigate(
                                WorkoutDayScreenDestination(
                                    workoutTitleId,
                                    titleValueState
                                )
                            )
                            titleValueState = ""
                            numWeeksState = ""
                            numDaysState = ""
                        }
                    },
                    titleValue = titleValueState,
                    onTitleValueChange = {
                        titleValueState = it
                    },
                    numWeeksValue = numWeeksState,
                    onNumWeeksValueChange = {
                        numWeeksState = it
                    },
                    numDaysValue = numDaysState,
                    onNumDaysValueChange = {
                        numDaysState = it
                    },
                    text = ""
                )
            }

            F4LButton("ADD WORKOUT", onClick = {
//                navigator.navigate(
//                    AddTitleScreenDestination(
//                        workoutTitleId = -1,
//                        workoutTitleTitle = ""
//                    )
//                )
                openAddWorkoutDialog = true
            }
            )
        }
    }
}

@Composable
fun AddWorkoutDialog(
    dismiss: () -> Unit,
    save: () -> Unit,
    titleValue: String,
    onTitleValueChange: (String) -> Unit,
    numWeeksValue: String,
    onNumWeeksValueChange: (String) -> Unit,
    numDaysValue: String,
    onNumDaysValueChange: (String) -> Unit,
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
        title = {
            Text(
                text = "Create Workout $text",
                color = F4LLightOrange
            )
        },
        text = {
            Column() {
                TextField(
                    value = titleValue,
                    onValueChange = { onTitleValueChange(it) },
                    label = { Text(text = "Workout title") },
                    keyboardOptions = KeyboardOptions.Default.copy(
//                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
//                    keyboardActions = KeyboardActions(
//                        onNext = null
//                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = F4LDarkGrey,
                        textColor = F4LLightOrange
                    )
                )
                TextField(
                    value = numWeeksValue,
                    onValueChange = { onNumWeeksValueChange(it) },
                    label = { Text(text = "Number of weeks") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
//                    keyboardActions = KeyboardActions(
//                        onDone = { save() }
//                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = F4LDarkGrey,
                        textColor = F4LLightOrange
                    )
                )
                TextField(
                    value = numDaysValue,
                    onValueChange = { onNumDaysValueChange(it) },
                    label = { Text(text = "Number of days a week") },
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
fun DestinationCardWithIcons(
    text: String,
    textClicked: () -> Unit = {},
    editClicked: () -> Unit = {},
    deleteClicked: () -> Unit = {},
) {
    DestinationCard(
        text = text,
        clickable = textClicked,
        icon = {
            Icon(
                Icons.Default.Edit,
                contentDescription = "Edit Icon",
                modifier = Modifier.clickable { editClicked() },
                tint = F4LBlack
            )
        },
        trailing = {
            Icon(
                Icons.Default.Delete,
                contentDescription = "Delete Icon",
                modifier = Modifier.clickable { deleteClicked() },
                tint = F4LBlack
            )
        }
    )
}

@Preview
@Composable
fun DestinationCardWithIconsPrev() {
    DestinationCardWithIcons("Workout Title")
}

@Preview
@Composable
fun BottomBarButtonPrev() {
    F4LButton()
}

@Preview
@Composable
fun ImagePrev() {
    Image(
        painterResource(R.drawable.logo5),
        contentDescription = "Logo",
        alignment = Alignment.Center,
        colorFilter = ColorFilter.tint(
            Color.White
        )
    )
}

@Preview
@Composable
fun TopAppBarPrev() {

    Box(contentAlignment = Alignment.CenterStart) {
        F4LButton(
            leftIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back Arrow",
                    modifier = Modifier
                        .clickable {

                        },
                )
            },
        )
        TopBarText(text = "Workouts")
    }
}