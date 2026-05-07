import java.util.ArrayList;

public class Library {
    private ArrayList<Member> members = new ArrayList<>();
    private ArrayList<LibraryItem> items = new ArrayList<>();
    private ArrayList<Loan> loans = new ArrayList<>();

    public Member registerMember(String name, String contact, String address) {
        Member member = new Member(name, contact, address);
        members.add(member);
        System.out.println("-------------------------------------");
        System.out.println("  Registration Successful!");
        System.out.println("  Welcome, " + name + "!");
        System.out.println("  Your Library Card No: " + member.getCardNumber());
        System.out.println("  (Save this number to borrow items)");
        System.out.println("-------------------------------------");
        return member;
    }

    public Member findMember(String cardNumber) {
        for (Member m : members) {
            if (m.getCardNumber().equalsIgnoreCase(cardNumber)) return m;
        }
        return null;
    }

    public void browseCatalog() {
        System.out.println("=====================================");
        System.out.println("          LIBRARY CATALOG           ");
        System.out.println("=====================================");
        if (items.isEmpty()) {
            System.out.println("  No items available.");
        } else {
            String currentCategory = "";
            for (LibraryItem item : items) {
                if (!item.getCategory().equals(currentCategory)) {
                    currentCategory = item.getCategory();
                    System.out.println("\n  [ " + currentCategory + " ]");
                }
                String status = item.isAvailable() ? "Available" : "Borrowed";
                String marker = item.isAvailable() ? "✓" : "✗";
                System.out.println("  " + marker + " [" + item.getItemId() + "] " 
                    + item.getTitle() + " (" + status + ")");
            }
        }
        System.out.println("=====================================");
    }

    public void searchCatalog(String keyword) {
        System.out.println("=====================================");
        System.out.println("  Search Results for: \"" + keyword + "\"");
        System.out.println("=====================================");
        boolean found = false;
        for (LibraryItem item : items) {
            if (item.getTitle().toLowerCase().contains(keyword.toLowerCase())
                    || item.getCategory().toLowerCase().contains(keyword.toLowerCase())) {
                String status = item.isAvailable() ? "Available" : "Borrowed";
                System.out.println("  [" + item.getItemId() + "] " + item.getTitle() 
                    + " - " + item.getCategory() + " (" + status + ")");
                found = true;
            }
        }
        if (!found) System.out.println("  No items found.");
        System.out.println("=====================================");
    }

    public void borrowItem(Member member, String itemId) {
        for (LibraryItem item : items) {
            if (item.getItemId().equalsIgnoreCase(itemId)) {
                if (item.isAvailable()) {
                    item.setAvailable(false);
                    Loan loan = new Loan(member, item);
                    loans.add(loan);
                    System.out.println("-------------------------------------");
                    System.out.println("  Borrow Confirmed!");
                    System.out.println("  Item  : " + item.getTitle());
                    System.out.println("  Member: " + member.getName());
                    System.out.println("  Due   : " + loan.getDueDate());
                    System.out.println("  Please return on or before due date.");
                    System.out.println("-------------------------------------");
                } else {
                    System.out.println("  Item is currently unavailable.");
                }
                return;
            }
        }
        System.out.println("  Item not found.");
    }

    public void returnItem(Member member, String itemId) {
        for (Loan loan : loans) {
            if (loan.getItem().getItemId().equalsIgnoreCase(itemId)
                    && loan.getMember() == member
                    && !loan.isReturned()) {
                loan.returnItem();
                System.out.println("-------------------------------------");
                System.out.println("  Return Successful!");
                System.out.println("  Item  : " + loan.getItem().getTitle());
                System.out.println("  Member: " + member.getName());
                System.out.println("  Thank you for returning on time!");
                System.out.println("-------------------------------------");
                return;
            }
        }
        System.out.println("  Item not found in your active loans.");
    }

    public void addItem(LibraryItem item) {
        items.add(item);
    }
}