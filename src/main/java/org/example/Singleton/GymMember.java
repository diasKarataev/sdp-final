package org.example.Singleton;

import org.example.Factory.Membership;

public class GymMember {
    private User user;
    private Membership membership;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }
}
