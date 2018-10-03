package com.g.e.criminalinternet.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.g.e.criminalinternet.database.CrimeBaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {

    // sName - for Android that is static
    private static CrimeLab sCrimeLab;

    private List<Crime> mCrimesList;
    //private Context mContext;
    private SQLiteDatabase mDataBase;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null)
            sCrimeLab = new CrimeLab(context);

        return sCrimeLab;
    }

    private CrimeLab(Context context) {
//        mContext = context.getApplicationContext();
        mDataBase = new CrimeBaseHelper(context.getApplicationContext())
                .getWritableDatabase();

        mCrimesList = new ArrayList<>();
    }

    public void addCrime(Crime crime) {
        mCrimesList.add(crime);
    }


    public List<Crime> getCrimesList() {
        return mCrimesList;
    }

    public int getSize() {
        return mCrimesList.size();
    }

    public Crime getCrime(int position) {
        return mCrimesList.get(position);
    }

    public Crime getCrime(UUID id) {
        for (Crime crime : mCrimesList) {
            if (crime.getId().equals(id)) return crime;
        }
        return null;
    }
}
