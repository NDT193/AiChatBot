package Adapter;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.aichatbot.R;

import java.util.ArrayList;
import java.util.List;

import Model.botModel;

public class botItemAdapter extends RecyclerView.Adapter<botItemAdapter.BotViewHolder> {

    private Context context;
    private List<botModel> itemsList = new ArrayList<>();
    public botItemAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<botModel> list)
    {
        this.itemsList = list;
        notifyDataSetChanged();
    }
    public void addItem(botModel item) {
        itemsList.add(item);
        notifyItemInserted(itemsList.size() - 1);
    }

    @NonNull
    @Override
    public BotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatbot_messegeitem,parent,false);
        return new BotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BotViewHolder holder, int position) {
        botModel bot = itemsList.get(position);

        if(bot == null)
        {
            return;
        }
        holder.botmessage.setText(bot.getMess());

    }

    @Override
    public int getItemCount() {
        if(itemsList != null)
        {
            return itemsList.size();
        }
        return 0;
    }



    public class BotViewHolder extends RecyclerView.ViewHolder
    {

        private ImageView imagebot;
        private TextView botmessage;

        public BotViewHolder(@NonNull View itemView) {
            super(itemView);

            imagebot = itemView.findViewById(R.id.botAvatar);
            botmessage = itemView.findViewById(R.id.botMessege);
        }
    }

}
