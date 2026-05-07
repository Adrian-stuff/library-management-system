import java.util.UUID;

public class Member {
    private String libraryCardNumber;
    private String name;
    private String contactNumber;
    private String address;

    public Member(String name, String contactNumber, String address) {
        this.libraryCardNumber = "LIB-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        this.name = name;
        this.contactNumber = contactNumber;
        this.address = address;
    }

    public String getCardNumber() { return libraryCardNumber; }
    public String getName() { return name; }

    public void displayInfo() {
        System.out.println("Card No: " + libraryCardNumber);
        System.out.println("Name: " + name);
        System.out.println("Contact: " + contactNumber);
        System.out.println("Address: " + address);
    }
}