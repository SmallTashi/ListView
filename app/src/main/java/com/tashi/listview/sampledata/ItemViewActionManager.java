package com.tashi.listview.sampledata;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.tashi.listview.sampledata.ItemTouchHelperAdapter;

public class ItemViewActionManager extends ItemTouchHelper.Callback {
    private ItemTouchHelperAdapter adapter;
    public ItemViewActionManager(ItemTouchHelperAdapter adapter) {
        this.adapter=adapter;
    }
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //设置可滑动的方向
        int dragFlags = ItemTouchHelper.UP|ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.LEFT;
        //返回 makeMovementFlags(int DragFlags, int SwipeFlags)
        return makeMovementFlags(dragFlags,swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

       adapter.onItemMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
       return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        adapter.onItemDelete(viewHolder.getAdapterPosition());
    }
}
