package com.mcp1993.recyclerviewdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Movie> list= new ArrayList<>() ;
    private RecyclerView recyclerView;
    private MoviesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView= (RecyclerView) findViewById(R.id.recycler_view);
        adapter=new MoviesAdapter(list);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        //水平方向，默认竖直方向
//        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Movie movie = list.get(position);
                Toast.makeText(MainActivity.this, movie.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        prepareMovieData();
    }
    private void prepareMovieData() {
        Movie movie = new Movie("Mad Max: Fury Road", "Action & Adventure", "2015");
        list.add(movie);

        movie = new Movie("Inside Out", "Animation, Kids & Family", "2015");
        list.add(movie);

        movie = new Movie("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
        list.add(movie);

        movie = new Movie("Shaun the Sheep", "Animation", "2015");
        list.add(movie);

        movie = new Movie("The Martian", "Science Fiction & Fantasy", "2015");
        list.add(movie);

        movie = new Movie("Mission: Impossible Rogue Nation", "Action", "2015");
        list.add(movie);

        movie = new Movie("Up", "Animation", "2009");
        list.add(movie);

        movie = new Movie("Star Trek", "Science Fiction", "2009");
        list.add(movie);

        movie = new Movie("The LEGO Movie", "Animation", "2014");
        list.add(movie);

        movie = new Movie("Iron Man", "Action & Adventure", "2008");
        list.add(movie);

        movie = new Movie("Aliens", "Science Fiction", "1986");
        list.add(movie);

        movie = new Movie("Chicken Run", "Animation", "2000");
        list.add(movie);

        movie = new Movie("Back to the Future", "Science Fiction", "1985");
        list.add(movie);

        movie = new Movie("Raiders of the Lost Ark", "Action & Adventure", "1981");
        list.add(movie);

        movie = new Movie("Goldfinger", "Action & Adventure", "1965");
        list.add(movie);

        movie = new Movie("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014");
        list.add(movie);

        adapter.notifyDataSetChanged();
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private MainActivity.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final MainActivity.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }


}
