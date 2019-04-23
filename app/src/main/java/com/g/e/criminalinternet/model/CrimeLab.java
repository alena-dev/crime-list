package com.g.e.criminalinternet.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.g.e.criminalinternet.database.CrimeBaseHelper;
import com.g.e.criminalinternet.database.CrimeCursorWrapper;
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

    public void removeCrime(Crime crime){
        mDataBase.delete(CrimeTable.NAME,
                CrimeTable.Cols.UUID + "=?",
                new String[]{crime.getId().toString()});

    }

    public List<Crime> getCrimesList() {
        List<Crime> crimeList = new ArrayList<>();
        try (CrimeCursorWrapper cursor = queryCrimes(null,
                null)) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                crimeList.add(cursor.getCrime());
                cursor.moveToNext();
            }
        }

        return crimeList;
    }

    public int getSize() {

        CrimeCursorWrapper cursor = queryCrimes(null,
                null);
        try {
            return cursor.getCount();
        } finally {
            cursor.close();
        }
    }

    public Crime getCrime(UUID id) {
        CrimeCursorWrapper cursor = queryCrimes(
                CrimeTable.Cols.UUID + "=?",
                new String[]{id.toString()}
        );
        try {
            if (cursor.getCount() == 0) return null;
            cursor.moveToFirst();
            return cursor.getCrime();
        } finally {
            cursor.close();
        }
    }

    public void updateCrime(Crime crime) {
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValues(crime);

        mDataBase.update(CrimeTable.NAME, values,
                CrimeTable.Cols.UUID + "=?",
                new String[]{uuidString});
    }

    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDataBase.query(
                CrimeTable.NAME,
                null, //if null - all columns
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new CrimeCursorWrapper(cursor);
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
