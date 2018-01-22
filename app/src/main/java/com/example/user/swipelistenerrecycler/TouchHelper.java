package com.example.user.swipelistenerrecycler;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.user.swipelistenerrecycler.recycler.RecyclerAdapter;;

/**
 * Created by user on 22-Jan-18.
 */

public class TouchHelper extends ItemTouchHelper.SimpleCallback {


  //RecyclerAdapter adapter;
  RecyclerTouchListerner listerner;


  public TouchHelper(int dragDirs, int swipeDirs,RecyclerTouchListerner listerner) {
    super(dragDirs, swipeDirs);
    this.listerner = listerner;
  }


 /* public TouchHelper( RecyclerAdapter adapter) {
    super(ItemTouchHelper.UP | ItemTouchHelper.DOWN , ItemTouchHelper.RIGHT);
    this.adapter = adapter;

  }*/






    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
    listerner.OnSwiped(viewHolder,direction,viewHolder.getAdapterPosition());



    }

    public interface RecyclerTouchListerner{
       void OnSwiped(RecyclerView.ViewHolder holder,int direction,int position);
    }

  @Override
  public int convertToAbsoluteDirection(int flags, int layoutDirection) {
    return super.convertToAbsoluteDirection(flags, layoutDirection);
  }


  @Override
  public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
    if(viewHolder!= null){
      final View foreground = ((RecyclerAdapter.MyHolder)viewHolder).linearLayout;
      getDefaultUIUtil().onSelected(foreground);
    }
  }

  @Override
  public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
    final View foreground = ((RecyclerAdapter.MyHolder)viewHolder).linearLayout;
    getDefaultUIUtil().clearView(foreground);
  }

  @Override
  public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
    final View foreground = ((RecyclerAdapter.MyHolder)viewHolder).linearLayout;
    getDefaultUIUtil().onDrawOver(c,recyclerView,foreground,dX,dY,actionState,isCurrentlyActive);
  }


  @Override
  public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
    final View foreground = ((RecyclerAdapter.MyHolder)viewHolder).linearLayout;
    getDefaultUIUtil().onDraw(c,recyclerView,foreground,dX,dY,actionState,isCurrentlyActive);
  }




}
