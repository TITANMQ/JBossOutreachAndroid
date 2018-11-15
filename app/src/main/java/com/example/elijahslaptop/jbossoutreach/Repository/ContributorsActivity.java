package com.example.elijahslaptop.jbossoutreach.Repository;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.elijahslaptop.jbossoutreach.R;

import org.eclipse.egit.github.core.Contributor;
import org.eclipse.egit.github.core.IRepositoryIdProvider;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.service.RepositoryService;

import java.io.IOException;
import java.util.ArrayList;

public class ContributorsActivity extends AppCompatActivity
{
    Repository repository;
    ArrayList<ContributorItem> contributorItems = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.repository_contributors_activity);
        repository = (Repository) getIntent().getExtras().getSerializable("REPO");
        final RepositoryService service = new RepositoryService();

        try {
            for(Contributor contributor :service.getContributors(repository, false))
            {
                contributorItems.add(new ContributorItem(contributor.getLogin()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        RecyclerView rvContributor =  (RecyclerView) findViewById(R.id.rVContributors);



        ContributorAdapter adapter = new ContributorAdapter(contributorItems);

        rvContributor.setAdapter(adapter);
        rvContributor.setLayoutManager(new LinearLayoutManager(this));
    }
}
