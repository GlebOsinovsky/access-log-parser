public class Employee {
    String name;
    Department dep;
    int emp;

    // Конструктор для имени
    public Employee(String name) {
        this.name = name;
    }

    // Конструктор для имени и отдела
    public Employee(String name, Department dep) {
        this.name = name;
        this.dep = dep;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                '}';
    }
}
