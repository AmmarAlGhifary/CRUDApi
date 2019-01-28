package com.blogspot.yourfavoritekaisar.crudapi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.yourfavoritekaisar.crudapi.DetailActivity;
import com.blogspot.yourfavoritekaisar.crudapi.R;
import com.blogspot.yourfavoritekaisar.crudapi.model.Constant;
import com.blogspot.yourfavoritekaisar.crudapi.model.User.UserData;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class userAdapter extends RecyclerView.Adapter<userAdapter.ViewHolder> {

    private Context context;
    private List<UserData> userDataList;

    public userAdapter(Context context, List<UserData> userDataList) {
        this.context = context;
        this.userDataList = userDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.user_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        // Mengambil Data List
        UserData userData = userDataList.get(i);

        viewHolder.txtFirst.setText(userData.getFirst_name());
        viewHolder.txtLast.setText(userData.getLast_name());

        // Membuat object request option
        RequestOptions options =  new RequestOptions().error(R.drawable.ic_flash_off_black_24dp).placeholder(R.drawable.ic_error_black_24dp);

        Glide.with(context).load(userData.getAvatar()).apply(options).into(viewHolder.imgOrang);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int id = userData.getId();
                Log.d(Constant.TAG, "id: " + id);
                Bundle bundle = new Bundle();
                bundle.putInt(Constant.EXTRA_OBJECT, id);
                context.startActivity(new Intent(context, DetailActivity.class).putExtras(bundle));
            }
        });

    }

    @Override
    public int getItemCount() {
        return userDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgOrang)
        ImageView imgOrang;
        @BindView(R.id.txtFirst)
        TextView txtFirst;
        @BindView(R.id.txtLast)
        TextView txtLast;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
