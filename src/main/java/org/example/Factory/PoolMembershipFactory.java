package org.example.Factory;

import org.example.Decorator.PersonalMembership;
import org.example.Decorator.PoolMembership;
import org.example.Strategy.ShoppingCart;

public class PoolMembershipFactory extends MembershipFactory{
    @Override
    public Membership createMembership(ShoppingCart shoppingCart) {
        Membership membership = new DefaultMembershipFactory().createMembership(shoppingCart);
        return new PoolMembership(membership);
    }
}
