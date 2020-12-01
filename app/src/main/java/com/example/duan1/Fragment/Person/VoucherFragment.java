package com.example.duan1.Fragment.Person;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.duan1.Fragment.Favorite.PagerFavorite;
import com.example.duan1.R;
import com.google.android.material.tabs.TabLayout;

public class VoucherFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.voucher_activity, container, false);


        TabLayout tabLayout = view.findViewById(R.id.tabLayoutPerson);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.effective));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.used));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.expired));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager = view.findViewById(R.id.pagePerson);
        final PagerFavorite adapter = new PagerFavorite
                (getFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        return view;
    }
}
