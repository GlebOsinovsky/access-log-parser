import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Line line1 = new Line(new Point(1,3),new Point(5,8));
        Line line2 = new Line(10,11,15,19);
        Line line3 = new Line(line1.endPoint,line2.startPoint);
        System.out.println("Длина линии 1: "+line1.getLenght());
        System.out.println("Линия 2 до изменений: "+line2);
        System.out.println("Линия 3 до изменений: "+ line3);
        System.out.println("Суммарная длина всех 3х линий:"+" "+(line1.getLenght()+line2.getLenght()+line3.getLenght()));

       line3.startPoint.x =12;
        line3.startPoint.y =13;
        line3.endPoint.x =16;
        line3.endPoint.y =17;

        System.out.println("линия 3 после изменений: "+line3);
        System.out.println("линия 2 после изменений: "+line2);
    }
}


//        Point a = new Point(1,3);
//        Point b = new Point(1,3);
//        Point c = new Point(5,8);
//     System.out.println(a);
//     System.out.println(b);
//     System.out.println(c);
//     System.out.println(a==b);
//     System.out.println(a==c);
//     Name k = new Name(null,"seena", "wait");
//     Name j = Name.ofPersonalNameAndPatronomic("asd","sd");
//        System.out.println(k);
//        System.out.println(j);
//    A m = A.of(null);
//    A n = A.ofnul(null);
//    A x = A.of("txt");
//        System.out.println(m);
//        System.out.println(n);
//        System.out.println(x);
