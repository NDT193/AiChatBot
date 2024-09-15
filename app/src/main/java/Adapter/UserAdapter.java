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

import Model.User;
import Model.botModel;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private Context ucontext;
    private List<User> userList = new ArrayList<>();
    public UserAdapter(Context context) {
        this.ucontext = context;
    }
    public void setData(List<User> ulist) {
        this.userList = ulist;
        notifyDataSetChanged();
    }

    public void addUserItem(User item) {
        userList.add(item);
        notifyItemInserted(userList.size() - 1);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_messegeitem, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);

        if (user == null) {
            return;
        }
        holder.usermessage.setText(user.getUsermess());
    }

    @Override
    public int getItemCount() {
        if (userList != null) {
            return userList.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageuser;
        private TextView usermessage;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            imageuser = itemView.findViewById(R.id.userAvatar);
            usermessage = itemView.findViewById(R.id.userMessage);
        }
    }
}
