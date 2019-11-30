import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Manager extends Person{

    private Collection employees = new ArrayList();

    public Manager(String name, Collection employees){
        super(name);
        this.employees = employees;
    }

    @Override
    public void task() {
        Iterator iter = employees.iterator();

        while (iter.hasNext()){
            Employee employee = (Employee) iter.next();
            employee.task();
        }
    }
}
