package com.mcp1993.recyclerviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by mcp1993 on 2016/10/9 0009.
 */

public class WaterFallActivity extends AppCompatActivity implements MyAdapter.OnItemClickListener{
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    List<Integer> resIds;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waterfall);
        recyclerView= (RecyclerView) findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager =
                new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        adapter=new MyAdapter(getData());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2,10,true));

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setHasFixedSize(true);

        recyclerView.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener(){
            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                super.onTouchEvent(rv, e);
            }
        });
        adapter.setOnItemClickListener(this);

    }

    private List<Integer> getData() {
        resIds = new ArrayList<>();

        resIds.add(R.drawable.meizhi003);
        resIds.add(R.drawable.meizhi014);
        resIds.add(R.drawable.meizhi002);
        resIds.add(R.drawable.meizhi009);
        resIds.add(R.drawable.meizhi001);
        resIds.add(R.drawable.meizhi004);
        resIds.add(R.drawable.meizhi005);
        resIds.add(R.drawable.meizhi006);
        resIds.add(R.drawable.meizhi008);
        resIds.add(R.drawable.meizhi010);
        resIds.add(R.drawable.meizhi012);
        resIds.add(R.drawable.meizhi013);
        resIds.add(R.drawable.meizhi015);
        return resIds;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recylerview,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                resIds.add(0,resIds.get(new Random().nextInt(resIds.size())));
                adapter.notifyItemInserted(0);
                break;
            case R.id.del:
                resIds.remove(0);
                adapter.notifyItemRemoved(0);
                break;
            case R.id.resort:
                Collections.shuffle(resIds);
                adapter.notifyItemRangeChanged(0,adapter.getItemCount());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(RecyclerView.ViewHolder VH, int position) {
        Toast.makeText(this,"position:" + position,Toast.LENGTH_SHORT).show();
    }
}
