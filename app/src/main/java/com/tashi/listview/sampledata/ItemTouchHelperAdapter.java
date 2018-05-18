package com.tashi.listview.sampledata;

public interface ItemTouchHelperAdapter {
    void onItemMove(int fromPosition,int toPosition);
    void onItemDelete(int position);
}
