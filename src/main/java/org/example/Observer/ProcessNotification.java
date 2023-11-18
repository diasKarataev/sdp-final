package org.example.Observer;



import org.example.Adapter.EmailService;
import org.example.Adapter.EmailServiceImpl;
import org.example.Adapter.ProcessNotificationEmailAdapter;
import org.example.User;

import java.util.ArrayList;
import java.util.List;

public class ProcessNotification implements Observable{
    private EmailServiceImpl emailService;
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
            EmailService mailing = new ProcessNotificationEmailAdapter(emailService);
            mailing.sendEmail(observer.getEmail());
            System.out.println("Send email to " + observer.getEmail());
        }
    }
}
