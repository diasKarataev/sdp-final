package org.example.Observer;


import org.example.Singleton.User;

public interface Observable {
    void addMailingSubscriber(User user);
    void removeMailingSubscriber(User user);
    void notifyMembers(String topic, String message);
}
