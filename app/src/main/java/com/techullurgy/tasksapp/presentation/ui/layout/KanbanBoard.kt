package com.techullurgy.tasksapp.presentation.ui.layout

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.drawscope.draw
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.round
import com.techullurgy.tasksapp.domain.model.KanbanStatusType
import com.techullurgy.tasksapp.presentation.ui.layout.core.rememberKanbanBoardState

/** KanbanBoard is the board which shows Kanban Board UI in horizontal fashion where each page shows elements whose group consists of.
 *
 * Each page is dedicated to one of the keys in groups.
 *
 * You can place element from one page to another page by drag and drop.
 *
 * The dropped element in the new page is always stay at the end of the list.
 *
 *
 * Note: You need to drag the element at least 75% of the element's view in the desired direction to move to the desired page. And triggering time 1.5s, which means, You should not hold the position of element in MovingView (75% of the desired direction) beyond 1.5s in the desired page, If you hold beyond this time, Automatically it will move to the next/previous page.
 *
 * @param groups Data holder of this Kanban Board.
 * @param onDragDrop Once the user is dragging from one page, and dropping to another page, this particular function will be called with the parameters of (FromPageKey, ToPageKey, FromPageIndex)
 * @param dragDropItem Composable function for how the each item should be delivered to the user.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <DragDropItemType> KanbanBoard(
    groups: Map<KanbanStatusType, List<DragDropItemType>>,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(8.dp),
    contentPadding: PaddingValues = PaddingValues(horizontal = 0.dp),
    onDragDrop: (KanbanStatusType, KanbanStatusType, Int) -> Unit,
    emptyItem: @Composable () -> Unit = {},
    dragDropItem: @Composable (DragDropItemType) -> Unit
) {
    val states = groups.map { it.key }
    val elements = groups.map { it.value }

    val state =
        rememberKanbanBoardState(groups = states, elements = elements, onDragDrop = onDragDrop)

    val pagerState = state.pagerState

    Box {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .pointerInput(state.elements) {
                    detectDragGesturesAfterLongPress(
                        onDragStart = { state.onDragStart(it) },
                        onDragCancel = { state.onDragInterrupted() },
                        onDragEnd = { state.onDragInterrupted() },
                        onDrag = { change, _ ->
                            change.consume()
                            state.onDrag(change.position)
                        }
                    )
                }
        ) { pageNo ->
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "${states[pageNo].value} (${elements[pageNo].size})",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .onGloballyPositioned {
                            state.layoutCoordinates = it
                        },
                    verticalArrangement = verticalArrangement,
                    state = state.lazyListStates[pageNo],
                    contentPadding = contentPadding
                ) {
                    if (elements[pageNo].isNotEmpty()) {
                        itemsIndexed(elements[pageNo]) { index, item ->
                            Box(
                                modifier = Modifier
                                    .run {
                                        if (state.desiredIndex == index) {
                                            drawWithContent {
                                                val pictureCanvas =
                                                    Canvas(
                                                        state.picture.beginRecording(
                                                            size.width.toInt(),
                                                            size.height.toInt()
                                                        )
                                                    )
                                                draw(this, layoutDirection, pictureCanvas, size) {
                                                    this@drawWithContent.drawContent()
                                                }
                                                state.picture.endRecording()
                                            }
                                        } else this
                                    }
                            ) {
                                dragDropItem.invoke(item)
                            }
                        }
                    } else {
                        item {
                            Box(modifier = Modifier.fillParentMaxSize()) {
                                emptyItem()
                            }
                        }
                    }
                }
            }
        }
        state.image?.let {
            Box(
                modifier = Modifier
                    .matchParentSize()
            ) {
                Image(
                    bitmap = it,
                    contentDescription = null,
                    modifier = Modifier
                        .layout { measurable, constraints ->
                            val placeable = measurable.measure(constraints)
                            layout(placeable.width, placeable.height) {
                                placeable.place(state.currentOffset.round())
                            }
                        }
                )
            }
        }
    }
}