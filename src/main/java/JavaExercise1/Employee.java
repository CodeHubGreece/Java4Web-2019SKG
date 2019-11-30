public class Employee extends Person{

    private Address address;

    public Employee (Address address, String name){
        super(name);
        this.address = address;
    }

    @Override
    public void task() {
        System.out.println(address + " " + super.toString());
    }
}
