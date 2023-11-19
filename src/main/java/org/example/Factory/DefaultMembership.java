package org.example.Factory;

public class DefaultMembership implements Membership {

    @Override
    public int getPrice() {
        return 20000;
    }

    @Override
    public String getDescription() {
        return "Базовый абонемент";
    }
}
