package com.example.duan1.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.duan1.Adapter.MenuItemsAdapter;
import com.example.duan1.Adapter.PopularAdapter;
import com.example.duan1.Adapter.RecommendedAdapter;
import com.example.duan1.Model.Popular;
import com.example.duan1.Model.ProductDrink;
import com.example.duan1.Model.Recommended;
import com.example.duan1.R;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    PopularAdapter popularAdapter;
    RecommendedAdapter recommendedAdapter;
    MenuItemsAdapter menuItemsAdapter;
    View view;
    RecyclerView recyclerPopular;
    RecyclerView recyclerRecommended;
    RecyclerView recyclerMenuItems;
    List<Popular> mList;
    List<Recommended> List;
    List<ProductDrink> nList;
    ArrayList<Popular> arrayListNameFood;

    private void setContentView(int activity_main) {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_home,container,false);

        recyclerPopular = view.findViewById(R.id.popular_recycler);
        recyclerRecommended = view.findViewById(R.id.recommended_recycler);
        recyclerMenuItems = view.findViewById(R.id.all_menu_recycler);

        popularAdapter = new PopularAdapter(mList,getContext());
        recyclerPopular.setAdapter(popularAdapter);
        recyclerPopular.setLayoutManager(new LinearLayoutManager(getActivity()));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerPopular.setLayoutManager(layoutManager);

        recommendedAdapter = new RecommendedAdapter(List, getContext());
        recyclerRecommended.setAdapter(recommendedAdapter);
        recyclerRecommended.setLayoutManager(new LinearLayoutManager(getActivity()));
        LinearLayoutManager layoutManagerRecommended = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerRecommended.setLayoutManager(layoutManagerRecommended);

        menuItemsAdapter = new MenuItemsAdapter(nList, getContext());
        recyclerMenuItems.setAdapter(menuItemsAdapter);
        recyclerMenuItems.setLayoutManager(new LinearLayoutManager(getActivity()));
        LinearLayoutManager layoutManagerMenuItems = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerRecommended.setLayoutManager(layoutManagerMenuItems);
        setData();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        setData();
    }
    public void setData(){
        mList = new ArrayList<>();
        mList.add(new Popular(R.drawable.comga,"Cơm Gà") );
        mList.add(new Popular(R.drawable.humberger,"Humberger") );
        mList.add(new Popular(R.drawable.ic_banhxeo,"Bánh Xèo") );
        mList.add(new Popular(R.drawable.comga,"Cơm Gà") );
        mList.add(new Popular(R.drawable.humberger,"Humberger") );
        mList.add(new Popular(R.drawable.ic_banhxeo,"Bánh Xèo") );
        mList.add(new Popular(R.drawable.comga,"Cơm Gà") );
        mList.add(new Popular(R.drawable.humberger,"Humberger") );
        mList.add(new Popular(R.drawable.ic_banhxeo,"Bánh Xèo") );

        List = new ArrayList<>();
        List.add(new Recommended(R.drawable.comga, "Cơm Gà"));
        List.add(new Recommended(R.drawable.humberger, "Humberger"));
        List.add(new Recommended(R.drawable.comga, "Cơm Gà"));
        List.add(new Recommended(R.drawable.humberger, "Humberger"));
        List.add(new Recommended(R.drawable.comga, "Cơm Gà"));
        List.add(new Recommended(R.drawable.humberger, "Humberger"));
    }
}
