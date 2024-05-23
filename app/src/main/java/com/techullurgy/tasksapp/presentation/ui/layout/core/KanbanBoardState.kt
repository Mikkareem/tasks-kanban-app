package com.techullurgy.tasksapp.presentation.ui.layout.core

import android.graphics.Bitmap
import android.graphics.Picture
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyListItemInfo
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInParent
import com.techullurgy.tasksapp.domain.model.KanbanStatusType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val THRESHOLD_WAIT_TIME_TO_MOVE_TO_DESIRED_PAGE = 1500L
private const val THRESHOLD_PERCENTAGE_TO_MOVE_TO_DESIRED_PAGE = .65f

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun <DragDropItemType> rememberKanbanBoardState(
    groups: List<KanbanStatusType>,
    elements: List<List<DragDropItemType>>,
    onDragDrop: (KanbanStatusType, KanbanStatusType, Int) -> Unit
): KanbanBoardState<DragDropItemType> {
    require(groups.size == elements.size)

    val lazyListStates = (1..groups.size).map { rememberLazyListState() }
    val pagerState = rememberPagerState { groups.size }
    val scope = rememberCoroutineScope()

    val picture = remember { Picture() }

    return remember(groups) {
        KanbanBoardState(groups, elements, lazyListStates, picture, pagerState, scope, onDragDrop)
    }
}

@OptIn(ExperimentalFoundationApi::class)
internal class KanbanBoardState<DragDropItemType>(
    private val groups: List<KanbanStatusType>,
    val elements: List<List<DragDropItemType>>,
    val lazyListStates: List<LazyListState>,
    val picture: Picture,
    val pagerState: PagerState,
    private val scope: CoroutineScope,
    private val onDragDrop: (KanbanStatusType, KanbanStatusType, Int) -> Unit
) {
    private val currentPage: Int
        get() = pagerState.currentPage

    private val currentLazyListState: LazyListState
        get() = lazyListStates[currentPage]

    private var isAnimationGoing = false
    private var startPageKey: KanbanStatusType? = null
    private var endPageKey: KanbanStatusType? = null
    private var startIndex = -1

    private var dragDisplacement by mutableStateOf(Offset.Zero)

    private var currentItemIndex by mutableIntStateOf(-1)
    private var currentPageIndex by mutableIntStateOf(-1)

    var currentOffset by mutableStateOf(Offset.Zero)
        private set
    var image by mutableStateOf<ImageBitmap?>(null)
        private set

    val desiredIndex: Int
        get() = if (currentPageIndex == pagerState.currentPage) currentItemIndex else -1


    private val desiredTop get() = (layoutCoordinates?.boundsInParent()?.top ?: 0f).toInt()
    private val desiredBottom get() = (layoutCoordinates?.boundsInParent()?.bottom ?: 0f).toInt()

    var layoutCoordinates: LayoutCoordinates? = null

    private var moveTimeExhausted = true

    fun onDragStart(start: Offset) {
        start
            .takeIf { it.y.toInt() in desiredTop..desiredBottom }
            ?.also {
                currentLazyListState.layoutInfo.visibleItemsInfo
                    .firstOrNull { item -> (it.y.toInt() - desiredTop) in item.offset..item.offsetEnd }
                    ?.also {
                        currentItemIndex = it.index
                        currentPageIndex = pagerState.currentPage
                        dragDisplacement = Offset(x = start.x, y = start.y - it.offset.toFloat())
                        currentOffset = start.copy(y = start.y + desiredTop) - dragDisplacement
                        scope.launch {
                            delay(200)
                            image = picture.createImageBitmap()
                        }
                        startPageKey = groups[pagerState.currentPage]
                        startIndex = it.index
                        endPageKey = groups[pagerState.currentPage]
                    }
            }
    }

    fun onDragInterrupted() {
        if (startPageKey != null && endPageKey != null && startPageKey != endPageKey) {
            onDragDrop(startPageKey!!, endPageKey!!, startIndex)
        }
        currentItemIndex = -1
        currentPageIndex = -1
        image = null
        currentOffset = Offset.Zero
        dragDisplacement = Offset.Zero
        isAnimationGoing = false
        startPageKey = null
        endPageKey = null
        startIndex = -1
    }

    fun onDrag(position: Offset) {
        currentOffset = position.copy(y = position.y + desiredTop) - dragDisplacement

        currentOffset.x
            .takeIf {
                layoutCoordinates != null && it > (layoutCoordinates!!.size.width * THRESHOLD_PERCENTAGE_TO_MOVE_TO_DESIRED_PAGE)
            }
            ?.let {
                moveNextPage()
            }

        currentOffset.x
            .takeIf {
                layoutCoordinates != null && it < (-1 * layoutCoordinates!!.size.width * THRESHOLD_PERCENTAGE_TO_MOVE_TO_DESIRED_PAGE)
            }
            ?.let {
                movePreviousPage()
            }
    }

    private fun moveNextPage() {
        if (pagerState.canScrollForward && moveTimeExhausted) {
            moveTimeExhausted = false
            if (pagerState.canScrollForward) {
                scope.launch {
                    isAnimationGoing = true
                    pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                    recalculate()
                    isAnimationGoing = false
                    delay(THRESHOLD_WAIT_TIME_TO_MOVE_TO_DESIRED_PAGE)
                    moveTimeExhausted = true
                }.invokeOnCompletion {
                    isAnimationGoing = false
                }
            }
        }
    }

    private fun movePreviousPage() {
        if (pagerState.canScrollBackward && moveTimeExhausted) {
            moveTimeExhausted = false
            if (pagerState.canScrollBackward) {
                scope.launch {
                    isAnimationGoing = true
                    pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                    recalculate()
                    isAnimationGoing = false
                    delay(THRESHOLD_WAIT_TIME_TO_MOVE_TO_DESIRED_PAGE)
                    moveTimeExhausted = true
                }.invokeOnCompletion {
                    isAnimationGoing = false
                }
            }
        }
    }

    private fun recalculate() {
        endPageKey = groups[pagerState.currentPage]
    }
}


private val LazyListItemInfo.offsetEnd: Int get() = offset + size


private fun Picture.createImageBitmap(): ImageBitmap {
    val bitmap = Bitmap.createBitmap(
        width,
        height,
        Bitmap.Config.ARGB_8888
    )

    val canvas = android.graphics.Canvas(bitmap)
    canvas.drawColor(android.graphics.Color.LTGRAY)
    canvas.drawPicture(this)
    return bitmap.asImageBitmap()
}