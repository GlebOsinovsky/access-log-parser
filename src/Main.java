import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите число и нажмите <Enter>: ");
        int firstNumber = new Scanner(System.in).nextInt();
        System.out.println("Введите второе число и нажмите <Enter>: ");
        int secondNumber = new Scanner(System.in).nextInt();
        System.out.println("Первое число: " + firstNumber + "\nВторое число: " + secondNumber);
        int sum = firstNumber + secondNumber;
        System.out.println("Сумма: " + sum);
        int diff = firstNumber - secondNumber;
        System.out.println("Разность: " + diff);
        int product = firstNumber * secondNumber;
        System.out.println("Произведение: " + product);
        double quotient = (double) firstNumber / secondNumber;
        System.out.println("Частное: " + quotient);
    }
}
