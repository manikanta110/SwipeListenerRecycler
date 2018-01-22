package com.example.user.swipelistenerrecycler;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.View;

import com.example.user.swipelistenerrecycler.model.Result;
import com.example.user.swipelistenerrecycler.recycler.RecyclerAdapter;
import com.example.user.swipelistenerrecycler.waste.CartListAdapter;
import com.example.user.swipelistenerrecycler.waste.Item;
import com.example.user.swipelistenerrecycler.waste.RecyclerItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements TouchHelper.RecyclerTouchListerner{

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
   // private List<Item> cartList;
   // private CartListAdapter mAdapter;
    private CoordinatorLayout coordinatorLayout;
    List<Result> resultList;
    RecyclerAdapter adapter;

    // url to fetch menu json
    private static final String URL = "https://api.androidhive.info/json/menu.json";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.my_cart));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupList();

        recyclerView = findViewById(R.id.recycler_view);
        coordinatorLayout = findViewById(R.id.coordinator_layout);
        //cartList = new ArrayList<>();

        adapter =  new RecyclerAdapter(this,resultList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new TouchHelper(0, ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);


        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }



    };
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);

    }

    private void setupList() {

        resultList =  new ArrayList<>();

        for(int i=0;i<=20;i++){
            Result result = new Result("Name "+i,"This is Description "+i);

            resultList.add(result);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds cartList to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public void OnSwiped(RecyclerView.ViewHolder holder, int direction, int position) {
        if(holder instanceof RecyclerAdapter.MyHolder){

            String name = resultList.get(holder.getAdapterPosition()).getName();

            // backup of removed item for undo purpose
            final Result deletedItem = resultList.get(holder.getAdapterPosition());
            final int deletedIndex = holder.getAdapterPosition();

            // remove the item from recycler view
            adapter.dismiss(holder.getAdapterPosition());

            // showing snack bar with Undo option
            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, name + " removed from cart!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // undo is selected, restore the deleted item
                    adapter.restoreItem(deletedItem, deletedIndex);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }

    }
}

