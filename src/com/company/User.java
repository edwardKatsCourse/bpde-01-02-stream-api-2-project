package com.company;

import java.util.Set;

public class User {
    private String name;
    private Set<Movie> myCollection;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Movie> getMyCollection() {
        return myCollection;
    }

    public void setMyCollection(Set<Movie> myCollection) {
        this.myCollection = myCollection;
    }

    @Override
    public String toString() {
        return String.format("Name: %s", name);
    }
}
