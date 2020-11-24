package com.example.duan1.Fragment.Favorite;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.duan1.Fragment.Favorite.FragmentFavorite.JustSavedFragment;
import com.example.duan1.Fragment.Favorite.FragmentFavorite.RecentlyFragment;

public class PagerFavorite extends FragmentStatePagerAdapter {

    int mFavoriteTabs;

    public PagerFavorite(FragmentManager fm, int FavoriteTabs) {

        super(fm);
        this.mFavoriteTabs = FavoriteTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                JustSavedFragment justSavedFragment = new JustSavedFragment();
                return justSavedFragment;
            case 1:
                RecentlyFragment recentlyFragment = new RecentlyFragment();
                return recentlyFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mFavoriteTabs;
    }
}
