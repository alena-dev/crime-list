package com.g.e.criminalinternet.controller;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
//import android.support.v17.leanback.widget.picker.TimePicker;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

import com.g.e.criminalinternet.R;

public class TimePickerFragment extends DialogFragment {

    public static final String EXTRA_TIME = "com.g.e.criminalinternet.time";

    private static final String ARG_TIME = "time";

    private TimePicker mTimePicker;
    private Calendar mCalendar;

    public static TimePickerFragment CreateInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TIME, date);

        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Date date = (Date) getArguments().getSerializable(ARG_TIME);

        mCalendar = Calendar.getInstance();
        mCalendar.setTime(date);

        int hour = mCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = mCalendar.get(Calendar.MINUTE);

        View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_time, null);

        mTimePicker = (TimePicker) view.findViewById(R.id.dialog_time_picker);

        mTimePicker.setIs24HourView(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mTimePicker.setHour(hour);
            mTimePicker.setMinute(minute);
        } else {
            mTimePicker.setCurrentHour(hour);
            mTimePicker.setCurrentMinute(minute);
        }

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(R.string.time_picker_title)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                int selectedHour;
                                int selectedMinute;
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    selectedHour = mTimePicker.getHour();
                                    selectedMinute = mTimePicker.getMinute();
                                } else {
                                    selectedHour = mTimePicker.getCurrentHour();
                                    selectedMinute = mTimePicker.getCurrentMinute();
                                }

                                mCalendar.set(Calendar.HOUR_OF_DAY, selectedHour);
                                mCalendar.set(Calendar.MINUTE,selectedMinute);
                                Date date= mCalendar.getTime();
                                sendResult(Activity.RESULT_OK, date);
                            }
                        })
                .create();
    }

    private void sendResult(int resultCode, Date date) {
        if (getTargetFragment() == null) return;

        Intent intent = new Intent().putExtra(EXTRA_TIME, date);
        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}
