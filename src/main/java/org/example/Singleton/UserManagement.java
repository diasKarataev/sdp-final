package org.example.Singleton;

import java.util.ArrayList;
import java.util.List;

public
class UserManagement {
    private static UserManagement instance;
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    private UserManagement() {
        users = new ArrayList<>();
    }

    public static UserManagement getInstance() {
        if (instance == null) {
            instance = new UserManagement();
        }
        return instance;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public String getUserPasswordByEmail(String email) {
        for(User user : users){
            if(user.getEmail().equals(email)){
                return user.getPassword();
            }
        }
        return null;
    }
    public ERole getRolePasswordByEmail(String email) {
        for(User user : users){
            if(user.getEmail().equals(email)){
                return user.getRole();
            }
        }
        return null;
    }

    public String getUserNameByEmail(String email) {
        for(User user : users){
            if(user.getEmail().equals(email)){
                return user.getName();
            }
        }
        return null;
    }
}

