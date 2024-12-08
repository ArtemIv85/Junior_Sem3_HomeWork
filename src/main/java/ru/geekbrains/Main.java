package ru.geekbrains;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.List;
import ru.geekbrains.Models.Person;
import ru.geekbrains.Models.PersonsService;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        System.out.println("Начало программы");
        try (SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Person.class).buildSessionFactory()){
            System.out.println("Открытие сессии");
            //Создание сессии
            Session session = sessionFactory.getCurrentSession();
            System.out.println("Начало транзакции");
            // Начало транзакции
            session.beginTransaction();

            //Создание объекта
            System.out.println("Создание объекта");
            Person person = PersonsService.addPerson();
            System.out.println(person.getId());
            session.save(person);
            System.out.println(person.getId());


            //Чтение объекта из базы данных
            Person retrievedPerson = session.get(Person.class, person.getId());
            System.out.println("Объект Person восстановлен");
            System.out.println("Retrived person object: "+ retrievedPerson);


            // Чтение всех персон
            //System.out.println(PersonsService.getAllPersons()); // Выдает ошибку
            //List personArrayList = (List) session.createQuery("FROM persons").list(); // Выдает ошибку
            //System.out.println(personArrayList);

            System.out.println("Подтверждаем изменения");
            session.getTransaction().commit(); // Подтверждаем все изменения которые
            // проводили в рамках данной транзакции
        }
        catch (Exception e){
            e.printStackTrace();
        }



    } //end main
}