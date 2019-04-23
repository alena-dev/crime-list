package com.g.e.criminalinternet.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.g.e.criminalinternet.R;
import com.g.e.criminalinternet.model.Crime;
import com.g.e.criminalinternet.model.CrimeLab;

import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity{

    private static final String EXTRA_CRIME_ID="com.g.e.criminalinternet.crime_id";

    private ViewPager mViewPager;
    private CrimeLab mCrimeLab;

    public static Intent createIntent(Context packageContext, UUID crimeId){
        Intent intent= new Intent(packageContext, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        UUID crimeId = (UUID)getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        mViewPager = (ViewPager)findViewById(R.id.crime_view_pager);
        mCrimeLab =CrimeLab.get(this);

        FragmentManager fragmentManager=getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {

            @Override
            public Fragment getItem(int position) {
                Crime crime= mCrimeLab.getCrime(position);
                return CrimeFragment.createInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimeLab.getSize();
            }
        });

        for(int i=0; i<mCrimeLab.getSize(); i++){
            if(mCrimeLab.getCrime(i).getId().equals(crimeId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
