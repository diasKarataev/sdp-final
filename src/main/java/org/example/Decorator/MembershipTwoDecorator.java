package org.example.Decorator;

import org.example.Factory.Membership;

public class MembershipTwoDecorator extends MembershipDecorator{
    public MembershipTwoDecorator(Membership decoratedMembership) {
        super(decoratedMembership);
    }
}
