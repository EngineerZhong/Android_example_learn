package com.example.a.myapplication12321321;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.a.myapplication12321321.base.recyclerviewadapter.JayChou;
import com.squareup.picasso.Picasso;
import java.util.List;
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.VH> {

    private List<JayChou> jayChouList = null;
    private Context mContext;
    public RecycleViewAdapter(Context context,List<JayChou> jayChous){
        this.mContext = context;
        this.jayChouList = jayChous;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.mezu_img_item,null);
        return new VH(item);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        Picasso.get().load(jayChouList.get(position).getImgurl())
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return jayChouList.size();
    }

    static class VH extends RecyclerView.ViewHolder{
         ImageView img;
         VH(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.mezu_item);
        }
    }
}
