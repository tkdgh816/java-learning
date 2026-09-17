package q258712;

import java.util.*;
import java.util.stream.Collectors;

// 가장 많이 받은 선물
// https://school.programmers.co.kr/learn/courses/30/lessons/258712
public class Test {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.solution(new String[]{"muzi", "ryan", "frodo", "neo"}, new String[]{"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"}));
        System.out.println(sol.solution(new String[]{"joy", "brad", "alessandro", "conan", "david"}, new String[]{"alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david"}));
        System.out.println(sol.solution(new String[]{"a", "b", "c"}, new String[]{"a b", "b a", "c a", "a c", "a c", "c a"}));
    }
}

class Solution {
    public int solution(String[] friends, String[] gifts) {
        Map<String, Member> membersByName = Arrays.stream(friends)
                .collect(Collectors.toMap(name -> name, Member::new));
        Collection<Member> members = membersByName.values();

        for (String gift : gifts) {
            String[] pair = gift.split("\\s+");
            Member sendMember = membersByName.get(pair[0]);
            Member receiveMember = membersByName.get(pair[1]);
            sendMember.sendGift(receiveMember);
        }

//        for (Member member : members) {
//            System.out.println(member);
//        }

        return members.stream()
                .mapToInt(member -> calculateNextReceiveCount(member, members))
                .max()
                .orElse(0);
    }

    int calculateNextReceiveCount(Member targetMember, Collection<Member> friends) {
        int count = 0;
        int giftIndex = targetMember.getGiftIndex();
        for (Member friend : friends) {
            int diff = targetMember.getSendCount(friend) - friend.getSendCount(targetMember);
            if (diff > 0 || (diff == 0 && giftIndex > friend.getGiftIndex())) {
                count++;
            }
        }
        return count;
    }
}

class Member {
    private final String name;
    private final Map<Member, Integer> sendCountsByMember = new HashMap<>();
    private int receiveCount = 0;

    Member(String name) {
        this.name = name;
    }

    void sendGift(Member friend) {
        sendCountsByMember.put(friend, sendCountsByMember.getOrDefault(friend, 0) + 1);
        friend.receiveGift();
    }

    private void receiveGift() {
        receiveCount++;
    }

    int getSendCount(Member friend) {
        return sendCountsByMember.getOrDefault(friend, 0);
    }

    private int getTotalSendCount() {
        return sendCountsByMember.values().stream().mapToInt(count -> count).sum();
    }

    int getGiftIndex() {
        return getTotalSendCount() - receiveCount;
    }

    @Override
    public String toString() {
        return name + ": " + getTotalSendCount() + " " + receiveCount + " " + getGiftIndex();
    }
}