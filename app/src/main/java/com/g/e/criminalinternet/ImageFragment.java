package com.g.e.criminalinternet;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.g.e.criminalinternet.controller.PictureUtils;

import java.io.File;

public class ImageFragment extends DialogFragment {

    private static final String ARG_IMAGE_FILE = "image";

    private ImageView mImageView;

    public static ImageFragment createInstance(File imageFile) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_IMAGE_FILE, imageFile);

        ImageFragment fragment = new ImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dialog_image, container, false);

        mImageView = view.findViewById(R.id.image_dialog);
        File imageFile = (File) getArguments().getSerializable(ARG_IMAGE_FILE);


        Bitmap bitmap = PictureUtils
                .getScaledBitmap(imageFile.getPath(), getActivity());
        mImageView.setImageBitmap(bitmap);

        return view;
    }
}
