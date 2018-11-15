package com.example.elijahslaptop.jbossoutreach;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.elijahslaptop.jbossoutreach.Repository.RepositoryItem;
import com.example.elijahslaptop.jbossoutreach.Repository.RepositoryAdapter;

import org.eclipse.egit.github.core.Contributor;
import org.eclipse.egit.github.core.IRepositoryIdProvider;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.service.RepositoryService;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<RepositoryItem> repositories = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);



        final RepositoryService service = new RepositoryService();


        try {

            for (Repository repo : service.getOrgRepositories("JBossOutreach"))
            {
                repositories.add(new RepositoryItem(repo.getName(),
                        repo.getDescription(),repo.getLanguage(),10,repo.getForks(), repo));




            }
        }catch (Exception e)
        {
            Log.e("error", e.getMessage(), e);
        }
        setContentView(R.layout.activity_main);

        RecyclerView rvRepository =  (RecyclerView) findViewById(R.id.rVRepository);



        RepositoryAdapter adapter = new RepositoryAdapter(repositories, getApplicationContext());

        rvRepository.setAdapter(adapter);
        rvRepository.setLayoutManager(new LinearLayoutManager(this));





    }



}
