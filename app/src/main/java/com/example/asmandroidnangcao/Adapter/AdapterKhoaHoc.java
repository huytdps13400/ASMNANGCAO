package com.example.asmandroidnangcao.Adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asmandroidnangcao.Model.KhoaHoc;
import com.example.asmandroidnangcao.R;

import java.util.ArrayList;

public class AdapterKhoaHoc extends RecyclerView.Adapter<AdapterKhoaHoc.TabLoaiSachHolder> {
    Activity context;
    ArrayList<KhoaHoc> datakhoahoc;

    public AdapterKhoaHoc(Activity context, ArrayList<KhoaHoc> datakhoahoc) {
        this.context = context;
        this.datakhoahoc = datakhoahoc;
    }

    @NonNull
    @Override
    public TabLoaiSachHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = context.getLayoutInflater();
        View view = inflater.inflate(R.layout.itemkhoahoc,parent,false);

        return new TabLoaiSachHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TabLoaiSachHolder holder, int position) {
        holder.txtmaloai.setText("Mã Khóa Học: \t"+datakhoahoc.get(position).getMakhoahoc());
        holder.txttenloaisach.setText(datakhoahoc.get(position).getTenkhoahoc());
        holder.txtmota.setText("Tiền: \t"+String.valueOf(datakhoahoc.get(position).getTien()));
        holder.txtvitri.setText("Thời Gian: \t"+datakhoahoc.get(position).getNgay());
        byte[] image = datakhoahoc.get(position).getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        holder.imghinhdep.setImageBitmap(bitmap);
    }


    @Override
    public int getItemCount() {
        return datakhoahoc.size();
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
