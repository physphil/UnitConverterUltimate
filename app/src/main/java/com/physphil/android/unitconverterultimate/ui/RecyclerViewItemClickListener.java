package com.physphil.android.unitconverterultimate.ui;

/**
 * Interface to handle item clicks in recycler view
 * Created by pshadlyn on 4/24/2015.
 */
public interface RecyclerViewItemClickListener
{
    /**
     * Called when an item is clicked in the recycler view list
     * @param item the selected item
     * @param position the position of the selected item in the list
     */
    void onListItemClicked(Object item, int position);
}
