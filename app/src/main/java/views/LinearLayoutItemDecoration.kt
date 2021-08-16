package views

import android.graphics.Rect
import androidx.recyclerview.widget.LinearLayoutManager

internal object LinearLayoutViewItemDecoration {
    fun getItemOffsets(
        outRect: Rect,
        linearLayoutManager: LinearLayoutManager,
        position: Int,
        itemCount: Int,
        space: Int
    ) {
        outRect.top = space
        outRect.left = space
        if (linearLayoutManager.canScrollHorizontally()) {
            if (position == itemCount - 1) {
                outRect.right = space
            } else {
                outRect.right = 0
            }
            outRect.bottom = space
        } else if (linearLayoutManager.canScrollVertically()) {
            outRect.right = space
            if (position == itemCount - 1) {
                outRect.bottom = space
            } else {
                outRect.bottom = 0
            }
        }
    }
}