package kgu.lab.repositories;

import org.springframework.stereotype.Repository;

@Repository
public class RepositoryImpl {
    public void addTest(String id, String text) {
        System.out.print("Id - " + id + ", text - " + text);
    }
}