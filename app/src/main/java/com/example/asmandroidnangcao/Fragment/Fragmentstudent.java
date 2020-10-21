package com.example.asmandroidnangcao.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asmandroidnangcao.Adapter.AdapterSinhVien;
import com.example.asmandroidnangcao.DAO.DAOSINHVIEN;
import com.example.asmandroidnangcao.Dialog.Bottom_Sheft_Add_SinhVien;
import com.example.asmandroidnangcao.Dialog.Bottom_Sheft_Update_SinhVien;
import com.example.asmandroidnangcao.Model.SinhVien;
import com.example.asmandroidnangcao.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class Fragmentstudent extends Fragment {
    FloatingActionButton floatbtnthem;
   public static RecyclerView rcvsinhvien;
    ArrayList<SinhVien> datasinhvien;
    DAOSINHVIEN daosinhvien;
    AdapterSinhVien adapterSinhVien;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragmentstudent,container,false);
        rcvsinhvien=view.findViewById(R.id.rcvsinhvien);
        floatbtnthem=view.findViewById(R.id.floatbtnthem);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rcvsinhvien.setHasFixedSize(true);
        rcvsinhvien.setLayoutManager(layoutManager);
        datasinhvien=new ArrayList<>();
        daosinhvien = new DAOSINHVIEN(getActivity());
        datasinhvien=daosinhvien.readAll();
        adapterSinhVien=new AdapterSinhVien(getActivity(),datasinhvien);
        rcvsinhvien.setAdapter(adapterSinhVien);
        floatbtnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bottom_Sheft_Add_SinhVien bottom_sheft_add_sinhVien = new Bottom_Sheft_Add_SinhVien();
                bottom_sheft_add_sinhVien.show(getFragmentManager(),"TAG");
            }
        });
        intswipe(view);
        return view;
    }
    public void intswipe(final View v) {

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                Bundle bundlell = new Bundle();
                datasinhvien=daosinhvien.readAll();
                bundlell.putString("idkh",datasinhvien.get(position).getIdkh()+"");
                bundlell.putString("Masv",datasinhvien.get(position).getMasv()+"");
                bundlell.putString("Tensv",datasinhvien.get(position).getTensv()+"");
                bundlell.putString("nganh", datasinhvien.get(position).getNganh()+"");
                bundlell.putString("id",datasinhvien.get(position).getId()+"");
                bundlell.putString("hinh",datasinhvien.get(position).get_image());

                switch (direction) {
                    case ItemTouchHelper.LEFT:
                        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("Thông Báo");
                        builder.setMessage("Bạn có chắc muốn xóa không");

                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String id = datasinhvien.get(position).getId();

                                datasinhvien = new ArrayList<>();
                                daosinhvien = new DAOSINHVIEN(getContext());
                                adapterSinhVien = new AdapterSinhVien(getActivity(), datasinhvien);
                                daosinhvien.delete(id);
                                datasinhvien=daosinhvien.readAll();
                                adapterSinhVien=new AdapterSinhVien(getActivity(),datasinhvien);
                                rcvsinhvien.setAdapter(adapterSinhVien);
                                adapterSinhVien.notifyDataSetChanged();
                                dialog.cancel();
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                datasinhvien = new ArrayList<>();
                                daosinhvien = new DAOSINHVIEN(getContext());
                                datasinhvien = daosinhvien.readAll();
                                adapterSinhVien = new AdapterSinhVien(getActivity(), datasinhvien);
                                rcvsinhvien.setAdapter(adapterSinhVien);
                                adapterSinhVien.notifyDataSetChanged();
                                dialog.cancel();
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                        adapterSinhVien.notifyDataSetChanged();
                        break;
                    case ItemTouchHelper.RIGHT:


                        Bottom_Sheft_Update_SinhVien bottom_sheft_update_sinhVien = new Bottom_Sheft_Update_SinhVien();
                        bottom_sheft_update_sinhVien.setArguments(bundlell);
                        bottom_sheft_update_sinhVien.show(getActivity().getSupportFragmentManager(), bottom_sheft_update_sinhVien.getTag());
                        datasinhvien = new ArrayList<>();
                        daosinhvien = new DAOSINHVIEN(getContext());
                        datasinhvien = daosinhvien.readAll();
                        adapterSinhVien = new AdapterSinhVien(getActivity(), datasinhvien);
                        rcvsinhvien.setAdapter(adapterSinhVien);
                        adapterSinhVien.notifyDataSetChanged();
                        break;

                }


            }

            public void onChildDraw(Canvas c, RecyclerView recyclerView,
                                    RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                    int actionState, boolean isCurrentlyActive) {

                new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeLeftBackgroundColor(ContextCompat.getColor(getContext(), R.color.Do))
                        .addSwipeLeftActionIcon(R.drawable.ic_delete_black_24dp)
                        .create()
                        .decorate();

                new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeRightBackgroundColor(ContextCompat.getColor(getContext(), R.color.Xanh))
                        .addSwipeRightActionIcon(R.drawable.ic_sua)
                        .create()
                        .decorate();

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }

        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rcvsinhvien);
    }
}
