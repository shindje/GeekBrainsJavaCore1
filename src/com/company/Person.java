package com.company;

public class Person {
    private String fio;
    private String post;
    private String email;
    private String phone;
    private int salary;
    private int age;

    public Person(String fio, String post, String email, String phone, int salary, int age) {
        this.fio = fio;
        this.post = post;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public void printInfo() {
        System.out.printf("Person info. Fio: %s, post: %s, email: %s, phone: %s, salary: %d, age: %d\n",
                fio, post, email, phone, salary, age);
    }

    public int getAge() {
        return age;
    }
}
