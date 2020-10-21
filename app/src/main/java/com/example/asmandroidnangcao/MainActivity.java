package com.example.asmandroidnangcao;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.asmandroidnangcao.Fragment.Fragmentclass;
import com.example.asmandroidnangcao.Fragment.Fragmentfacebook;
import com.example.asmandroidnangcao.Fragment.Fragmenthome;
import com.example.asmandroidnangcao.Fragment.Fragmentnews;
import com.example.asmandroidnangcao.Fragment.Fragmentstudent;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    MeowBottomNavigation bottommeo;
    public  static  String emailface,imgface,tendem;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottommeo=(MeowBottomNavigation) findViewById(R.id.bottommeo);
        Bundle bundle = getIntent().getExtras();
        if(bundle !=null){
            tendem = bundle.getString("name");
             emailface = bundle.getString("email");
             imgface = bundle.getString("profile_pic");
        }
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.maunen));
        bottommeo.add(new MeowBottomNavigation.Model(1, R.drawable.icons8_google_classroom_24px));
        bottommeo.add(new MeowBottomNavigation.Model(2, R.drawable.icons8_student_male_30px));
        bottommeo.add(new MeowBottomNavigation.Model(3, R.drawable.icons8_home_page_24px));
        bottommeo.add(new MeowBottomNavigation.Model(4, R.drawable.icons8_google_maps_old_24px));
        bottommeo.add(new MeowBottomNavigation.Model(5, R.drawable.icons8_facebook_30px_3));
        if(savedInstanceState==null){

            getSupportFragmentManager().beginTransaction().replace(R.id.fr_l,new Fragmenthome()).commit();

        }

        bottommeo.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
//                Toast.makeText(MainActivity.this, "Click"+item.getId(), Toast.LENGTH_SHORT).show();
            }
        });
        isPermissionGranted();
        bottommeo.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;
                switch (item.getId()){
                    case 1:
                        fragment=new Fragmentclass();
                        break;
                    case 2:
                        fragment=new Fragmentstudent();
                        break;
                    case 3:
                        fragment=new Fragmenthome();
                        break;
                    case 4:
                        fragment=new Fragmentnews();

                        break;
                    case 5:
                        fragment=new Fragmentfacebook();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fr_l,fragment).commit();
            }
        });
    }
    public  boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (this.checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED
                    && this.checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG","Permission is granted");
                return true;
            } else {

                Log.v("TAG","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 2);
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 2);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG","Permission is granted");
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {




        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
            //do your specific task after read phone state granted
        } else {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
        }
        return;
    }
}
