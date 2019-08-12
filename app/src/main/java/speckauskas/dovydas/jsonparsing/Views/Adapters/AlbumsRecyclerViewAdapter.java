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

import speckauskas.dovydas.jsonparsing.Services.Models.Album;
import speckauskas.dovydas.jsonparsing.R;

public class AlbumsRecyclerViewAdapter extends RecyclerView.Adapter<AlbumsRecyclerViewAdapter.AlbumViewHolder> {

    private List<Album> data;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public AlbumsRecyclerViewAdapter(Context context, OnItemClickListener onItemClickListener) {
       this.context = context;
       this.onItemClickListener = onItemClickListener;
    }

    public void setAlbumsList(final List<Album> albumsList) {
        this.data = albumsList;
        notifyItemRangeInserted(0, albumsList.size());
    }

    @NonNull
    @Override
    public AlbumsRecyclerViewAdapter.AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_albums, viewGroup, false);
        AlbumViewHolder holder = new AlbumViewHolder(view, onItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumsRecyclerViewAdapter.AlbumViewHolder viewHolder, int i) {
        viewHolder.id.setText(data.get(i).getId());
        viewHolder.title.setText(data.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        if(data != null){
            return data.size();
        }
        return 0;
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView id;
        TextView title;
        ConstraintLayout layout;
        OnItemClickListener onItemClickListener;

        public AlbumViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);

            id = itemView.findViewById(R.id.textViewId);
            title = itemView.findViewById(R.id.textViewTitle);
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
