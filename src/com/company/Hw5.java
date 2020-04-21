package com.company;

public class Hw5 {

    public static void main(String[] args) {
        Person[] persons = new Person[5];
        persons[0] = new Person("Иванов И. И.", "директор", "ceo@company.com", "555-55-55", 200000, 50);
        persons[1] = new Person("Петрова С. К.", "бухгалтер", "money@company.com", "123-45-67", 80000, 35);
        persons[2] = new Person("Дуров П. А.", "разработчик", "durov@gmail.com", "436-22-51", 150000, 37);
        persons[3] = new Person("Малевич В. У.", "дизайнер", "black_square@company.com", "111-11-11", 120000, 45);
        persons[4] = new Person("Васильева А. К.", "HR", "job@company.com", "333-22-11", 80000, 33);

        for (int i = 0; i < persons.length; i++) {
            if (persons[i].getAge() > 40)
                persons[i].printInfo();
        }
    }
}
