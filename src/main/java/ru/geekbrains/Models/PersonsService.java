package ru.geekbrains.Models;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Scanner;

public class PersonsService {

    public static Person addPerson(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите имя персоны: ");
        String name = scanner.nextLine();
        System.out.println("Укажите возраст");
        int age = Integer.parseInt(scanner.nextLine());
        Person person = new Person(name,age);
        return person;
    }

    @PersistenceContext
    private static EntityManager entityManager;

    public static List<Person> getAllPersons() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
        Root<Person> personsRoot = criteriaQuery.from(Person.class);
        criteriaQuery.select(personsRoot);
        Query query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

}
