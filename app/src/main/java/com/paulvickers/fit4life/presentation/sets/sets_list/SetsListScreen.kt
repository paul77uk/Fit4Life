package com.paulvickers.fit4life.presentation.sets.sets_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.paulvickers.fit4life.data.models.Set
import com.paulvickers.fit4life.presentation.shared_components.DialogWindow
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterialApi::class, androidx.compose.ui.ExperimentalComposeUiApi::class)
@Destination
@Composable
fun SetsListScreen(
    exerciseId: Int,
    exerciseTitle: String,
    navigator: DestinationsNavigator,
    viewModel: SetViewModel = hiltViewModel()
) {
    val sets by viewModel.sets.collectAsState()
    lateinit var set: Set

    var openDialog by remember { mutableStateOf(false) }
    viewModel.getSets(exerciseId)
    val keyboardController = LocalSoftwareKeyboardController.current
    Scaffold(
        topBar = {
            TopAppBar(content = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = exerciseTitle,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h6,
                )
            })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.addSet(
                        setId = -1,
                        setTitle = "",
                        setNum = 0,
                        weight = 0,
                        reps = 0,
                        exerciseId = exerciseId,
                        isCompleted = false
                    )
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        },
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            items(sets) { setItem ->
                var setNumState by rememberSaveable { mutableStateOf(setItem.setNum.toString()) }
                var weightState by rememberSaveable { mutableStateOf(setItem.weight.toString()) }
                var repState by rememberSaveable { mutableStateOf(setItem.reps.toString()) }
                var textState by rememberSaveable { mutableStateOf(setItem.setTitle) }
                var checkedState by rememberSaveable { mutableStateOf(setItem.isCompleted) }

                Card(
                    Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .fillMaxWidth(),
                    border = BorderStroke(width = 0.5.dp, color = Color.LightGray)
                ) {
                    SetTile(
                        textValue = textState,
                        onTextValueChange = {
                            textState = it
                        },
                        setValue = setNumState,
                        onSetValueChange = {
                            setNumState = it
                        },
                        weightValue = weightState,
                        onWeightValueChange = {
                            weightState = it
                        },
                        repsValue = repState,
                        onRepsValueChange = {
                            repState = it
                        },
                        checked = checkedState,
                        onCheckedChange = {
                            checkedState = it
                            viewModel.addSet(
                                setId = setItem.id ?: -1,
                                setTitle = textState,
                                setNum = setNumState.toInt(),
                                weight = weightState.toInt(),
                                reps = repState.toInt(),
                                exerciseId = exerciseId,
                                isCompleted = checkedState
                            )
                            keyboardController?.hide()
                        },
                        onDeleteClicked = {
                            openDialog = true
//                                    exerciseTitle = it
                            set = setItem
                        },
//                        onEditClicked = {
//                            viewModel.addSet(
//                                setId = setItem.id ?: -1,
//                                setTitle = textState,
//                                setNum = setNumState.toInt(),
//                                weight = weightState.toInt(),
//                                reps = repState.toInt(),
//                                exerciseId = exerciseId
//                            )
//                            keyboardController?.hide()
//                        }
                    )
                }
            }
        }

        if (openDialog) {
            DialogWindow(
                dismiss = { openDialog = false },
                delete = {
                    viewModel.deleteSet(set)
                    openDialog = false
                },
                titleToDelete = "exercise"
            )
        }

    }
}

@Composable


fun SetTile(
    textValue: String,
    onTextValueChange: (String) -> Unit,
    setValue: String,
    onSetValueChange: (String) -> Unit,
    weightValue: String,
    onWeightValueChange: (String) -> Unit,
    repsValue: String,
    onRepsValueChange: (String) -> Unit,
//    onExerciseValueChange: (String) -> Unit,
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit,
    onDeleteClicked: () -> Unit,
//    onEditClicked: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = textValue,
            onValueChange = onTextValueChange,
//            modifier = Modifier.padding(8.dp),
            textStyle = LocalTextStyle.current.copy(
                fontStyle = MaterialTheme.typography.subtitle1.fontStyle,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                textAlign = TextAlign.Center
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.background,
                unfocusedIndicatorColor = MaterialTheme.colors.background,
            )
        )
//        Text(
//            text = exerciseValue,
//            modifier = Modifier.padding(8.dp),
//            style = MaterialTheme.typography.subtitle1,
//            fontWeight = FontWeight.Bold,
//            color = Color.Gray
//        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

//            IconButton(
//                onClick = onEditClicked
//
//                //                navigator.navigate(
//                //                    AddExerciseScreenDestination(
//                //                        exerciseId = it.id ?: -1,
//                //                        exerciseTitle = it.title,
//                //                        dayId = dayId
//                //                    )
//                //                )
//
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Edit,
//                    contentDescription = "Edit Exercise",
//                    tint = MaterialTheme.colors.onSurface
//                )
//            }
            //        Text(
            //            text = setText,
            ////            modifier = Modifier
            ////                .padding(16.dp),
            ////            .weight(0.3f),
            //            textAlign = TextAlign.Center
            //        )
            TextFieldBoxes(text = "Set", setValue, onSetValueChange)
            TextFieldBoxes(text = "Weight", weightValue, onWeightValueChange)
            TextFieldBoxes(text = "Reps", repsValue, onRepsValueChange)
            //        OutlinedTextField(
            //            value = weightValue,
            //            onValueChange = onWeightValueChange,
            //            modifier = Modifier
            ////                .weight(0.2f)
            //                .width(52.dp)
            //                .padding(vertical = 16.dp),
            //            label = { Text("Weight") },
            //            singleLine = true
            //        )
            //        Spacer(modifier = Modifier.width(16.dp))
            //        OutlinedTextField(
            //            value = repsValue,
            //            onValueChange = onRepsValueChange,
            //            modifier = Modifier
            ////                .weight(0.2f)
            //                .width(52.dp)
            //                .padding(vertical = 16.dp),
            //            label = { Text("Reps") },
            //            singleLine = true
            //        )
            IconButton(
                onClick =
                onDeleteClicked
//                                    openDialog = true
//                                    exerciseTitle = it

            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Exercise",
                    tint = MaterialTheme.colors.onSurface
                )
            }
            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange,
                //            modifier = Modifier.weight(0.3f)
            )
        }
    }
}

@Composable
fun TextFieldBoxes(text: String, value: String, onValueChange: (String) -> Unit) {
    Column(
//        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = text, fontStyle = MaterialTheme.typography.subtitle2.fontStyle)
        Box(
            contentAlignment = Alignment.Center,

            modifier = Modifier
                .width(60.dp)
                .height(54.dp)
//            .border(
//                width = 5.dp
//            )
        ) {
            TextField(
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
//                keyboardActions = KeyboardActions(onDone = ),
                value = value,
                onValueChange = onValueChange,
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Center
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.background,
                    unfocusedIndicatorColor = MaterialTheme.colors.background,

                    )
            )
        }
    }
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