package Lesson3.Collections.HomeWork;

import java.util.*;
// Задание 1 : Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
// Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
// Посчитать сколько раз встречается каждое слово.
//Задание 2 : Написать простой класс ТелефонныйСправочник,
// который хранит в себе список фамилий и телефонных номеров.
// В этот телефонный справочник с помощью метода add() можно добавлять записи.
// С помощью метода get() искать номер телефона по фамилии.
// Следует учесть, что под одной фамилией может быть несколько телефонов
// (в случае однофамильцев), тогда при запросе такой фамилии должны выводиться все телефоны.
public class HomeWorkCollection {

    public static void main(String[] args){

        listHomeWork(); //Задание 1

        addPhoneBook(); //Задание 2


    }

    private static void addPhoneBook() {
        System.out.println("Задание 2 :\n<<Телефонный справочник>>");
        Phonebook phonebook = new Phonebook();

        phonebook.add("Аистов", "111");
        phonebook.add("Белкин", "222");
        phonebook.add("Волков", "333");
        phonebook.add("Гусев", "444");
        phonebook.add("Дятлов", "555");
        phonebook.add("Дятлов", "666");

        System.out.println("\nС помощью метода get() найдем номер телефона по фамилии \n");

        System.out.println("Аистов" + phonebook.get("Аистов"));
        System.out.println("Белкин" + phonebook.get("Белкин"));
        System.out.println("Волков" + phonebook.get("Волков"));
        System.out.println("Волков" + phonebook.get("Гусев"));
        System.out.println("Волков" + phonebook.get("Дятлов"));
    }


    private static void listHomeWork() {
        List<String> name =  new ArrayList<>();

        name.add("Anna");
        name.add("Boris");
        name.add("Gena");
        name.add("Vova");
        name.add("Dima");
        name.add("Alina");
        name.add("Bob");
        name.add("Ken");
        name.add("Dacha");
        name.add("Artem");
        name.add("Sofa");
        name.add("Lena");
        name.add("Sofa");
        name.add("Lena");


        System.out.println("Задание 1 :\nМассив из имен: ");
        System.out.println(name);
        System.out.println("Колличество всех элементов в массиве: " + name.size());

        Set<String> unique = new HashSet<String>(name);


        System.out.println("Уникальные имена: " + unique.toString());

        for (String key : unique) {
    System.out.println("имя: " + key + " встречается в массиве: "
            + Collections.frequency(name, key));
}
    }
}
