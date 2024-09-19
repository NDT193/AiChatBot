package Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aichatbot.R;

import java.util.List;

import Model.itemModel;

public class itemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static int TYPE_USER =1;
    private static int TYPE_BOT =2;

    public List<itemModel> imlist;

    public void setData(List<itemModel> list)
    {
        this.imlist = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (TYPE_USER == viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_messegeitem,parent,false);
            return new UserViewHolder(view);
        } else if (TYPE_BOT == viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatbot_messegeitem,parent,false);
            return new BotViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        itemModel itemModel = imlist.get(position);
        if(itemModel == null)
        {
            return;
        }

        if(TYPE_USER == holder.getItemViewType())
        {
            UserViewHolder userViewHolder= (UserViewHolder) holder;
            userViewHolder.userTxt.setText(itemModel.getText());

        }else if (TYPE_BOT == holder.getItemViewType())
        {
            BotViewHolder botViewHolder = (BotViewHolder) holder;
            botViewHolder.botTxt.setText(itemModel.getText());

        }
    }

    @Override
    public int getItemCount() {
        if(imlist != null)
        {
            return imlist.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        itemModel itemModel = imlist.get(position);
        if(itemModel.isUser())
        {
            return TYPE_USER;
        } else {
            return TYPE_BOT;
        }
    }

    //-----------XỬ LÝ VIEWHOLDER CHO 2 CUSTOM ITEM----------------
    public class BotViewHolder extends RecyclerView.ViewHolder {
        private TextView botTxt ;
        public BotViewHolder(@NonNull View itemView) {
            super(itemView);
            botTxt = itemView.findViewById(R.id.botMessege);

        }
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        private TextView userTxt ;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            userTxt= itemView.findViewById(R.id.userMessage);
        }
    }
}
