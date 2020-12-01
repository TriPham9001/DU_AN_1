package com.example.duan1.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan1.R;
import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.callback.OnPieSelectListener;
import com.razerdp.widget.animatedpieview.data.IPieInfo;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;

public class RevenueFragment extends Fragment {

    AnimatedPieView animatedPieView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragnment_revenue, container, false);

        animatedPieView = view.findViewById(R.id.pieChart);
        AnimatedPieViewConfig config = new AnimatedPieViewConfig();

        config.addData(new SimplePieInfo(15, Color.parseColor("red"), getString(R.string.drink)));
        config.addData(new SimplePieInfo(50, Color.parseColor("blue"), getString(R.string.carried_away)));
        config.addData(new SimplePieInfo(15, Color.parseColor("olive"), getString(R.string.dish_together)));
        config.addData(new SimplePieInfo(20, Color.parseColor("yellow"), getString(R.string.food)));

        config.duration(1000);
        config.drawText(true);
        config.strokeMode(false);
        config.textSize(40);
        config.selectListener(new OnPieSelectListener<IPieInfo>() {
            @Override
            public void onSelectPie(@NonNull IPieInfo pieInfo, boolean isFloatUp) {

                Toast.makeText(getContext(), pieInfo.getDesc() + " - " +
                        pieInfo.getValue(), Toast.LENGTH_SHORT).show();
            }
        });
        config.startAngle(-180);

        animatedPieView.applyConfig(config);
        animatedPieView.start();
        return view;

    }

}
