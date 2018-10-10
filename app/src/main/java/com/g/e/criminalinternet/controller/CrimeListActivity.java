package com.g.e.criminalinternet.controller;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.g.e.criminalinternet.R;
import com.g.e.criminalinternet.model.Crime;

public class CrimeListActivity extends SingleFragmentActivity
implements CrimeListFragment.Callbacks{

    @Override
    public void onCrimeSelected(Crime crime) {
        if(findViewById(R.id.detail_fragment_container)==null){
            Intent intent=CrimePagerActivity.createIntent(this,
                    crime.getId());
            startActivity(intent);
        } else {
            Fragment newDetail=CrimeFragment.newInstance(crime.getId());

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_container, newDetail)
                    .commit();
        }
    }

    @Override
    protected android.support.v4.app.Fragment createFragment(){
        return new CrimeListFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }
}
