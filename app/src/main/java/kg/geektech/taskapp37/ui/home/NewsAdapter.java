package kg.geektech.taskapp37.ui.home;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kg.geektech.taskapp37.databinding.ItemNewsBinding;
import kg.geektech.taskapp37.intarfaces.OnItemClickListener;
import kg.geektech.taskapp37.models.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private ArrayList<News> list;
    private OnItemClickListener onItemClickListener;
    private ItemNewsBinding binding;

    public NewsAdapter() {
        list = new ArrayList<>();
//        list.add(new News("Askar",System.currentTimeMillis()));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position),onItemClickListener);
        if (position % 2 == 0){
            binding.textTitle.setBackgroundColor(Color.GRAY);

        }else {
            binding.textTitle.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(News news) {

        list.add(0,news);

        notifyItemInserted(0);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public News getItem(int pos) {
        return list.get(pos);
    }

    public void removeItem(News news, int p) {
        this.list.remove(news);
        notifyItemRemoved(p);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
//        private ItemNewsBinding binding;
        public ViewHolder( ItemNewsBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            binding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onItemClickListener.onLongClick(getAdapterPosition());
                    return true;
                }
            });

        }

        public void onBind(News news, OnItemClickListener onItemClickListener) {
            binding.textTitle.setText(news.getTitle());
        }
    }
}