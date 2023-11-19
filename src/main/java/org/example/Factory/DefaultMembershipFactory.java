package org.example.Factory;

import org.example.Strategy.ShoppingCart;

public class DefaultMembershipFactory extends MembershipFactory{
    @Override
    public Membership createMembership(ShoppingCart shoppingCart) {
        Membership membership = new DefaultMembership();
        shoppingCart.addToCart(membership);
        return membership;
    }
}
