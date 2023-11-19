package org.example.Decorator;

import org.example.Factory.Membership;

public class PoolMembership implements Membership{
    private Membership membership;

    public PoolMembership(Membership decoratedMembership) {
        this.membership = decoratedMembership;
    }
    @Override
    public String getDescription() {
        return "+ бассейн";
    }
    @Override
    public int getPrice() {
        return 10000;
    }
}
