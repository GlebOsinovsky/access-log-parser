import java.util.ArrayList;

public class Student {
    private final String name;
    private ArrayList <Integer> grades ;

    public Student(String name) {
        this.name = name;
        this.grades = new ArrayList<>();
    }

    public Student(String name, ArrayList<Integer> grades) {
        this.name =name;
        for (Integer grade : grades) {
            if (grade<2 || grade>5) throw new IllegalArgumentException("Оценка долдна быть от 2 до 5");
            this.grades = new ArrayList<>(grades);
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getGrades() {
        return new ArrayList<>(grades);
    }

    public void setGrades( int grade) {
        if (grade<2 || grade>5) throw new IllegalArgumentException("Оценка долдна быть от 2 до 5");
        grades.add(grade);
    }

    @Override
    public String toString() {
        return  name + ":" + grades;
    }
}