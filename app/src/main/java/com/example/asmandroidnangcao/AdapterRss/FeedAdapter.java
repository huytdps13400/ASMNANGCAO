package com.example.asmandroidnangcao.AdapterRss;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.asmandroidnangcao.Model.Item;
import com.example.asmandroidnangcao.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder>  {

    private ArrayList<Item> arrayItem;

    public FeedAdapter(ArrayList<Item> arrayItem) {
        this.arrayItem = arrayItem;
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //LayoutInflater là 1 component conver từ file xml thành View Java code
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new FeedViewHolder(itemView);
        //trả về bố cục xml

    }

    @Override
    public void onBindViewHolder(FeedViewHolder holder, int position) {
        holder.txtTitle.setText(arrayItem.get(position).getTitle());
        holder.txtPubDate.setText(arrayItem.get(position).getPubDate());
        Glide.with(holder.itemView)
                .load(arrayItem.get(position).getThumbnail())
                .centerCrop()
                .into(holder.imgrss);
        //Mỗi khi ViewHolder được tạo trước đó được sử dụng lại,
        // RecyclerView sẽ bảo Adapter cập nhật dữ liệu của nó. Bạn tùy chỉnh quy trình này bằng cách ghi đè lên BindViewHolder().

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.txtContent.setText(Html.fromHtml(arrayItem.get(position).getContent(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            holder.txtContent.setText(Html.fromHtml(arrayItem.get(position).getContent()));
        }
    }

    @Override
    public int getItemCount() {
//Để vẽ danh sách trên màn hình, Recyclerview hỏi Adapter xem tổng cộng bao nhiêu Item
        // Adapter sẽ trả về kích thước số item đó

        return arrayItem.size();
    }


    class FeedViewHolder extends RecyclerView.ViewHolder{

        TextView txtTitle,txtPubDate,txtContent;
        ImageView imgrss;

        FeedViewHolder(View itemView) {
            super(itemView);
            imgrss=itemView.findViewById(R.id.imgrss);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtPubDate = itemView.findViewById(R.id.txtPubDate);
            txtContent = itemView.findViewById(R.id.txtContent);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Action_view nằm trong intent
                    //getAdapterPosition() nằm trong RecyclerView

                   // Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(arrayItem.get(getAdapterPosition()).getLink()));
                 //   Intent intent = new Intent(Intent.ACTION_SEND,Uri.parse(arrayItem.get(getAdapterPosition()).getLink());
                    Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(arrayItem.get(getAdapterPosition()).getLink()));

                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}