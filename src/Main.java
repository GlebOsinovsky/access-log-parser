import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Point a = new Point(1,5);
        Point b = new Point(2,8);
        Point c = new Point(5,3);
        Point d = new Point(8,9);


        System.out.println("До изменения точки{2,8}");
        PolyLine poliLine = new PolyLine(a,b,c,d);
        System.out.println(poliLine);
        System.out.println("Длина ломаной: "+poliLine.getLength());
        Line[] lines = poliLine.getLines();
        System.out.println("Массив линий: "+Arrays.toString(lines));
        System.out.println("Длина массива линий: "+ lines.length);
        double linesLength = 0.0;
        for (Line line : lines) {
            linesLength += line.getLength();
        }
        System.out.println("Сумма длин линий: "+ linesLength);
        double polyLength = poliLine.getLength();
        System.out.println("Длины совпадают: " + (polyLength == linesLength));

        System.out.println(" ");
        System.out.println("После изменения точки{2,8} на {12,8}");
        b.x = 12;
        System.out.println("Изменения в точке"+b);
        System.out.println(poliLine);
        System.out.println("Изменения в ломаной: "+poliLine);
        System.out.println("Изменения в 2х линиях массива: "+Arrays.toString(lines));


    }
}


