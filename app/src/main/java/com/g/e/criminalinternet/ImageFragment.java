package com.g.e.criminalinternet;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.g.e.criminalinternet.controller.DatePickerFragment;

import java.util.Date;
import java.util.GregorianCalendar;

public class ImageFragment extends DialogFragment{

//    private static final String ARG_DATE="image";

//    public static ImageFragment createInstance (Date date){
//        Bundle args=new Bundle();
//        args.putSerializable(ARG_DATE,date);
//
//        ImageFragment fragment=new ImageFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dialog_image, container, false);


        return view;
    }
}
