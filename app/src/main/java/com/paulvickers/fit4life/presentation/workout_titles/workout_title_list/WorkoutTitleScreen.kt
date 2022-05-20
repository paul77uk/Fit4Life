package com.paulvickers.fit4life.presentation.workout_titles.workout_title_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.paulvickers.fit4life.R
import com.paulvickers.fit4life.data.models.WorkoutTitle
import com.paulvickers.fit4life.presentation.destinations.WorkoutDayScreenDestination
import com.paulvickers.fit4life.presentation.shared_components.*
import com.paulvickers.fit4life.ui.theme.F4LBlack
import com.paulvickers.fit4life.ui.theme.F4LLightOrange
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun WorkoutTitleScreen(
    navigator: DestinationsNavigator,
) {
    var openAddWorkoutDialog by remember { mutableStateOf(false) }
    Scaffold(
        topBar = { WorkoutTitleTopAppBar(navigator) },
        floatingActionButton = { WorkoutTitleFAB { openAddWorkoutDialog = true } }
    ) { it ->
        WorkoutTitleLazyColumn(
            navigator = navigator,
            contentPadding = it.calculateBottomPadding(),
            openAddWorkoutDialogState = openAddWorkoutDialog,
            openAddWorkoutDialog = { openAddWorkoutDialog = it }
        ) {
            WorkoutTitleCard(navigator, it)
        }
    }
}

@Composable
fun WorkoutTitleTopAppBar(navigator: DestinationsNavigator) {
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

@Composable
fun WorkoutTitleLazyColumn(
    contentPadding: Dp,
    viewModel: WorkoutTitleViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,
    openAddWorkoutDialogState: Boolean,
    openAddWorkoutDialog: (Boolean) -> Unit,
    content: @Composable (WorkoutTitle) -> Unit
) {
    var openDeleteDialog by remember { mutableStateOf(false) }
    var openEditDialog by remember { mutableStateOf(false) }
    var workoutTitleIndex by remember { mutableStateOf(0) }
    var editDialogTextState by remember { mutableStateOf("") }
    var addWorkoutDialogTextState by remember { mutableStateOf("") }
    val allWorkoutTitles by viewModel.allWorkoutTitles.collectAsState()
    LazyColumn(
        Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(contentPadding)
//                .weight(0.8f),
    ) {
        items(allWorkoutTitles) { it: WorkoutTitle ->
//        content(it)
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
                    editDialogTextState = it.title
                    workoutTitleIndex = it.id ?: 0
                    openEditDialog = true
                },
                deleteClicked = {
                    openDeleteDialog = true
                    workoutTitleIndex = it.id ?: 0
                }
            )
            if (openDeleteDialog)
                DialogWindow(
                    dismiss = { openDeleteDialog = false },
                    delete = {
                        viewModel.deleteWorkoutTitle(
                            WorkoutTitle(
                                id = workoutTitleIndex,
                                ""
                            )
                        )
                        openDeleteDialog = false
                    },
                    titleToDelete = "Workout"
                )
            if (openEditDialog)
                F4LEditDialog(
                    dismiss = {
                        openEditDialog = false
                        editDialogTextState = ""
                    },
                    save = {
                        viewModel.addWorkoutTitle(
                            workoutTitleIndex,
                            editDialogTextState
                        )
                        openEditDialog = false
                        editDialogTextState = ""
                    },
                    value = editDialogTextState,
                    onValueChange = { editDialogTextState = it },
                    text = "Update Workout",
                    keyboardType = KeyboardType.Text
                )
            if (openAddWorkoutDialogState)
                F4LEditDialog(
                    dismiss = {
                        openAddWorkoutDialog(false)
                        addWorkoutDialogTextState = ""
                    },
                    save = {
                        viewModel.addWorkoutTitle(
                            -1,
                            addWorkoutDialogTextState
                        )
                        openAddWorkoutDialog(false)
                        addWorkoutDialogTextState = ""
                    },
                    value = addWorkoutDialogTextState,
                    onValueChange = { addWorkoutDialogTextState = it },
                    text = "Create Workout",
                    keyboardType = KeyboardType.Text
                )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

//onClick = {
//    if (textState.isNotBlank()
////                        && dayState.isNotBlank() && weekState.isNotBlank()
//    ) {
//        viewModel.addWorkoutTitle(workoutTitleId, textState)
////                        viewModel.addWorkoutWeeks(numOfDays = weekState.toInt())
////                        viewModel.addWorkoutDays(dayState.toInt())
//        navigator.navigate(
//            WorkoutTitleScreenDestination(
////                                workoutTitleId,
////                                workoutTitleTitle
//            )
//        )
//    } else {
//        scope.launch {
//            scaffoldState.snackbarHostState.showSnackbar(
//                "Fields cannot be empty", null, SnackbarDuration.Short
//            )
//        }
//    }
//}

@Composable
fun WorkoutTitleCard(
    navigator: DestinationsNavigator,
    workoutTitle: WorkoutTitle
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                navigator.navigate(
                    WorkoutDayScreenDestination(
                        workoutTitleId = workoutTitle.id ?: -1,
                        workoutTitleTitle = workoutTitle.title
                    )
                )
            },
        shape = RoundedCornerShape(12.dp),
        backgroundColor = F4LLightOrange
    ) {
        Text(
            text = workoutTitle.title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle1,
        )
    }
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

@Composable
fun WorkoutTitleFAB(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        Icon(imageVector = Icons.Default.Add, contentDescription = null)
    }
}

@Preview
@Composable
fun DestinationCardWithIconsPrev() {
    DestinationCardWithIcons("Workout Title")
}

@Preview
@Composable
fun BottomBarButtonPrev() {
    F4LButton(text = "Add")
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