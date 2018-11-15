package com.example.elijahslaptop.jbossoutreach.Repository;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.elijahslaptop.jbossoutreach.R;

import java.io.Serializable;
import java.util.List;
import java.util.zip.Inflater;

public class RepositoryAdapter extends
        RecyclerView.Adapter<RepositoryAdapter.ViewHolder> {




    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView nameTextView;
        public TextView descriptionTextView;
        public TextView languageTextView;
        public ImageView starsImageView;
        public TextView starsTextView;
        public ImageView forksImageView;
        public TextView forksTextView;
        public Button openButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.name);
            descriptionTextView = (TextView) itemView.findViewById(R.id.description);
            languageTextView = (TextView) itemView.findViewById(R.id.language);
//            starsImageView = (ImageView) itemView.findViewById(R.id.starsImg);
//            starsTextView = (TextView) itemView.findViewById(R.id.stars);
            forksImageView = (ImageView) itemView.findViewById(R.id.forksImg);
            forksTextView = (TextView) itemView.findViewById(R.id.forks);
            openButton = (Button) itemView.findViewById(R.id.openBtn);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }

    private Context mContext;
    private List<RepositoryItem> mRepository;
    private static final String REPO = "REPO";

    public RepositoryAdapter(List<RepositoryItem> repository, Context context)
    {
        mRepository = repository;
        mContext = context;
    }


    @NonNull
    @Override
    public RepositoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View repositoryView = inflater.inflate(R.layout.item_repository, parent, false);

        ViewHolder viewHolder = new ViewHolder(repositoryView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RepositoryAdapter.ViewHolder viewHolder, int position) {

        final RepositoryItem repository = mRepository.get(position);

        TextView name = viewHolder.nameTextView;
        name.setText(repository.getName());

        TextView description  = viewHolder.descriptionTextView;
        description.setText(repository.getDescription());

        TextView language = viewHolder.languageTextView;
        language.setText(repository.getProgrammingLanguage());

//        TextView stars = viewHolder.starsTextView;
//        stars.setText(repository.toString(repository.getStars()));

        TextView forks = viewHolder.forksTextView;
        forks.setText(repository.toString(repository.getForks()));

        Button button = viewHolder.openButton;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(mContext, ContributorsActivity.class);
                Bundle bundle = new Bundle();

                bundle.putSerializable(REPO, (Serializable) repository.getRepositoryReference());
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mRepository.size();
    }
}

