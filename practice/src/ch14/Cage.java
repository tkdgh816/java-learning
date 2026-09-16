package ch14;

import java.util.List;

class Cage<T extends Animal> extends Box<T> {
    public <U extends Animal> void greet(List<U> friends) {
        for (U friend : friends) {
            System.out.println(friend + " 친구와 인사");
            friend.makeSound();
        }
    }

    public void hello(List<? extends Animal> friends) {
        for (Animal friend : friends) {
            System.out.println(friend + " 친구 안녕!");
            friend.makeSound();
        }
    }


}