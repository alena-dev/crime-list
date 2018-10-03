package com.g.e.criminalinternet.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.g.e.criminalinternet.database.CrimeBaseHelper;
import com.g.e.criminalinternet.database.CrimeDbSchema;
import com.g.e.criminalinternet.database.CrimeDbSchema.CrimeTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {

    // sName - for Android that is static
    private static CrimeLab sCrimeLab;

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
    }

    public void addCrime(Crime crime) {
        ContentValues values = getContentValues(crime);
        mDataBase.insert(CrimeTable.NAME, null, values);
    }


    public List<Crime> getCrimesList() {

        return new ArrayList<>();
    }

    public int getSize() {

        return 0;
    }

    public Crime getCrime(int position) {

        return null;
    }

    public Crime getCrime(UUID id) {
//        for (Crime crime : mCrimesList) {
//            if (crime.getId().equals(id)) return crime;
//        }
        return null;
    }

    public void updateCrime(Crime crime) {
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValues(crime);

        mDataBase.update(CrimeTable.NAME, values,
                CrimeTable.Cols.UUID + "=?",
                new String[]{uuidString});
    }

    private static ContentValues getContentValues(Crime crime) {
        ContentValues values = new ContentValues();
        values.put(CrimeTable.Cols.UUID, crime.getId().toString());
        values.put(CrimeTable.Cols.TITLE, crime.getTitle());
        values.put(CrimeTable.Cols.DATE, crime.getDate().getTime());
        values.put(CrimeTable.Cols.SOLVED, crime.isSolved() ? 1 : 0);
        return values;
    }
}
