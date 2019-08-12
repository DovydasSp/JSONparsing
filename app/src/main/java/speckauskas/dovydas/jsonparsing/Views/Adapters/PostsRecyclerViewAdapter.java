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

import speckauskas.dovydas.jsonparsing.Services.Models.Post;
import speckauskas.dovydas.jsonparsing.R;

public class PostsRecyclerViewAdapter extends RecyclerView.Adapter<PostsRecyclerViewAdapter.UserViewHolder> {

    private List<Post> data;
    private Context context;

    public PostsRecyclerViewAdapter(Context context) {
       this.context = context;
    }

    public void setPostsList(final List<Post> postsList) {
        this.data = postsList;
        notifyItemRangeInserted(0, postsList.size());
    }

    @NonNull
    @Override
    public PostsRecyclerViewAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_posts, viewGroup, false);
        UserViewHolder holder = new UserViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostsRecyclerViewAdapter.UserViewHolder viewHolder, int i) {
        final int pos = i;

        viewHolder.postId.setText(data.get(i).getId());
        viewHolder.postTitle.setText(data.get(i).getTitle());
        viewHolder.postBody.setText(data.get(i).getBody());
    }

    @Override
    public int getItemCount() {
        if(data != null){
            return data.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        TextView postId;
        TextView postTitle;
        TextView postBody;
        ConstraintLayout layout;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            postId = itemView.findViewById(R.id.textViewPostID);
            postTitle = itemView.findViewById(R.id.textViewPostTitle);
            postBody = itemView.findViewById(R.id.textViewPostBody);
            layout = itemView.findViewById(R.id.usersLayout);
        }
    }
}
