package com.example.duan1.Fragment.Person;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan1.R;

public class PersonFragment extends Fragment {

    RelativeLayout voucher;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, container, false);

        voucher = view.findViewById(R.id.rltVoucher);

        voucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), VoucherFragment.class);
                startActivity(i);
            }
        });


        return view;
    }
}
