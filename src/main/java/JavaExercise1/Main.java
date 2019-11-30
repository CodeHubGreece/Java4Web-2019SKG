import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args){
        Person person = new Person("Nick");
        person.task();

        Employee emp1 = new Employee(
                new Address("Tsimiski", "55555"),
                "Jim");
        emp1.task();

        Address addr = new Address("Mitropoleos", "55552");
        Employee emp2 = new Employee(addr,"Mark");
        emp2.task();

        Employee emp3 = new Employee(addr,"Leonidas");

        Set employees = new HashSet();
        employees.add(emp1);
        employees.add(emp3);
        Manager mgr = new Manager("Peter", employees);
        mgr.task();

        StressedEmployee emp4 =
                new StressedEmployee(addr, "John", 10);
        emp4.task();
        emp4.setStressLevel(2);
        emp4.task();
    }
}
