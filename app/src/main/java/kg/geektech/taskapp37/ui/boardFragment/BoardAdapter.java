package kg.geektech.taskapp37.ui.boardFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import kg.geektech.taskapp37.Prefs;
import kg.geektech.taskapp37.R;
import kg.geektech.taskapp37.databinding.ItemBoardBinding;
import kg.geektech.taskapp37.intarfaces.OnBoardStartClickListener;


public class  BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private String[] titles = new String[]{"Салам", "Привет", "Hello"};
    private String [] desc = new String []{"Дубровник","Торонто","Токио"};
    private int [] image = new int[]{R.drawable.dubrovnic,R.drawable.toronto,R.drawable.tokyo};
    public OnBoardStartClickListener  clickListener;
    private Context context;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        return new ViewHolder(ItemBoardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    public void setClickListener(OnBoardStartClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemBoardBinding binding;

        public ViewHolder(ItemBoardBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            binding.btnStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new Prefs(context ).saveBoardState();
                    clickListener.onStartClick();

                }
            });
        }

        public void onBind(int position) {

            binding.textTitle.setText(titles[position]);
            binding.textDesc.setText(desc[position]);
            binding.imageView.setImageResource(image[position]);
            if (position == titles.length -1) {
                binding.btnStart.setVisibility(View.VISIBLE);
            } else {
                binding.btnStart.setVisibility(View.INVISIBLE);
            }
        }
    }
}

