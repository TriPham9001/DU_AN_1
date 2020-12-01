package com.example.duan1.Fragment.Person;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.duan1.Fragment.Person.Voucher_fragment.EffectiveFragment;
import com.example.duan1.Fragment.Person.Voucher_fragment.ExpiredFragment;
import com.example.duan1.Fragment.Person.Voucher_fragment.UsedFragment;

public class PagerPerson extends FragmentStatePagerAdapter {

    int mPersonTabs;

    public PagerPerson(FragmentManager fm, int FavoriteTabs) {

        super(fm);
        this.mPersonTabs = FavoriteTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                EffectiveFragment effectiveFragment = new EffectiveFragment();
                return effectiveFragment;
            case 1:
                UsedFragment usedFragment = new UsedFragment();
                return usedFragment;
            case 2:
                ExpiredFragment expiredFragment = new ExpiredFragment();
                return expiredFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mPersonTabs;
    }
}
