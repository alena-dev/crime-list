package com.g.e.criminalinternet.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.g.e.criminalinternet.R;
import com.g.e.criminalinternet.model.Crime;
import com.g.e.criminalinternet.model.CrimeLab;

import java.util.List;

public class CrimeListFragment extends Fragment {

    private RecyclerView mCrimeRecycleView;
    private CrimeAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list,
                container, false);
        mCrimeRecycleView = (RecyclerView) view
                .findViewById(R.id.crime_recycler_view);
        mCrimeRecycleView.setLayoutManager(new LinearLayoutManager
                (getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_crime_list_menu, menu);
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimesList = crimeLab.getCrimesList();
        if(mAdapter==null) {
            mAdapter = new CrimeAdapter(crimesList);
            mCrimeRecycleView.setAdapter(mAdapter);
        } else{
            mAdapter.notifyDataSetChanged();
        }


    }




    // imlementation ViewHolder
    private class CrimeHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private Crime mCrime;
        private ImageView mSolvedImageView;

        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime,
                    parent, false));

            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView
                    .findViewById(R.id.crime_title);
            mDateTextView = (TextView) itemView
                    .findViewById(R.id.crime_date);
            mSolvedImageView=(ImageView)itemView
                    .findViewById(R.id.crime_solved);
        }

        @Override
        public void onClick(View view){
            Intent intent =CrimePagerActivity.createIntent(getActivity(),mCrime.getId());
            startActivity(intent);
        }

        public void bind(Crime crime) {
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
            mSolvedImageView.setVisibility(crime.isSolved()
                    ? View.VISIBLE : View.GONE);
        }
    }

    // imlementation Adapter
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {

        private List<Crime> mCrimesList;

        public CrimeAdapter(List<Crime> crimesList) {
            mCrimesList = crimesList;
        }

        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CrimeHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {
            Crime crime = mCrimesList.get(position);
            holder.bind(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimesList.size();
        }
    }
}
