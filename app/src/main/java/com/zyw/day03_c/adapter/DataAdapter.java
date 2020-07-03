package com.zyw.day03_c.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zyw.day03_c.R;
import com.zyw.day03_c.bean.UserBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {


    private List<UserBean> data = new ArrayList<>();
    private Context context;

    public DataAdapter(Context context) {
        this.context = context;
    }

    public void addData(List<UserBean> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final UserBean userBean = data.get(i);
        viewHolder.ivImg.setImageURI(Uri.fromFile(new File(userBean.getImagePath())));
        viewHolder.tvName.setText(userBean.getUsername());

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onClickItem != null) {
                    onClickItem.onClick(userBean);
                }
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    OnClickItem onClickItem;

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    public interface OnClickItem {
        void onClick(UserBean userBean);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_img)
        ImageView ivImg;
        @BindView(R.id.tv_name)
        TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
