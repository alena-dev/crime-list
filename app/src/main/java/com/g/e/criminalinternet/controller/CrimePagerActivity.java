package com.g.e.criminalinternet.controller;

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

public class CrimePagerActivity extends AppCompatActivity{

    private ViewPager mViewPager;
    private CrimeLab mCrimeLab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        mViewPager = (ViewPager)findViewById(R.id.crime_view_pager);
        mCrimeLab =CrimeLab.get(this);

        FragmentManager fragmentManager=getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Crime crime= mCrimeLab.getCrime(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimeLab.getSize();
            }
        });
    }
}
