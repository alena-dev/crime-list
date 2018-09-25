package com.g.e.criminalinternet;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {

    // sName - for Android that is static
    private static CrimeLab sCrimeLab;
    private List<Crime> mCrimesList;

    public static CrimeLab get(Context context){
        if(sCrimeLab==null)
            sCrimeLab=new CrimeLab(context);

        return sCrimeLab;
    }

    private CrimeLab (Context context){
        mCrimesList=new ArrayList<>();

        // temporary using e.g.
        for (int i=0; i<100; i++){
            Crime crime= new Crime();
            crime.setTitle("Crime #"+i);
            crime.setSolved(i%2==0); //for every second obj
            mCrimesList.add(crime);
        }
    }

    public List<Crime> getCrimesList(){
        return mCrimesList;
    }

    public Crime getCrime(UUID id){
        for(Crime crime:mCrimesList){
            if(crime.getId().equals(id)) return crime;
        }
        return null;
    }
}
