import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;



public class PatronManagement {
    public void Add(ArrayList<Patron> patronlist, Patron patron){
        Iterator<Patron> it = patronlist.iterator();

        while (it.hasNext()) {
            Patron user = it.next();

            if (user.getID() == patron.getID()) {
                return;
            }
        }
        patronlist.add(patron);
    }
    public void Edit(String comand, Patron patron){
        Scanner entrada = new Scanner(System.in);
        
        switch (comand.toUpperCase()) {
            case "NAME":
                System.out.println("Digite o novo nome: ");
                String name = entrada.next();
                patron.setName(name);
                break;

            case "ID":
                System.out.println("Digite o novo ID: ");
                int ID = entrada.nextInt();
                patron.setID(ID);
                break;
            
            default:
                System.out.println("Opção inválida");
                break;
        }
        entrada.close();

    }
    public void Delete(ArrayList<Patron> patronlist, Patron patron){
        Iterator<Patron> it = patronlist.iterator();

        while (it.hasNext()) {
            Patron user = it.next();

            if (user.getID() == patron.getID()) {
                it.remove();
                break;
            }
        }
    }


    public void Search(String comand, ArrayList<Patron> patronlist){
        Scanner entrada = new Scanner(System.in);
        Iterator<Patron> it = patronlist.iterator();
        switch (comand.toUpperCase()) {
            case "NAME":
                System.out.println("Digite o nome: ");
                String name = entrada.next();
                while (it.hasNext()) {
                    Patron patron = it.next();

                    if (patron.getName().equalsIgnoreCase(name)) {
                        patron.getName();
                        patron.getID();
                        patron.getContact();
                        patron.booksHistoric();
                        break;
                    }
                }   
                break;

            case "ID":
                System.out.println("Digite o ID: ");
                int ID = entrada.nextInt();
                while (it.hasNext()) {
                    Patron patron = it.next();

                    if (patron.getID() == ID) {
                        patron.getName();
                        patron.getID();
                        patron.getContact();
                        patron.booksHistoric();
                        break;
                    }
                }
                break;
                
            default:
                System.out.println("Opção inválida");
                break;
                
        }      
        entrada.close();      
    }
}
