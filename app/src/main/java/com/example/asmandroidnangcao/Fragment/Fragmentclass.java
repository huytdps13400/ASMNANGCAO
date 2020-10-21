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

import com.example.asmandroidnangcao.Adapter.AdapterKhoaHoc;
import com.example.asmandroidnangcao.DAO.DAOKHOAHOC;
import com.example.asmandroidnangcao.Dialog.Bottom_Sheft_Add_KhoaHoc;
import com.example.asmandroidnangcao.Dialog.Bottom_Sheft_Update_KhoaHoc;
import com.example.asmandroidnangcao.Model.KhoaHoc;
import com.example.asmandroidnangcao.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class Fragmentclass extends Fragment {
   public static RecyclerView rcvkhoahoc;
    public static AdapterKhoaHoc adapterKhoaHoc;
    ArrayList<KhoaHoc> datakhoahoc;
    DAOKHOAHOC daokhoahoc;
    FloatingActionButton floatbtnthem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragmentclass,container,false);
        rcvkhoahoc=view.findViewById(R.id.rcvkhoahoc);
     floatbtnthem=view.findViewById(R.id.floatbtnthem);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        rcvkhoahoc.setHasFixedSize(true);
        rcvkhoahoc.setLayoutManager(layoutManager);
        datakhoahoc=new ArrayList<>();
        daokhoahoc = new DAOKHOAHOC(getActivity());
        datakhoahoc=daokhoahoc.readAll();
        adapterKhoaHoc=new AdapterKhoaHoc(getActivity(),datakhoahoc);
        rcvkhoahoc.setAdapter(adapterKhoaHoc);
        floatbtnthem.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
          Bottom_Sheft_Add_KhoaHoc bottom_sheft_add_khoaHoc = new Bottom_Sheft_Add_KhoaHoc();
          bottom_sheft_add_khoaHoc.show(getFragmentManager(),"TAG");
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

                Bundle bundle = new Bundle();
                datakhoahoc=daokhoahoc.readAll();
                bundle.putString("Makh",datakhoahoc.get(position).getMakhoahoc()+"");
                bundle.putString("Tenkh",datakhoahoc.get(position).getTenkhoahoc()+"");
                bundle.putDouble("Tien", Double.parseDouble(datakhoahoc.get(position).getTien()+""));
                bundle.putString("ngay",datakhoahoc.get(position).getNgay()+"");
                bundle.putString("Idkh",datakhoahoc.get(position).getIdkh()+"");
               final String id = datakhoahoc.get(position).getIdkh();

                switch (direction) {
                    case ItemTouchHelper.LEFT:
                        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("Thông Báo");
                        builder.setMessage("Bạn có chắc muốn xóa không");

                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                datakhoahoc = new ArrayList<>();
                                daokhoahoc = new DAOKHOAHOC(getContext());
                                adapterKhoaHoc = new AdapterKhoaHoc(getActivity(), datakhoahoc);
                                daokhoahoc.delete(id);
                                datakhoahoc=daokhoahoc.readAll();
                                adapterKhoaHoc=new AdapterKhoaHoc(getActivity(),datakhoahoc);
                                rcvkhoahoc.setAdapter(adapterKhoaHoc);
                                adapterKhoaHoc.notifyDataSetChanged();
                                dialog.cancel();
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                datakhoahoc = new ArrayList<>();
                                daokhoahoc = new DAOKHOAHOC(getContext());
                                datakhoahoc = daokhoahoc.readAll();
                                adapterKhoaHoc = new AdapterKhoaHoc(getActivity(), datakhoahoc);
                                rcvkhoahoc.setAdapter(adapterKhoaHoc);
                                adapterKhoaHoc.notifyDataSetChanged();
                                dialog.cancel();
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                        adapterKhoaHoc.notifyDataSetChanged();
                        break;
                    case ItemTouchHelper.RIGHT:


                        Bottom_Sheft_Update_KhoaHoc bottom_sheft_update_khoaHoc = new Bottom_Sheft_Update_KhoaHoc();
                        bottom_sheft_update_khoaHoc.setArguments(bundle);
                        bottom_sheft_update_khoaHoc.show(getActivity().getSupportFragmentManager(), bottom_sheft_update_khoaHoc.getTag());
                        adapterKhoaHoc.notifyDataSetChanged();
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
        itemTouchHelper.attachToRecyclerView(rcvkhoahoc);
    }
}
