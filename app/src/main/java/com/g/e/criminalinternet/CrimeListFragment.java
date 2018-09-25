package com.g.e.criminalinternet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class CrimeListFragment extends Fragment {

    private RecyclerView mCrimeRecycleView;
    private CrimeAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_crime_list,
                container, false);
        mCrimeRecycleView=(RecyclerView)view
                .findViewById(R.id.crime_recycler_view);
        mCrimeRecycleView.setLayoutManager(new LinearLayoutManager
                (getActivity()));

        updateUI();

        return view;
    }

    private void updateUI(){
        CrimeLab crimeLab=CrimeLab.get(getActivity());
        List<Crime> crimesList =crimeLab.getCrimesList();
        mAdapter=new CrimeAdapter(crimesList);
        mCrimeRecycleView.setAdapter(mAdapter);
    }


    // imlementation ViewHolder
    private class CrimeHolder extends RecyclerView.ViewHolder{

        public CrimeHolder (LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_crime,
                    parent, false));
        }
    }

    // imlementation Adapter
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{

        private List<Crime> mCrimesList;

        public CrimeAdapter(List<Crime> crimesList){
            mCrimesList=crimesList;
        }

        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater=LayoutInflater.from(getActivity());
            return new CrimeHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {
        // empty temporary
        }

        @Override
        public int getItemCount() {
            return mCrimesList.size();
        }
    }
}
