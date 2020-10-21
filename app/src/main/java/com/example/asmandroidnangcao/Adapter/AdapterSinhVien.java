package com.example.asmandroidnangcao.Adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asmandroidnangcao.Model.KhoaHoc;
import com.example.asmandroidnangcao.Model.SinhVien;
import com.example.asmandroidnangcao.R;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class AdapterSinhVien extends RecyclerView.Adapter<AdapterSinhVien.TabLoaiSachHolder> {
    Activity context;
    ArrayList<SinhVien> datasinhvien;
    Bitmap theImage;

    public AdapterSinhVien(Activity context, ArrayList<SinhVien> datasinhvien) {
        this.context = context;
        this.datasinhvien = datasinhvien;
    }

    @NonNull
    @Override
    public TabLoaiSachHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.itemsinhvien,parent,false);

        return new TabLoaiSachHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TabLoaiSachHolder holder, int position) {
        holder.txtmaloai.setText(datasinhvien.get(position).getMasv());
        holder.txttenloaisach.setText("Tên Sinh Viên: \t"+datasinhvien.get(position).getTensv());
        holder.txtmota.setText("Khóa Học: \t"+datasinhvien.get(position).getIdkh());
        holder.txtvitri.setText("Ngành: \t"+datasinhvien.get(position).getNganh());
//        byte[] image = datasinhvien.get(position).getImage();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
//        holder.imghinhdep.setImageBitmap(bitmap);
        Picasso.with(context).load(datasinhvien.get(position).get_image()).into(holder.imghinhdep);
    }


    @Override
    public int getItemCount() {
        return datasinhvien.size();
    }
    public static class TabLoaiSachHolder extends RecyclerView.ViewHolder{
        public TextView txtmaloai,txttenloaisach,txtmota,txtvitri;
        public ImageView imghinhdep;
        public TabLoaiSachHolder(@NonNull View itemView) {

            super(itemView);
            txtmaloai = itemView.findViewById(R.id.txtmaloai);
            txttenloaisach = itemView.findViewById(R.id.txttenloaisach);
            txtmota = itemView.findViewById(R.id.txtmota);
            txtvitri = itemView.findViewById(R.id.txtvitri);
            imghinhdep = itemView.findViewById(R.id.imghinhdep);

        }
    }
}
