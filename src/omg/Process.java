package omg;

import omg.Human;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Process {
    public static void run() throws IOException {

        System.out.println();

        System.out.println("Введите данные в следующем формате через пробел:");
        System.out.println("Фамилия Имя Отчество, дата рождения(ДД.ММ.ГГГГ), номер телефона, пол(М или Ж)");
        System.out.print("Например: ");
        System.out.println("Иванов Иван Иванович 01.01.1980 89096090088 М");
        System.out.println();
        System.out.print("Поле для ввода: ");


        Scanner scanner = new Scanner(System.in);
        String next = scanner.nextLine();
        if (next.isEmpty()) {
            System.out.println("Введена пустая строка. Повторите попытку!");
            return;
        }

        String[] numb = next.split(" ");

        if (numb.length != 6) {
            System.out.println("Неверное количество параметров в строке. Повторите попытку!");
            return;
        }
        String family = numb[0];
        String name = numb[1];
        String patronymic = numb[2];
        String date = numb[3];
        String telefon = numb[4];
        String gender = numb[5];

        if (!Validator.isValidData(date)) {
            System.out.println("Дата указана неверно. Введите дату в формате: ДД.ММ.ГГГГ");
            return;
        }
        if (!Validator.isValidTelefon(telefon)) {
            System.out.println("Телефон указан неверно. Повторите попытку!");
            return;
        }
        if (!Validator.isValidGender(gender)) {
            System.out.println("Пол указан неверно. Пол указываем одной буквой: М - муж, Ж - женский");
            return;
        }


        Path srcPath = Paths.get("src");

        String directoryName = "people";
        File directory = new File(srcPath.toFile(), directoryName);
        directory.mkdir();

        String files = directory.getAbsolutePath() + File.separator + numb[0] + ".txt";


        Human human = new Human(family, name, patronymic, date, telefon, gender);

        try (FileWriter writer = new FileWriter(files, true)) {
            writer.write(human.toString());
            writer.append('\n');
            writer.flush();
            System.out.println("Данные успешно сохранены в файл " + files);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}