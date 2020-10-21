package com.example.asmandroidnangcao.Dialog;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;


import com.example.asmandroidnangcao.Adapter.AdapterKhoaHoc;
import com.example.asmandroidnangcao.DAO.DAOKHOAHOC;
import com.example.asmandroidnangcao.Model.KhoaHoc;

import com.example.asmandroidnangcao.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static android.app.Activity.RESULT_OK;
import static androidx.core.content.ContextCompat.checkSelfPermission;
import static com.example.asmandroidnangcao.Fragment.Fragmentclass.adapterKhoaHoc;
import static com.example.asmandroidnangcao.Fragment.Fragmentclass.rcvkhoahoc;


public class Bottom_Sheft_Add_KhoaHoc extends BottomSheetDialogFragment {
    EditText edt_makh,edt_tenkh,edt_tien;
    TextView tv_date;
    Button btn_add,btnaddhinh;
    ImageView imghinhshow;
    ArrayList<KhoaHoc> datakhoahoc;
    DAOKHOAHOC daokhoahoc;
    final int REQUEST_CODE_GALLERY = 999;


    public Bottom_Sheft_Add_KhoaHoc() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomm_sheft_add_khoahoc,container,false);
        edt_makh= view.findViewById(R.id.edt_makh);
        edt_tenkh= view.findViewById(R.id.edt_tenkh);
        btnaddhinh= view.findViewById(R.id.btnaddhinh);
        imghinhshow= view.findViewById(R.id.imghinhshow);
        edt_tien= view.findViewById(R.id.edt_tien);
        tv_date= view.findViewById(R.id.tv_date);
        btn_add= view.findViewById(R.id.btnadd);
        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datapicker();
            }
        });
        btnaddhinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    if (checkSelfPermission((getActivity()),Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED
                            ){
                        // gồm 2 quyền camera và quyền đọc dữ liệu
                        String[] premission ={Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(premission,REQUEST_CODE_GALLERY);
                    }else {
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(intent, REQUEST_CODE_GALLERY);
                    }
                }else {

                }
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String makh = edt_makh.getText().toString();
                String tenkh = edt_tenkh.getText().toString();
                double tien = Double.parseDouble(edt_tien.getText().toString());
                String ngay = tv_date.getText().toString();

                datakhoahoc= new ArrayList<>();
                daokhoahoc = new DAOKHOAHOC(getContext());
               KhoaHoc khoaHoc = new KhoaHoc(null,makh,tenkh,tien,ngay,imageViewToByte(imghinhshow));
                daokhoahoc.insert(khoaHoc);
                datakhoahoc = daokhoahoc.readAll();
                adapterKhoaHoc = new AdapterKhoaHoc(getActivity(), datakhoahoc);
                rcvkhoahoc.setAdapter(adapterKhoaHoc);
                adapterKhoaHoc.notifyDataSetChanged();
                dismiss();

            }
        });

        return view;
    }



    private void datapicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year2, int month2, int dayOfMonth2) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                calendar.set(year2, month2, dayOfMonth2);
                String date = dateFormat.format(calendar.getTime());
                tv_date.setText(date);
            }
        }, year, month, day);
        datePickerDialog.show();
    }
    public byte[] imageViewToByte(ImageView image) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) image.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        //100 là chất lượng
        bitmap.compress(Bitmap.CompressFormat.PNG, 10, stream);
        byte imageInByte[] = stream.toByteArray();
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        options.inSampleSize = 2;
//        BitmapFactory.decodeResource(getResources(), stream, options);

        return imageInByte;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getActivity(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imghinhshow.setImageBitmap(bitmap);

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
