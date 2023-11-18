package org.example.Observer;


import org.example.User;

public interface Observable {
    void addObserver(User user);
    void removeObserver(User user);
    void notifyMembers();
}
