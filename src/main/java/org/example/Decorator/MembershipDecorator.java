package org.example.Decorator;

import org.example.Factory.Membership;

public abstract class MembershipDecorator {
    private Membership decoratedMembership;

    public MembershipDecorator(Membership decoratedMembership) {
        this.decoratedMembership = decoratedMembership;
    }
}
