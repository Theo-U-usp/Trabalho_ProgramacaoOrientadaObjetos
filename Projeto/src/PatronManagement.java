import java.util.ArrayList;
import java.util.Iterator;



public class PatronManagement {
    public void AddPatron(ArrayList<Patron> patronlist, Patron patron){
        Iterator<Patron> it = patronlist.iterator();

        while (it.hasNext()) {
            Patron user = it.next();

            if (user.getID() == patron.getID()) {
                return;
            }
        }
        patronlist.add(patron);
    }
    public void EditString(Patron patron, String field, String newValue) {

        switch (field) {
            case "Name":
                patron.setName(newValue);
                break;

            case "Contact":
                patron.setContact(newValue);
                break;
        }
    }
    public void EditInt(Patron patron, String field, int newValue) {

        switch (field) {
            case "ID":
                patron.setID(newValue);
                break;

        }
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


    public ArrayList<Patron> Search(String command, ArrayList<Patron> patronList, String value) {

        ArrayList<Patron> result = new ArrayList<>();

        for (Patron patron : patronList) {

            switch (command.toUpperCase()) {

                case "NAME":
                    if (patron.getName()
                            .equalsIgnoreCase(value)) {
                        result.add(patron);
                    }
                    break;


                case "ID":
                    if (patron.getID()
                            == Integer.parseInt(value)) {
                        result.add(patron);
                    }
                    break;
            }
        }

        return result;
    }
    public boolean idExists(ArrayList<Patron> patronList, int id) {

        for (Patron patron : patronList) {
            if (patron.getID() == id) {
                return true;
            }
        }

        return false;
    }
}
