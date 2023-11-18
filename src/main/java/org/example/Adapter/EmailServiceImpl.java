package org.example.Adapter;

public class EmailServiceImpl {
    public void send(String email, String message) {
        System.out.println("Sending email to " + email + ": " + message);
    }
}
