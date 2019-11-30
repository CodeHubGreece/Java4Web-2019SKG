public class StressedEmployee extends Employee{

    private int stressLevel;
    private int MAX_STRESS_LEVEL = 5;

    public StressedEmployee(Address address, String name, int stressLevel ) {
        super(address, name);
        this.stressLevel = stressLevel;
    }

    @Override
    public void task() {
        if (stressLevel > MAX_STRESS_LEVEL) {
            System.out.println("I'm stressed!!");
        } else {
            super.task();
        }
    }

    public void setStressLevel(int stressLevel) {
        this.stressLevel = stressLevel;
    }
}
