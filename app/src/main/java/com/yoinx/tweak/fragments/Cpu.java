package com.yoinx.tweak.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yoinx.tweak.R;
import com.yoinx.tweak.utils.Utils;

public class Cpu extends Fragment implements View.OnClickListener {
    View view;
    Button mButton;
    Utils mUtils;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        view = inflater.inflate(R.layout.fragment_cpu, container, false);
        mButton = (Button) view.findViewById(R.id.button);
        mButton.setOnClickListener(this);
        mUtils = new Utils(getActivity().getApplicationContext());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("CPU");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                mUtils.connectService();
                //String filePath = "/data/local/tmp/test.txt";
                String filePath = "/sys/devices/virtual/graphics/fb0/hbm";
                Log.i("Tweak", "File Exists: " + mUtils.existFile(filePath));
                Log.i("Tweak", "File Writable: " + mUtils.canWriteFile(filePath));
                Log.i("Tweak", "Read File: " + mUtils.readFile(filePath));
                //Log.i("Tweak", "Write File: " + mUtils.writeFile(filePath, "Text from app \n"));
                Log.i("Tweak", "Version: " + mUtils.getVersion());

                if (mUtils.existFile(filePath)) {
                    if (mUtils.getBool(filePath)) {
                        mUtils.setBool(filePath, false);
                    } else {
                        mUtils.setBool(filePath, true);
                    }
                }

                break;
        }
    }
}