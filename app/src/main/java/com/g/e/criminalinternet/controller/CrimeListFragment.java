package com.g.e.criminalinternet.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.g.e.criminalinternet.R;
import com.g.e.criminalinternet.model.Crime;
import com.g.e.criminalinternet.model.CrimeLab;

import java.util.List;

public class CrimeListFragment extends Fragment {

    private RecyclerView mCrimeRecycleView;
    private CrimeAdapter mAdapter;

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

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimesList = crimeLab.getCrimesList();
        mAdapter = new CrimeAdapter(crimesList);
        mCrimeRecycleView.setAdapter(mAdapter);
    }

    private abstract class CrimeHolderBase extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected TextView mTitleTextView;
        protected TextView mDateTextView;
        private Crime mCrime;

        public CrimeHolderBase(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView
                    .findViewById(R.id.crime_title);
            mDateTextView = (TextView) itemView
                    .findViewById(R.id.crime_date);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
        Toast.makeText(getActivity(),
                mCrime.getTitle() + "clicked", Toast.LENGTH_SHORT)
                .show();
        }

        public void bind(Crime crime) {
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
        }
    }

    // imlementation ViewHolder
    private class CrimeHolder extends CrimeHolderBase {
        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime,
                    parent, false));
        }
    }

    private class CrimeHolderRequiredPolice extends CrimeHolderBase {
        private final Button mCallPoliceButton;

        public CrimeHolderRequiredPolice(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_serious_crime,
                    parent, false));

            mCallPoliceButton = (Button) itemView
                    .findViewById(R.id.crime_button_call_police);
        }
    }

    // imlementation Adapter
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolderBase> {

        private List<Crime> mCrimesList;
        private final int REQUIRED_POLICE_VIEW_TYPE = 1;
        private final int UNREQUIRED_POLICE_VIEW_TYPE = 2;

        public CrimeAdapter(List<Crime> crimesList) {
            mCrimesList = crimesList;
        }

        @NonNull
        @Override
        public CrimeHolderBase onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CrimeHolderRequiredPolice(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull CrimeHolderBase holder, int position) {
            Crime crime = mCrimesList.get(position);
            holder.bind(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimesList.size();
        }

//        @Override
//        public int getItemViewType(int position) {
//
//            if (mCrimesList.isEmpty()) return 0;
//
//            if (mCrimesList.get(position)
//                    .isRequiresPolice())
//                return REQUIRED_POLICE_VIEW_TYPE;
//
//            return UNREQUIRED_POLICE_VIEW_TYPE;
//        }
    }
}
