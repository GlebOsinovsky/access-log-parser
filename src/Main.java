import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите текст и нажмите <Enter>: ");
        String scanner = new Scanner(System.in).nextLine();
        System.out.println("Длина текста: " + scanner.length());
    }
}
