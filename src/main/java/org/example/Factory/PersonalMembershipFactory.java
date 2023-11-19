package org.example.Factory;

import org.example.Decorator.PersonalMembership;
import org.example.Strategy.ShoppingCart;

public class PersonalMembershipFactory extends MembershipFactory{
    @Override
    public Membership createMembership(ShoppingCart shoppingCart) {
        Membership membership = new DefaultMembershipFactory().createMembership(shoppingCart);
        return new PersonalMembership(membership);
    }
}
