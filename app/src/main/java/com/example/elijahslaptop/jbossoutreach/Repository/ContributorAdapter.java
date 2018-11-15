package com.example.elijahslaptop.jbossoutreach.Repository;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.elijahslaptop.jbossoutreach.R;

import java.util.List;

public class ContributorAdapter
        extends RecyclerView.Adapter<ContributorAdapter.ViewHolder> {



    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView nameTextview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextview = (TextView) itemView.findViewById(R.id.contributorName);
        }
    }
    private List<ContributorItem> mContributors;
    public ContributorAdapter(List<ContributorItem> contributors)
    {
        this.mContributors = contributors;
    }

    @NonNull
    @Override
    public ContributorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View repositoryView = inflater.inflate(R.layout.contributor_item, parent, false);

        ContributorAdapter.ViewHolder viewHolder = new ContributorAdapter.ViewHolder(repositoryView);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        ContributorItem contributorItem = mContributors.get(position);

        viewHolder.nameTextview.setText(contributorItem.getName());
    }

    @Override
    public int getItemCount() {
        return mContributors.size();
    }
}
