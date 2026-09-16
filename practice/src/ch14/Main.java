package ch14;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ex1();
        ex2();
        ex3();
        ex4();
    }

    public static void ex1() {
        Box<Dog> dogBox = new Box<>();

        dogBox.putIn(new Dog("초코"));

        Dog dog = dogBox.takeOut();
        dog.makeSound();

        Box<Animal> animalBox = new Box<>();

        animalBox.putIn(new Dog("초코"));
        animalBox.putIn(new Cat("나비"));

        Animal animal = animalBox.takeOut();
    }

    public static void ex2() {
        Box<Dog> dogBox = new Box<>();

//        Box<Animal> animalBox = dogBox;
    }

    public static void ex3() {
        Box<Dog> dogBox = new Box<>();
        Box<Cat> catBox = new Box<>();

        Box<? extends Animal> box1 = dogBox;
        Box<? extends Animal> box2 = catBox;

        Box<? extends Animal> box = new Box<Dog>();

        Animal animal = box.takeOut();

//        box.putIn(new Dog("초코"));
//        box.putIn(new Cat("나비"));
    }

    public static void ex4() {
        Box<Dog> dogBox = new Box<>();
        Box<Animal> animalBox = new Box<>();
        Box<Object> objectBox = new Box<>();

        Box<? super Dog> box1 = dogBox;
        Box<? super Dog> box2 = animalBox;
        Box<? super Dog> box3 = objectBox;

        Box<? super Dog> box = new Box<Animal>();

        box.putIn(new Dog("초코"));

//        Dog dog = box.takeOut();
//        Animal animal = box.takeOut();
    }

    public static void ex5() {
        List<Dog> dogs = List.of(
                new Dog("초코"),
                new Dog("콩이")
        );

        List<Cat> cats = List.of(
                new Cat("나비"),
                new Cat("보리")
        );

        barkAll(dogs);
        barkAll(cats);
    }

    static void barkAll(List<? extends Animal> animals) {
        for (Animal animal : animals) {
            animal.makeSound();
        }
    }

    public static void ex6() {
        List<Dog> dogs = new ArrayList<>();
        List<Animal> animals = new ArrayList<>();
        List<Object> objects = new ArrayList<>();

        addDog(dogs);
        addDog(animals);
        addDog(objects);
    }

    static void addDog(List<? super Dog> dogs) {
        dogs.add(new Dog("새 강아지"));
    }
    public static void ex7() {

    }

    public static void ex8() {

    }
}