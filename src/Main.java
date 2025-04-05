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


        PolyLine polyLine = new PolyLine(a,b,c,d);
        System.out.println(polyLine);
        System.out.println("Длина ломаной: "+polyLine.getLength());
        Line line = new Line(d,a);
        System.out.println("Длина замыкающей линии "+line.getLength());

        ClosedPolyLine closedPolyLine = new ClosedPolyLine(a,b,c,d);
        System.out.println("Длина замкнутой: "+closedPolyLine.getLength());

    }
}


