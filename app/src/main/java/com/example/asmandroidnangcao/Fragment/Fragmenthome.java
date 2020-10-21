package com.example.asmandroidnangcao.Fragment;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.asmandroidnangcao.AdapterRss.FeedAdapter;
import com.example.asmandroidnangcao.AdapterSlide.AdapterViewPayer;
import com.example.asmandroidnangcao.AdapterSlide.ModelItem;


import com.example.asmandroidnangcao.Common.HTTPDataHandler;
import com.example.asmandroidnangcao.MainActivity;
import com.example.asmandroidnangcao.Model.Item;
import com.example.asmandroidnangcao.Model.RSSObject;
import com.example.asmandroidnangcao.R;

import com.google.gson.Gson;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fragmenthome extends Fragment {
    TextView hello;
    SliderView sliderView;
    RecyclerView recyclerView;
    TextView txttenface,txtemailface;

    private AdapterViewPayer adapter;
    private ArrayList<Item> arrayItem = new ArrayList<>();
    private FeedAdapter feedAdapter;
    public final String RSS_link = "https://vnexpress.net/rss/giao-duc.rss";
    private final String RSS_to_Json_API = "https://api.rss2json.com/v1/api.json?rss_url=";
    CircleImageView profile_image;
    private ProgressDialog mDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmenthome, container, false);
        hello = view.findViewById(R.id.hello);
        sliderView = view.findViewById(R.id.imageSlider);
        recyclerView = view.findViewById(R.id.rcyrss);

        txttenface= view.findViewById(R.id.txttenface);
        txtemailface=view.findViewById(R.id.txtemailface);
        profile_image=view.findViewById(R.id.profile_image);

        txtemailface.setText(MainActivity.emailface);
        txttenface.setText(MainActivity.tendem);
        Picasso.with(getActivity()).load(MainActivity.imgface).into(profile_image);
        adapter = new AdapterViewPayer(getContext());
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();
        Date currentTime = Calendar.getInstance().getTime();
        if (currentTime.getHours() > 12) {
            hello.setText("Hello Huy,Chào Buổi Chiều !");
        } else {
            hello.setText("Hello Huy,Chào Buổi Sáng !");
        }
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);
        feedAdapter = new FeedAdapter(arrayItem);
        recyclerView.setAdapter(feedAdapter);

        mDialog = new ProgressDialog(getActivity());
        mDialog.setMessage("Chờ chút ");
        mDialog.setCancelable(false);

        new loadRSSAsync().execute(RSS_to_Json_API + RSS_link);


        renewItems(view);
        removeLastItem(view);
        addNewItem(view);
        return view;
    }

    public void renewItems(View view) {
        List<ModelItem> sliderItemList = new ArrayList<>();
        //dummy data
        for (int i = 0; i < 5; i++) {
            ModelItem sliderItem = new ModelItem();
//            sliderItem.setDescription("Slider Item " + i);
            if (i % 2 == 0) {
                sliderItem.setImageurl("https://i.chungta.vn/2020/09/10/660nhtng-1599705360.png");
            } else {
                sliderItem.setImageurl("https://caodang.fpt.edu.vn/wp-content/uploads/Back-to-School-scaled.jpg");
            }
            sliderItemList.add(sliderItem);
        }
        adapter.ViewPagerAdapter(sliderItemList);
    }

    public void removeLastItem(View view) {
        if (adapter.getCount() - 1 >= 0)
            adapter.deleteitem(adapter.getCount() - 1);
    }

    public void addNewItem(View view) {
        ModelItem sliderItem = new ModelItem();
//        sliderItem.setDescription("Re");
        sliderItem.setImageurl("https://i.chungta.vn/2020/07/01/640-FE-2640-1593601081.png");
        adapter.addItem(sliderItem);
    }

    private class loadRSSAsync extends AsyncTask<String, String, String>{

        @Override
        protected void onPreExecute() {
            // mDialog.isShowing();
            mDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String result;
            HTTPDataHandler http = new HTTPDataHandler();
            result = http.GetHTTPData(params[0]);
            return  result;
        }

        @Override
        protected void onPostExecute(String s) {
            RSSObject rssObject = new Gson().fromJson(s,RSSObject.class);

            arrayItem.clear();
            arrayItem.addAll(rssObject.getItems());
            feedAdapter.notifyDataSetChanged();
            if(mDialog.isShowing())

                // mDialog.isShowing();
                mDialog.dismiss();
        }
    }
}
