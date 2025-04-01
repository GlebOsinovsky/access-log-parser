
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Student st = new Student("John");
        st.setGrades(4);
        st.setGrades(5);
        System.out.println(st.getName());
        System.out.println(st);

        ArrayList <Integer> vasyaGrades = new ArrayList <>(List.of(2,3,4,5,5));
        Student st2 = new  Student("Vasya",vasyaGrades);
        System.out.println(st2.getGrades());
        st2.setGrades(5);
        System.out.println(st2.getGrades());
        System.out.println(st2);
    }

}

