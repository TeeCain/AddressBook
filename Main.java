package AddressBook;


//Imports to access ArrayList and Scanner functionalities throughout program. 
import java.util.ArrayList;
import java.util.Scanner;

//Contact constructor
class Contact {
  private String name;
  private String address;
  private int phoneNumber;

  // Initialization of Contact method to use in the AddressBook function and the main program
  public Contact(String name, String address, int phoneNumber) {
    this.name = name;
    this.address = address;
    this.phoneNumber = phoneNumber;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public int getPhoneNumber() {
    return phoneNumber;
  }
}

//AddressBook class with ArrayList to store contacts in an ArrayList
class AddressBook {
  public static ArrayList<Contact> contacts = new ArrayList<Contact>();

  //Adds user's input to Contact class and prints a confirmation that contact has been added
  public void addContact(Contact newContact) {
    contacts.add(newContact);
    System.out.println("Contact added.");
  }
//Edit's user's input to Contact class and prints a confirmation that contact has been edited
  public void editContact(Contact oldContact, Contact newContact) {
    int index = contacts.indexOf(oldContact);
    contacts.set(index, newContact);
    System.out.println("Contact edited.");
  }
//Delete's user's input to Contact class and prints a confirmation that contact has been deleted
  public void deleteContact(Contact contactToDelete) {
    contacts.remove(contactToDelete);
    System.out.println("Contact deleted.");
  }
//Display's user's input that is stored in the Contact class and prints the everything in one line
  public void displayContacts() {
    for (Contact contact : contacts) {
      System.out.println(contact.getName() + " " + contact.getAddress() + " " + contact.getPhoneNumber());
    }
  }

  //Getter method that initializes a new Contact variable
  public Contact[] getContact() {
    return contacts.toArray(new Contact[0]);
  }
}

//Main Program function that user interacts with
public class Main {
  public static void main(String[] args) {
    AddressBook addressBook = new AddressBook();
    Scanner input = new Scanner(System.in);
    String choice = "";

    //While loop that gives the user the option to choose their actions 
    while (!choice.equals("quit")) {
      System.out.println("Choose an action: add, edit, delete, display, or quit");
      choice = input.nextLine().toLowerCase();

      //Switch statement to determine the action to perform based on the user's input.
      switch (choice) {
        case "add":
          System.out.println("Enter name:");
          String name = input.nextLine();
          System.out.println("Enter address:");
          String address = input.nextLine();
          System.out.println("Enter phone number:");
          int phoneNumber = input.nextInt();
          input.nextLine(); // consume the remaining newline
          final Contact newContact = new Contact(name, address, phoneNumber);
          addressBook.addContact(newContact);
          break;

        case "edit":
          System.out.println("Enter name of contact to edit:");
          String oldName = input.nextLine();
          ArrayList<Contact> editResults = new ArrayList<Contact>();
          for (Contact contact : addressBook.getContact()) {
            if (contact.getName().equals(oldName)) {
              editResults.add(contact);
            }
          }
          if (editResults.size() == 0) {
            System.out.println("No contacts found with that name.");
          } else if (editResults.size() == 1) {
            Contact oldContact = editResults.get(0);
            System.out.println("Enter new name:");
            name = input.nextLine();
            System.out.println("Enter new address:");
            address = input.nextLine();
            System.out.println("Enter new phone number:");
            phoneNumber = input.nextInt();
            input.nextLine(); 
            final Contact finalContact = new Contact(name, address, phoneNumber);
            addressBook.editContact(oldContact, finalContact);
          } else {
            System.out.println("Multiple contacts found with that name:");
            for (Contact contact : editResults) {
              System.out.println(contact.getName() + " " + contact.getAddress() + " " + contact.getPhoneNumber());
            }
          }
          break;

        case "delete":
          System.out.println("Enter name of contact to delete:");
          String deleteName = input.nextLine();
          ArrayList<Contact> deleteResults = new ArrayList<Contact>();
          for (Contact contact : addressBook.getContact()) {
            if (contact.getName().equals(deleteName)) {
              deleteResults.add(contact);
            }
          }
          if (deleteResults.size() == 0) {
            System.out.println("No contacts found with that name.");
          } else if (deleteResults.size() == 1) {
            Contact contactToDelete = deleteResults.get(0);
            addressBook.deleteContact(contactToDelete);
          } else {
            System.out.println("Multiple contacts found with that name:");
            for (Contact contact : deleteResults) {
              System.out.println(contact.getName() + " " + contact.getAddress() + " " + contact.getPhoneNumber());
            }
          }
          break;

        case "display":
          addressBook.displayContacts();
          break;

        case "quit":
          System.out.println("Goodbye!");
          break;

        default:
          System.out.println("Invalid input.");
      }
    }
//Close the Scanner object after the while loop.
    input.close();

  }
}
