package org.example.Factory;

public class MembershipTwoFactory extends MembershipFactory{
    @Override
    public Membership createMembership() {
        return new MembershipTwo();
    }
}
