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

        Fraction f4 = new Fraction(3,5);
        Fraction f5 = new Fraction(49,12);
        Fraction f6 = new Fraction(3,2);
        Fraction f7 = new Fraction(1,3);


        System.out.println(sumAll(2,f4,2.3));
        System.out.println(sumAll(3.6,f5,3,f6));
        System.out.println(sumAll(f7,1));
    }
    public static double sumAll(Number...numbers){
        double sum = 0.0;
        for (Number num:numbers){
            sum+=num.doubleValue();
        } return sum;
    }
}


