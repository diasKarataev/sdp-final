package org.example.Factory;

import org.example.Strategy.ShoppingCart;

public abstract class MembershipFactory {
    public abstract Membership createMembership(ShoppingCart shoppingCart);
}
