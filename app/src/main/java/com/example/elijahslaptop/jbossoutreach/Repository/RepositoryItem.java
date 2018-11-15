package com.example.elijahslaptop.jbossoutreach.Repository;

import org.eclipse.egit.github.core.Repository;

import java.util.ArrayList;

public class RepositoryItem {

    private String name;
    private String description;
    private String programmingLanguage;
    private int stars;
    private  int forks;
    private Repository repositoryReference;

    public RepositoryItem(String name, String description, String programmingLanguage, int stars, int forks, Repository repository) {
        this.name = name;
        this.description = description;
        this.programmingLanguage = programmingLanguage;
        this.stars = stars;
        this.forks = forks;
        repositoryReference = repository;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public int getStars() {
        return stars;
    }

    public int getForks() {
        return forks;
    }

   public String toString(int i) {
       return Integer.toString(i);
   }

    private static int lastContactId = 0;

    public Repository getRepositoryReference() {
        return repositoryReference;
    }

    public static ArrayList<RepositoryItem> createContactsList(int numContacts) {
        ArrayList<RepositoryItem> contacts = new ArrayList<RepositoryItem>();

        for (int i = 1; i <= numContacts; i++) {
            contacts.add(new RepositoryItem("Repository " + ++lastContactId,
                    "Description" , "Java",10,10, null));
        }

        return contacts;
    }
}
