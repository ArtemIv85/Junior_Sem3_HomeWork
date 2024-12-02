package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.Menu.FILE_BIN;


public class PersionService {

    //region Метод обновления
    public static void uppDatePersonsData (String filename, List<Person> persons){
        try (ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream(filename))){
            oos.writeObject(persons);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    //endregion

    //region Вывод на экран
    public static void viewPersons (List<Person> persons){
        System.out.println("Список персон:");
        for (int i=0; i < persons.size(); i++){
            System.out.println("№ "+ (i+1) + " \t Имя: "+persons.get(i).getName()+" \t возраст: "+persons.get(i).getAge());
        }
    }
    //endregion

    //region метод загрузки из файла
    public static List<Person> loadPersonsFromFile( String fileName){
        List<Person> persons = new ArrayList<>();
        File file = new File(fileName);

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
            persons = (List<Person>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return persons;
    }
    //endregion

    //region Метод добавление
    public static void addNewPerson (Scanner scanner, List<Person> persons){
        System.out.println("Введите имя: ");
        String name = scanner.nextLine();
        System.out.println("Укажите возраст: ");
        int age = Integer.parseInt(scanner.nextLine());
        persons.add(new Person(name,age));
        uppDatePersonsData(FILE_BIN,persons);

    }
    //endregion

    //region Метод удаления
    public static void delPerson (Scanner scanner, List<Person> persons){
        viewPersons(persons);
        System.out.println("Введите номер персоны кого нужно удалить: ");
        int delId = Integer.parseInt(scanner.nextLine());
        persons.remove(delId-1);
        uppDatePersonsData(FILE_BIN,persons);
    }
    //endregion

//region Создание шаблона если файл отсутствует
    static List<Person> preparePersons() {
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person("Артем",25));
        list.add(new Person("Семен",36));
        list.add(new Person("Владимир",28));
        list.add(new Person("Генадий",41));
        list.add(new Person("Кирилл",60));

        return list;
    }
    //endregion

}
