package week_4_task_HOP.ContactManager;

import java.util.ArrayList; 
import java.util.Scanner; 
public class ContactManager {
	
	private final ArrayList<Contact> contacts = new ArrayList<>();
	private final Scanner scanner = new Scanner(System.in); 
	
	// Add Contact
	public void addContact() { 
		
		System.out.print("Enter name: "); 
		String name = scanner.nextLine().trim();
		     if (name.isEmpty()) { 
			       System.out.println("Name cannot be empty."); 
		           return; 
		        }
		
		System.out.print("Enter phone number: ");
		String phoneNumber = scanner.nextLine().trim(); 
		     if (!phoneNumber.matches("\\d{10}")) { 
			       System.out.println("Phone number must contain exactly 10 digits."); 
			        return; 
			   } 
		
	// Prevent duplicate phone numbers 
		for (Contact contact : contacts) { 
			if (contact.getPhoneNumber().equals(phoneNumber)) { 
				System.out.println("A contact with this phone number already exists.");
				 return; 
				} 
			} 
		
		System.out.print("Enter email: ");
		String email = scanner.nextLine().trim(); 
		    if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
			    System.out.println("Invalid email address.");
			     return; 
			   } 
		    
		contacts.add(new Contact(name, phoneNumber, email)); 
		System.out.println("Contact added successfully."); 
		
	}
	
	
	// View Contacts
	public void viewContacts() { 
		
		if (contacts.isEmpty()) {
			 System.out.println("No contacts available.");
			  return; 
			} 
		
		System.out.println("\n----- Contact List -----"); 
		for (int i = 0; i < contacts.size(); i++) {
			System.out.println((i + 1) + ". " + contacts.get(i)); 
			} 
		
	} 
	
	
	// Search Contact 
	public void searchContact() { 
		
		System.out.print("Enter name to search: ");
		String searchName = scanner.nextLine().trim();
		
		boolean found = false; 
		
		for (Contact contact : contacts) {
			if (contact.getName().toLowerCase() .contains(searchName.toLowerCase())) { 
				System.out.println(contact);
				found = true;
				} 		
		} 
		
		if (!found) {
			System.out.println("Contact not found."); 
		} 
		
	}
	
	
	// Update Contact 
	public void updateContact() { 
		
		System.out.print("Enter phone number of contact to update: "); 
		String phoneNumber = scanner.nextLine().trim(); 
		Contact contactToUpdate = null; 
		
		for (Contact contact : contacts) { 
			if (contact.getPhoneNumber().equals(phoneNumber)) {
				contactToUpdate = contact;
				break;
				} 
			} 
		
		if (contactToUpdate == null) {
			System.out.println("Contact not found."); 
			return;
			}
		
		System.out.print("Enter new name: ");
		String newName = scanner.nextLine().trim();
		      if (newName.isEmpty()) { 
			        System.out.println("Name cannot be empty.");
			          return;
			      }
		      
		System.out.print("Enter new email: ");
		String newEmail = scanner.nextLine().trim(); 
		      if (!newEmail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
			        System.out.println("Invalid email address."); 
			          return;
			      } 
		      
		      
		contactToUpdate.setName(newName);
		contactToUpdate.setEmail(newEmail);
		
		System.out.println("Contact updated successfully.");
		
	} 
	
	// Delete Contact
	public void deleteContact() { 
		
		System.out.print("Enter phone number of contact to delete: "); 
		String phoneNumber = scanner.nextLine().trim(); 
		
		for (Contact contact : contacts) { 
			if (contact.getPhoneNumber().equals(phoneNumber)) { 
				  contacts.remove(contact); 
				 System.out.println("Contact deleted successfully."); 
				return;
			  } 
			}
		System.out.println("Contact not found.");
		
	}
	
	
	
	// Menu
	public void showMenu() {
		while (true) {
			System.out.println("\n========== CONTACT MANAGER ==========");
			System.out.println("1. Add Contact");
			System.out.println("2. View Contacts"); 
			System.out.println("3. Search Contact");
			System.out.println("4. Update Contact");
			System.out.println("5. Delete Contact");
			System.out.println("6. Exit"); 
			System.out.println("=====================================");
			
			System.out.print("Enter your choice: ");
			String choice = scanner.nextLine(); 
			
			switch (choice) { 
			
			case "1": 
				addContact(); 
			    break;
			
			case "2": 
				viewContacts(); 
				break;
				
			case "3": 
				searchContact(); 
				break; 
				
			case "4": 
				updateContact(); 
				break;
				
			case "5": 
				deleteContact(); 
				break; 
				
			case "6": 
				System.out.println("Thank you for using Contact Manager."); 
				scanner.close(); 
				return; 
				
			default: 
				System.out.println("Invalid choice. Please try again.");
				}
			
		} 
		
	} 
	
}
