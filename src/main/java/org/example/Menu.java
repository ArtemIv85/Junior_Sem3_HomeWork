package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import static org.example.PersionService.*;
import static org.example.PersionService.uppDatePersonsData;

public class Menu {

    public static final  String FILE_BIN = "persons.bin";
    public static void start(){
        Scanner scanner =new Scanner(System.in);
        List<Person> personList = new ArrayList<>();
        File file = new File(FILE_BIN);
        if (file.exists()) {
            personList = loadPersonsFromFile(FILE_BIN);
        } else {
            personList = preparePersons();
        }
        viewPersons(personList);
         //region Меню навигации
        while (true){
            System.out.println("Выбирете действие");
            System.out.println("1. Добавить нового персоанажа");
            System.out.println("2. Удалить персонажа");
            System.out.println("3. Выйти");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" :
                    PersionService.addNewPerson(scanner, personList);
                    break;
                case "2" :
                    PersionService.delPerson(scanner,personList);
                    break;
                case "3" :

                    PersionService.uppDatePersonsData(FILE_BIN,personList);

                    System.out.println("Список задач сохранен.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Некорректный выбор. попробуйте снова.");
                    break;
            }
            viewPersons(personList);
        }
        //endregion


    }


}
