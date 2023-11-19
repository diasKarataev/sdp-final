package org.example.Singleton;

import org.example.Observer.Observable;

import java.util.ArrayList;
import java.util.List;

public class MembershipManagement {
    private static MembershipManagement instance;
    private List<GymMember> gymMembers;
    public List<GymMember> getMembers() {
        return gymMembers;
    }
    public void addMember(GymMember member) {
        gymMembers.add(member);
    }
    private MembershipManagement() {
        gymMembers = new ArrayList<>();
    }

    public static MembershipManagement getInstance() {
        if (instance == null) {
            instance = new MembershipManagement();
        }
        return instance;
    }

}
