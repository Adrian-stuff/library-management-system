import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        // Pre-load some items
        library.addItem(new LibraryItem("ITEM-001", "Introduction to Java", "Programming"));
        library.addItem(new LibraryItem("ITEM-002", "Clean Code", "Software Engineering"));
        library.addItem(new LibraryItem("ITEM-003", "Data Structures & Algorithms", "Computer Science"));
        library.addItem(new LibraryItem("ITEM-004", "Object-Oriented Design", "Programming"));
        library.addItem(new LibraryItem("ITEM-005", "The Pragmatic Programmer", "Software Engineering"));

        int choice;
        do {
            printMenu();
            System.out.print("Enter choice: ");
            choice = Integer.parseInt(scanner.nextLine().trim());
            System.out.println();

            switch (choice) {
                case 1 -> library.browseCatalog();
                case 2 -> {
                    System.out.print("Enter keyword to search: ");
                    String keyword = scanner.nextLine();
                    library.searchCatalog(keyword);
                }
                case 3 -> {
                    System.out.print("Enter Library Card Number: ");
                    String cardNum = scanner.nextLine();
                    Member m = library.findMember(cardNum);
                    if (m == null) {
                        System.out.println("Member not found. Please register first.");
                    } else {
                        library.browseCatalog();
                        System.out.print("Enter Item ID to borrow: ");
                        String itemId = scanner.nextLine();
                        library.borrowItem(m, itemId);
                    }
                }
                case 4 -> {
                    System.out.print("Enter Library Card Number: ");
                    String cardNum = scanner.nextLine();
                    Member m = library.findMember(cardNum);
                    if (m == null) {
                        System.out.println("Member not found.");
                    } else {
                        System.out.print("Enter Item ID to return: ");
                        String itemId = scanner.nextLine();
                        library.returnItem(m, itemId);
                    }
                }
                case 5 -> {
                    System.out.print("Enter Full Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Contact Number: ");
                    String contact = scanner.nextLine();
                    System.out.print("Enter Address: ");
                    String address = scanner.nextLine();
                    library.registerMember(name, contact, address);
                }
                case 0 -> System.out.println("Thank you for using the Library System. Goodbye!");
                default -> System.out.println("Invalid choice. Please try again.");
            }

            if (choice != 0) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
                clearScreen();
            }

        } while (choice != 0);

        scanner.close();
    }

    static void printMenu() {
        System.out.println("=====================================");
        System.out.println("     LIBRARY MANAGEMENT SYSTEM      ");
        System.out.println("=====================================");
        System.out.println("  [1] Browse Catalog");
        System.out.println("  [2] Search Catalog");
        System.out.println("  [3] Borrow Item");
        System.out.println("  [4] Return Item");
        System.out.println("  [5] Register Member");
        System.out.println("  [0] Exit");
        System.out.println("=====================================");
    }

    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}