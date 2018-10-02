package com.g.e.criminalinternet.controller;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import com.g.e.criminalinternet.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerFragment extends DialogFragment {

    private static final String ARG_DATE="date";
    public static final String EXTRA_DATE = "com.g.e.criminalinternet.date";

    private DatePicker mDatePicker;
    private Button mPositiveButton;

    public static DatePickerFragment createInstance(Date date){
        Bundle args=new Bundle();
        args.putSerializable(ARG_DATE,date);

        DatePickerFragment fragment=new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @ Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        Date date = (Date)getArguments().getSerializable(ARG_DATE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_date_fragment, null);

        mDatePicker = (DatePicker)view.findViewById(R.id.dialog_date_picker);
        mDatePicker.init(year, month, day, null);

        mPositiveButton = (Button)view.findViewById(R.id.dialog_date_positive_button);
        mPositiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedYear = mDatePicker.getYear();
                int selectedMonth = mDatePicker.getMonth();
                int selectedDayOfMonth = mDatePicker.getDayOfMonth();
                Date date = new GregorianCalendar(selectedYear, selectedMonth, selectedDayOfMonth)
                        .getTime();
                sendResult(Activity.RESULT_OK, date);
                getActivity().getSupportFragmentManager().beginTransaction().remove(DatePickerFragment.this).commit();
            }
        });


        return view;
    }

    public void sendResult(int resultCode, Date date){
        if(getTargetFragment()==null) return;

        Intent intent=new Intent();
        intent.putExtra(EXTRA_DATE, date);

        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), resultCode, intent);

    }
}
