package com.example.elijahslaptop.jbossoutreach.Repository;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.elijahslaptop.jbossoutreach.R;
import com.example.elijahslaptop.jbossoutreach.Utility.DownloadmageTask;

import org.eclipse.egit.github.core.Contributor;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.service.RepositoryService;

import java.io.IOException;
import java.util.ArrayList;

public class ContributorsActivity extends AppCompatActivity
{
    Repository repository;
    ArrayList<ContributorItem> contributorItems = new ArrayList<>();
    ArrayList<Contributor> topContributors = new ArrayList<>();
    TextView secondPlaceTxt ;
    TextView firstPlaceTxt ;
    TextView thirdPlaceTxt ;
    ImageView secondAvatar;
    ImageView firstAvatar;
    ImageView thirdAvatar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.repository_contributors_activity);
        repository = (Repository) getIntent().getExtras().getSerializable("REPO");
        final RepositoryService service = new RepositoryService();
        secondPlaceTxt = (TextView) findViewById(R.id.secondPlace);
        firstPlaceTxt = (TextView) findViewById(R.id.firstPlace);
        thirdPlaceTxt = (TextView) findViewById(R.id.thirdPlace);
        secondAvatar = (ImageView) findViewById(R.id.secondPlaceAvatar);
        firstAvatar = (ImageView) findViewById(R.id.firstPlaceAvatar);
        thirdAvatar = (ImageView) findViewById(R.id.thirdPlaceAvatar);
        ArrayList<Contributor> contributors;

//        try {
//            boolean first = false;
//            Contributor previousContributor = null;
//            contributors = (ArrayList<Contributor>) service.getContributors(repository, false);
//
//
//            for(int i = 0; i < contributors.size(); i++ )
//            {
//                for (int j = 0; j < contributors.size(); j++) {
//
//                    Contributor contributor = contributors.get(j);
//                    if (first) {
//                        if (contributor.getContributions() > previousContributor.getContributions()) {
//                            previousContributor = contributor;
//                        }
//                    }
//                    if (!first) {
//                        first = true;
//                        previousContributor = contributor;
//                    }
//
//
//                }
//                topContributors.add(previousContributor);
//                contributors.remove(previousContributor);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            int counter = 3;
            for(Contributor contributor :service.getContributors(repository, false))
            {
                if(counter > 0)
                {
                    topContributors.add(contributor);
                    counter--;
                }
                //Log.i("Contributor test", "onCreate: " + contributor.getContributions());
                contributorItems.add(new ContributorItem(contributor.getLogin()));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


            for(Contributor contributor :topContributors)
            {
//                contributorItems.add(new ContributorItem(contributor.getLogin()));
                Log.i("Contributor test", "onCreate: " + contributor.getContributions());
            }




        RecyclerView rvContributor =  (RecyclerView) findViewById(R.id.rVContributors);



        ContributorAdapter adapter = new ContributorAdapter(contributorItems);
        rvContributor.setAdapter(adapter);
        rvContributor.setLayoutManager(new LinearLayoutManager(this));
        firstPlaceTxt.setText(topContributors.get(0).getLogin());
        new DownloadmageTask(firstAvatar).execute(topContributors.get(0).getAvatarUrl());
        secondPlaceTxt.setText(topContributors.get(1).getLogin());
        new DownloadmageTask(secondAvatar).execute(topContributors.get(1).getAvatarUrl());
        thirdPlaceTxt.setText(topContributors.get(2).getLogin());
        new DownloadmageTask(thirdAvatar).execute(topContributors.get(2).getAvatarUrl());
        Log.i("THIRD PLACE", "onCreate: " + thirdPlaceTxt.getText());
    }
}
