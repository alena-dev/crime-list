package com.g.e.criminalinternet.controller;

import android.content.Context;
import android.content.Intent;

import java.util.UUID;

public class CrimeActivity extends SingleFragmentActivity {

    public static final String EXTRA_CRIME_ID=
            "com.g.e.criminalinternet.model.crime_id";

    public static Intent createIntent(Context packageContext, UUID crimeId){
        Intent intent=new Intent(packageContext,CrimeActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }


    @Override
    protected android.support.v4.app.Fragment createFragment(){
        return new CrimeFragment();
    }
}
