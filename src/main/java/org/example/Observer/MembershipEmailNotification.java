package org.example.Observer;



import org.example.Adapter.EmailService;
import org.example.Adapter.EmailServiceImpl;
import org.example.Adapter.JoinMembershipNotificationEmailAdapter;
import org.example.Singleton.User;

import java.util.ArrayList;
import java.util.List;

public class MembershipEmailNotification implements Observable{
    private EmailServiceImpl emailService = new EmailServiceImpl();
    List<User> observers = new ArrayList<>();

    @Override
    public void addMailingSubscriber(User user) {
        observers.add(user);
    }

    @Override
    public void removeMailingSubscriber(User user) {
        observers.remove(user);
    }

    @Override
    public void notifyMembers(String topic, String message) {
        for(User observer : observers){
            emailService.send(observer.getEmail(), topic,message);
            System.out.println("Send email to " + observer.getEmail());
        }
    }
}
