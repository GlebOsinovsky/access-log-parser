import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Fraction f1 = new Fraction(1,3);
        Fraction f2 = new Fraction(2,5);
        Fraction f3 = new Fraction(7,8);
        System.out.println(f1);
        System.out.println(f1.sum(f2));
        System.out.println(f1.sumInt(4));
        System.out.println(f1.sum(f2).sum(f3).minusInt(5));
    }
}


