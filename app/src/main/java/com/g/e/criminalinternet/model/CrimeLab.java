package com.g.e.criminalinternet.model;

import android.content.Context;
import android.os.SystemClock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CrimeLab {

    // sName - for Android that is static
    private static CrimeLab sCrimeLab;
    private List<Crime> mCrimesList;
    private HashMap<UUID, Crime> mCrimesHashMap;

    public static CrimeLab get(Context context){
        if(sCrimeLab==null)
            sCrimeLab=new CrimeLab(context);

        return sCrimeLab;
    }

    private CrimeLab (Context context){
        mCrimesList=new ArrayList<>();
        mCrimesHashMap=new HashMap<>();

        // temporary using e.g.
        for (int i=0; i<100; i++){
            Crime crime= new Crime();
            crime.setTitle("Crime #"+i);
            crime.setSolved(i%2==0); //for every second obj
//            crime.setRequiredPolice(i%2==0); //for every second obj
            mCrimesList.add(crime);
            mCrimesHashMap.put(crime.getId(), crime);
        }
    }

    public List<Crime> getCrimesList(){
        return mCrimesList;
    }

    public Crime getCrime(UUID id){
//        SystemClock.sleep(300);
//        for(Crime crime:mCrimesList){
//            if(crime.getId().equals(id)) return crime;
//        }
//        return null;
        return mCrimesHashMap.get(id);


    }
}
