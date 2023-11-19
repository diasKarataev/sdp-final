package org.example.Decorator;

import org.example.Factory.Membership;

import java.util.ArrayList;
import java.util.List;

public class DecoratedMembership {
    private final Membership baseMembership;
    private final List<MembershipDecorator> decorators;

    public DecoratedMembership(Membership baseMembership) {
        this.baseMembership = baseMembership;
        this.decorators = new ArrayList<>();
    }

    public void addDecorator(MembershipDecorator decorator) {
        decorators.add(decorator);
    }

    public double getCost() {
        double totalCost = baseMembership.getPrice();
        for (MembershipDecorator decorator : decorators) {
            totalCost = decorator.getPrice();
        }
        return totalCost;
    }

    public String getDescription() {
        StringBuilder description = new StringBuilder(baseMembership.getDescription());
        for (MembershipDecorator decorator : decorators) {
            description.append(", ").append(decorator.getDescription());
        }
        return description.toString();
    }
}
