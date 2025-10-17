package dinner;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static DinnerConstructor dc;
    static Scanner scanner;

    public static void main(String[] args) {
        dc = new DinnerConstructor();
        scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    addNewDish();
                    break;
                case "2":
                    generateDishCombo();
                    break;
                case "3":
                    System.out.println("Программа завершена. До скорых встреч!");
                    return;
                default:
                    System.out.println("Команда введена некорректно, попробуйте еще раз!");
            }
        }
    }

    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Выход");
    }

    private static void addNewDish() {
        System.out.println("Введите тип блюда:");
        String dishType = scanner.nextLine();
        System.out.println("Введите название блюда:");
        String dishName = scanner.nextLine();

        dc.addNewDish(dishType, dishName);// добавьте новое блюдо, с помощью метода DinnerConstructor addNewDish
    }

    private static void generateDishCombo() {

        if (dc.dinnersByType.isEmpty()) {
            System.out.println("Список пуст! Блюда не добавлены!");
            return;
        }

        System.out.println("Начинаем конструировать обед...");

        System.out.println("Введите количество наборов, которые нужно сгенерировать:");
        int numberOfCombos = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Вводите типы блюда, разделяя символом переноса строки (enter). Для завершения ввода введите пустую строку");
        String nextItem = scanner.nextLine();

        //реализуйте ввод типов блюд
        ArrayList<String> selectedTypes = new ArrayList<>();
        while (!nextItem.isEmpty()) { //варианты вводит пользователь
            if (dc.checkType(nextItem)) { //но вы должны проверить, существуют ли эти блюда в хранилище с помощью метода DinnerConstructor checkType
                selectedTypes.add(nextItem); //выбранное блюдо добавьте в список вариантов
            } else {
                System.out.println("Такой тип блюд мы еще не умеем готовить. Попробуйте что-нибудь другое!");
            }
            nextItem = scanner.nextLine(); //перейдите к следующему пункту ввода пользователя
        }

        // сгенерируйте комбинации блюд и выведите на экран
        ArrayList<ArrayList<String>> generatedCombos = dc.generateCombos(numberOfCombos, selectedTypes); //сгенерируйте варианты комбинаций блюд с помощью метода DinnerConstructor generateCombos
        for (int i = 0; i < numberOfCombos; i++) {
            System.out.println("Комбинация " + (i+1));
            System.out.println(generatedCombos.get(i)); //выведите каждый элемент получившейся комбинации
        }
    }
}