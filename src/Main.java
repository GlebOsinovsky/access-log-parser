import java.io.File;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filePath;
        int rightPathamount=0;
        while (true) {
            System.out.println("Введите полный путь к файлу:");
            filePath = scanner.nextLine();

            File file = new File(filePath);

            boolean fileExists = file.isFile();
            boolean isDirectory = file.isDirectory();

            if (fileExists == true) {
                rightPathamount++;
                System.out.println("Путь к файлу указан верно");
                System.out.println("Это файл номер "+rightPathamount);
                ;
            } else if ( isDirectory == true) {
                System.out.println("Указанный путь является путём к папке");continue;}
                            else {
                    System.out.println("Указаный файл не существует, либо путь указан неверно");continue;
                }
            }
        }
    }


