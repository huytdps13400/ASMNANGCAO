package com.example.asmandroidnangcao.Dialog;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.asmandroidnangcao.Adapter.AdapterKhoaHoc;
import com.example.asmandroidnangcao.Adapter.AdapterSinhVien;
import com.example.asmandroidnangcao.DAO.DAOKHOAHOC;
import com.example.asmandroidnangcao.DAO.DAOSINHVIEN;
import com.example.asmandroidnangcao.Model.KhoaHoc;
import com.example.asmandroidnangcao.Model.SinhVien;
import com.example.asmandroidnangcao.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Picasso;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;
import static androidx.core.content.ContextCompat.checkSelfPermission;
import static com.example.asmandroidnangcao.Fragment.Fragmentstudent.rcvsinhvien;


public class Bottom_Sheft_Update_SinhVien extends BottomSheetDialogFragment {
    EditText edt_makh,edt_tenkh,edt_tien;
    Spinner spinermaloai;
    Button btn_add,btnaddhinh;
    ImageView imghinhshow;
    ArrayList<KhoaHoc> datakhoahoc;
    DAOKHOAHOC daokhoahoc;
    ArrayList<SinhVien> datasinhvien;
    DAOSINHVIEN daosinhvien;
    String spinner;
    AdapterSinhVien adapterSinhVien;
    Bitmap theImage;
    private static final int PREMISSION_CODE = 1000;
    Uri image_uri;
    private static final int IMAGE_CAPTURE_CODE = 1001;
    public Bottom_Sheft_Update_SinhVien() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomm_sheft_update_sinhvien,container,false);
        edt_makh= view.findViewById(R.id.edt_makh);
        edt_tenkh= view.findViewById(R.id.edt_tenkh);
        btnaddhinh = view.findViewById(R.id.btnaddhinh);
        imghinhshow = view.findViewById(R.id.imghinhshow);
        edt_tien= view.findViewById(R.id.edt_tien);
        spinermaloai= view.findViewById(R.id.spinermaloai);
        btn_add= view.findViewById(R.id.btnadd);
        final Bundle getupdate = getArguments();
        String masv =getupdate.getString("Masv");
        String tensv =getupdate.getString("Tensv");
        String nganh =getupdate.getString("nganh");
        String khoahoc =getupdate.getString("idkh");
//        String image = getupdate.getString("hinh");
//        Picasso.with(getContext()).load(image).into(imghinhshow);
        edt_makh.setText(masv);
        edt_tenkh.setText(tensv);
        edt_tien.setText(nganh);
        datakhoahoc = new ArrayList<>();
        daokhoahoc= new DAOKHOAHOC(getContext());
        datakhoahoc=daokhoahoc.readAll();
        ArrayAdapter<KhoaHoc> arrayAdapter = new ArrayAdapter<KhoaHoc>(getActivity(),android.R.layout.simple_list_item_1,datakhoahoc);

        spinermaloai.setAdapter(arrayAdapter);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        for (int i = 0; i < datakhoahoc.size(); i++) {
            if (datakhoahoc.get(i).getTenkhoahoc().equals(khoahoc)) {
                spinermaloai.setSelection(i);
                break;
            }
        }
        spinermaloai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner=datakhoahoc.get(spinermaloai.getSelectedItemPosition()).getTenkhoahoc();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnaddhinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED ||
                            checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        // gồm 2 quyền camera và quyền đọc dữ liệu
                        String[] premission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(premission, PREMISSION_CODE);
                    } else {
                        callCamera();
                    }
                } else {

                }
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String makh = edt_makh.getText().toString();
                String tenkh = edt_tenkh.getText().toString();
                String nganh = edt_tien.getText().toString();
                String ngay = spinner;
                String id = getupdate.getString("id");
                datasinhvien= new ArrayList<>();
                daosinhvien = new DAOSINHVIEN(getContext());

                daosinhvien.update(id,makh,tenkh,nganh,ngay,image_uri.toString());
                datasinhvien = daosinhvien.readAll();
                adapterSinhVien = new AdapterSinhVien(getActivity(), datasinhvien);
                rcvsinhvien.setAdapter(adapterSinhVien);
                adapterSinhVien.notifyDataSetChanged();
                dismiss();

            }
        });

        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PREMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callCamera();
                } else {
                    Toast.makeText(getContext(), "Yêu Cầu Cấp Quyền ", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void callCamera() {

        Intent cameraIntent = new Intent(
                android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra("crop", "true");
        cameraIntent.putExtra("aspectX", 0);
        cameraIntent.putExtra("aspectY", 0);
        cameraIntent.putExtra("outputX", 200);
        cameraIntent.putExtra("outputY", 150);
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE);
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode == RESULT_OK) {
            if (requestCode == IMAGE_CAPTURE_CODE) {
                Bundle extras = data.getExtras();

                if (extras != null) {
                    theImage = extras.getParcelable("data");
                    // convert bitmap to byte
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    theImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte imageInByte[] = stream.toByteArray();
                    Log.e("output before conversion", imageInByte.toString());
                    // Inserting Contacts
                    Log.d("Insert: ", "Inserting ..");
                    imghinhshow.setImageBitmap(theImage);
                    image_uri =getImageUri(getContext(),theImage);


                }



            }

//            image_uri = data.getData();
//            Picasso.with(getContext()).load(image_uri).into(imghinhshow);
//            imghinhshow.setImageURI(image_uri);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public static Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }


}
