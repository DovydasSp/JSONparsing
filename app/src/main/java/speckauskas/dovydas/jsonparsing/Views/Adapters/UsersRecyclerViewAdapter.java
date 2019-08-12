package speckauskas.dovydas.jsonparsing.Views.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

import speckauskas.dovydas.jsonparsing.Services.Models.User;
import speckauskas.dovydas.jsonparsing.R;

public class UsersRecyclerViewAdapter extends RecyclerView.Adapter<UsersRecyclerViewAdapter.UserViewHolder> {

    private List<User> data;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public UsersRecyclerViewAdapter(Context mcontext, OnItemClickListener onItemClickListener) {
       this.context = mcontext;
       this.onItemClickListener = onItemClickListener;
    }

    public void setUsersList(final List<User> usersList) {
         this.data = usersList;
         notifyItemRangeInserted(0, usersList.size());
    }

    @NonNull
    @Override
    public UsersRecyclerViewAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_users, viewGroup, false);
        UserViewHolder holder = new UserViewHolder(view, onItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UsersRecyclerViewAdapter.UserViewHolder viewHolder, int i) {
        final int pos = i;

        viewHolder.userId.setText(data.get(i).getId());
        viewHolder.username.setText(data.get(i).getUsername());
        viewHolder.userName.setText(data.get(i).getName());
        viewHolder.userEmail.setText(data.get(i).getEmail());
    }

    @Override
    public int getItemCount() {
        if(data != null){
            return data.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView userId;
        TextView username;
        TextView userName;
        TextView userEmail;
        ConstraintLayout layout;
        OnItemClickListener onItemClickListener;

        public UserViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            userId = itemView.findViewById(R.id.textViewUserID);
            username = itemView.findViewById(R.id.textViewUsername);
            userName = itemView.findViewById(R.id.textViewUserName);
            userEmail = itemView.findViewById(R.id.textViewUserEmail);
            layout = itemView.findViewById(R.id.usersLayout);

            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(getAdapterPosition());
        }
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
