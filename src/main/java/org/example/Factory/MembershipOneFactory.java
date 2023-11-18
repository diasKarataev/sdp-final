package org.example.Factory;

public class MembershipOneFactory extends MembershipFactory{
    @Override
    public Membership createMembership() {
        return new MembershipOne();
    }
}
