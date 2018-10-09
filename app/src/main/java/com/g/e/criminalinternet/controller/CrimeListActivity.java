package com.g.e.criminalinternet.controller;

import com.g.e.criminalinternet.R;

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected android.support.v4.app.Fragment createFragment(){
        return new CrimeListFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }
}
