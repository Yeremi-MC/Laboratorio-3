import java.util.HashMap;
import java.util.Map;
import java.io.*;
public class AddressBook {
    private HashMap<String,Contact>contacts;
    private final String FILE_NAME="contacts.dat";
    public AddressBook() {
        this.contacts=new HashMap<>();
        loadContacts();
    }
    public void storeContacts(){
        try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(FILE_NAME))){
            oos.writeObject(contacts);
            System.out.println("File contact saved successfully.");
        }catch(IOException e){
            System.out.println("Error saving file: "+e.getMessage());
        }
    }
    public void loadContacts(){
        File file=new File(FILE_NAME);
        if(!file.exists()){
            return;
        }
        try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream(FILE_NAME))){
            contacts=(HashMap<String,Contact>) ois.readObject();
        }catch(IOException|ClassNotFoundException e){
            System.out.println("Error saving file: "+e.getMessage());    
        }
    }
    public void addContact(Contact contact){
        if(contacts.containsKey(contact.getEmail())){
            System.out.println("A contact with this email already exists.");
        }else{
            contacts.put(contact.getEmail(),contact);
            System.out.println("Contact added successfully.");
            storeContacts();
        }
    }
    public void viewContacts(){
        if(contacts.isEmpty()){
            System.out.println("The address book is empty.");
            return;
        }
        for(Contact contact:this.contacts.values()){
            System.out.println(contact.print());
        }
    }
    public void searchContact(String email){
        if(contacts.containsKey(email)){
            System.out.println(contacts.get(email).print());
        }else{
            System.out.println("Contact not found");
        }
    }
    public void deleteContact(String email){
        if(contacts.containsKey(email)){
            contacts.remove(email);
            System.out.println("Contact deleted.");
            storeContacts();
        }else{
            System.out.println("No contact found with the provided email");
        }
    }
}
