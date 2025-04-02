
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Student st = new Student("John");
        ArrayList <Integer> vasyaGrades = new ArrayList <>(List.of(1,3,4,5,5));
        Student st2 = new  Student("Vasya",vasyaGrades);
        System.out.println(st2);



        A a = new A();
        B b = new B();
        b.x = 1;

        System.out.println(a);
    }

}

