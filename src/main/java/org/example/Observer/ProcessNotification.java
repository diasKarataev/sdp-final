package org.example.Observer;



import org.example.User;

import java.util.ArrayList;
import java.util.List;

public class ProcessNotification implements Observable{
    List<User> observers = new ArrayList<>();

    @Override
    public void addObserver(User user) {
        observers.add(user);
    }

    @Override
    public void removeObserver(User user) {
        observers.remove(user);
    }

    @Override
    public void notifyMembers() {
        for(User observer : observers){
            System.out.println("Send email to " + observer.getEmail());
        }
    }
}
