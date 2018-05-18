

 //TODO 多布局的Item的位置与数据的绑定在排序、删除操作后仍有BUG无法解决

package com.tashi.listview.sampledata;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tashi.listview.R;

import java.util.ArrayList;
import java.util.Collections;

public class mAdapter extends RecyclerView.Adapter<mAdapter.ViewHolderPool> implements ItemTouchHelperAdapter {
    private ArrayList<Integer> image_normal;
    private ArrayList<Integer> image_first;
    private ArrayList<String> texts_normal;
    private ArrayList<String> texts_first;
    private ArrayList<Integer> image_third;


    public mAdapter(ArrayList<Integer> image_normal, ArrayList<String> texts_normal) {
        this.image_normal = image_normal;
        this.image_first = new ArrayList<>();
        this.texts_normal = texts_normal;
        this.texts_first = new ArrayList<>();
        this.image_third = new ArrayList<>();

        image_third.add(R.mipmap.ic_head);
        image_third.add(R.mipmap.recommend_share);
        image_third.add(R.mipmap.recommend_subscribe_default);
        image_third.add(R.mipmap.ic_detail);

        image_first.add(R.mipmap.halloffame);
        image_first.add(R.mipmap.ic_article);
        image_first.add(R.mipmap.article_read_icon);
        image_first.add(R.mipmap.article_fabulous_icon);
        texts_first.add("作者");
        texts_first.add("佛秀不负如来");
        texts_first.add("爱与佛不可兼得，不负如来便负卿");


    }

    @Override
    public int getItemViewType(int position) {
        return position % 5;
    }
    @NonNull
    @Override
    public ViewHolderPool onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.first_item, parent, false);
        }else if(viewType==3){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.third_item,parent,false);
        }
        else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.normal_item, parent, false);
        }
        return new ViewHolderPool(view, viewType);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolderPool holder, int position) {

        if (getItemViewType(position) == 0) {
            holder.author_head.setImageResource(image_first.get(0));
            holder.article_img.setImageResource(image_first.get(1));
            holder.read_icon.setImageResource(image_first.get(2));
            holder.collection_icon.setImageResource(image_first.get(3));
            holder.author_id.setText(texts_first.get(0));
            holder.article_title.setText(texts_first.get(1));
            holder.intro.setText(texts_first.get(2));
        }else if(getItemViewType(position)==3){
            holder.theme_id.setText(texts_first.get(0));
            holder.theme_head.setImageResource(image_third.get(0));
            holder.theme_share.setImageResource(image_third.get(1));
            holder.theme_add.setImageResource(image_third.get(2));
            holder.theme_article_img.setImageResource(image_third.get(3));
        }
        else {
            holder.imageView_normal.setImageResource(image_normal.get(position));
            holder.content.setText(texts_normal.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return (texts_normal.size());
    }


    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if(fromPosition%2==0&&toPosition%2==0){
            Collections.swap(texts_first,fromPosition,toPosition);
            Collections.swap(image_first,fromPosition,toPosition);
        }else if(fromPosition%2!=0&&toPosition%2!=0){
            Collections.swap(image_normal, fromPosition, toPosition);
            Collections.swap(texts_normal, fromPosition, toPosition);
        }
        notifyItemMoved(fromPosition,toPosition);
    }

    @Override
    public void onItemDelete(int position) {
        if(position%2==0){
            texts_first.remove(position);
            image_first.remove(position);
        }else if(position%2!=0){
            texts_normal.remove(position);
            image_normal.remove(position);

             }
        notifyItemRemoved(position);
    }


    class ViewHolderPool extends RecyclerView.ViewHolder implements View.OnClickListener {
        static final int ITEM_FIRST = 0;
        //        static final int ITEM_BUTTON=0;
        static final int ITEM_THIRD = 3;
        private TextView content;
        private ImageView imageView_normal;

        private Button button;
        private ImageView author_head;
        private ImageView read_icon;
        private ImageView collection_icon;
        private ImageView article_img;
        private TextView article_title;
        private TextView intro;
        private TextView author_id;

        private ImageView theme_head;
        private TextView theme_id;
        private ImageView theme_share;
        private ImageView theme_add;
        private ImageView theme_article_img;



        private ViewHolderPool(View itemView, int Type) {
            super(itemView);
            if (Type == ITEM_FIRST) {
                initFist(itemView);
            }else if(Type==3){
                initThird(itemView);
            }
            else {
                initNormal(itemView);
            }

        }
        void initThird(View itemView){
            this.theme_add = itemView.findViewById(R.id.theme_add);
            this.theme_article_img = itemView.findViewById(R.id.theme_article_img);
            this.theme_head = itemView.findViewById(R.id.theme_head);
           this.theme_share = itemView.findViewById(R.id.theme_share);
            this.theme_id = itemView.findViewById(R.id.theme_id);
            CardView cardView = itemView.findViewById(R.id.item_view_third);
            cardView.setCardElevation(5);
            cardView.setCardBackgroundColor(itemView.getResources().getColor(R.color.colorAccent));
        }



        void initFist(View itemView) {
            author_id = itemView.findViewById(R.id.author_id);
            author_head = itemView.findViewById(R.id.author_head);
            read_icon = itemView.findViewById(R.id.read_icon);
            collection_icon = itemView.findViewById(R.id.collection_icon);
            intro = itemView.findViewById(R.id.intro);
            article_img = itemView.findViewById(R.id.article_img);
            article_title = itemView.findViewById(R.id.article_title);
            CardView cardView = itemView.findViewById(R.id.item_view_first);
            cardView.setCardElevation(5);
            cardView.setCardBackgroundColor(itemView.getResources().getColor(R.color.colorAccent));
        }

        void initNormal(View itemView) {
            CardView cardView = itemView.findViewById(R.id.item_view);
            cardView.setCardElevation(5);
            cardView.setCardBackgroundColor(itemView.getResources().getColor(R.color.colorAccent));
            imageView_normal = itemView.findViewById(R.id.image);
            content = itemView.findViewById(R.id.text);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case (R.layout.first_item):
                    //TODO 点击事件代码逻辑
                    break;
                case (R.layout.third_item):
                    break;
                case (R.layout.normal_item):
                    break;
            }

        }
    }

}

