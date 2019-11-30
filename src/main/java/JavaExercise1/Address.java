public class Address {

    private String street;
    private int number;
    private String zipCode;

    public int getNumber() {
        return number;
    }

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }
    public Address(String street, String zipCode){
        this.street = street;
        this.zipCode = zipCode;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return street + " " + number + " " + zipCode;
    }
}
