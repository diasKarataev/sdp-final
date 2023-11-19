package org.example.Decorator;

import org.example.Factory.Membership;

public class PersonalMembership implements MembershipDecorator {
    private Membership membership;
    public PersonalMembership(Membership decoratedMembership) {
        this.membership = decoratedMembership;
    }
    @Override
    public String getDescription() {
        return "+ персональный треннер";
    }
    @Override
    public int getPrice() {
        return 40000;
    }
}
