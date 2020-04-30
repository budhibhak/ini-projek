package com.example.movie.view.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movie.R;
import com.example.movie.adapter.NowshowingAdapter;
import com.example.movie.model.nowshowing.NowshowingResultsItem;
import com.example.movie.view.viewmodel.NowshowingViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowshowingFragment extends Fragment {

    private NowshowingAdapter nowshowingAdapter;
    private RecyclerView rvNowshowing;
    private NowshowingViewModel nowshowingViewModel;


    public NowshowingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nowshowing, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nowshowingAdapter = new NowshowingAdapter(getContext());
        nowshowingAdapter.notifyDataSetChanged();

        rvNowshowing = view.findViewById(R.id.fragmentnowshowing_rv);
        rvNowshowing.setLayoutManager(new GridLayoutManager(getContext(), 2));

        nowshowingViewModel= new ViewModelProvider(this).get(NowshowingViewModel.class);
        nowshowingViewModel.setNowshowingMovie();
        nowshowingViewModel.getNowshowing().observe(this, getNowshowing);

        rvNowshowing.setAdapter(nowshowingAdapter);
    }
    private Observer<ArrayList<NowshowingResultsItem>> getNowshowing = new Observer<ArrayList<NowshowingResultsItem>>() {
        @Override
        public void onChanged(ArrayList<NowshowingResultsItem> nowshowingResultsItems) {
            if (nowshowingResultsItems!= null){
                nowshowingAdapter.setData(nowshowingResultsItems);
            }
        }
    };
}
