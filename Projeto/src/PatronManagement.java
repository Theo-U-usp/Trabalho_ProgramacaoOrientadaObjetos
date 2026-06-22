import java.util.ArrayList;
import java.util.Iterator;

/**
 * Provides management operations for patrons.
 *
 * <p>This class contains methods to add, edit, delete, search and validate
 * patrons. Patron ID is treated as a String and validated by checking if
 * every character is a numeric digit.</p>
 */
public class PatronManagement {

    /**
     * Adds a patron if the ID does not already exist.
     *
     * @param patronlist list of patrons
     * @param patron patron to add
     */
    public void AddPatron(ArrayList<Patron> patronlist, Patron patron) {
        if (idExists(patronlist, patron.getID())) {
            return;
        }

        patronlist.add(patron);
    }

    /**
     * Edits a String field of a patron.
     *
     * @param patron patron to edit
     * @param field field name
     * @param newValue new value
     */
    public void EditString(Patron patron, String field, String newValue) {
        switch (field) {
            case "Name":
                patron.setName(newValue);
                break;

            case "ID":
                patron.setID(newValue);
                break;

            case "Contact":
                patron.setContact(newValue);
                break;
        }
    }

    /**
     * Deletes a patron from the list.
     *
     * @param patronlist list of patrons
     * @param patron patron to delete
     */
    public void Delete(ArrayList<Patron> patronlist, Patron patron) {
        Iterator<Patron> it = patronlist.iterator();

        while (it.hasNext()) {
            Patron user = it.next();

            if (user.getID().equals(patron.getID())) {
                it.remove();
                break;
            }
        }
    }

    /**
     * Searches patrons by name or ID.
     *
     * @param command search field
     * @param patronList list of patrons
     * @param value searched value
     * @return list of matching patrons
     */
    public ArrayList<Patron> Search(String command, ArrayList<Patron> patronList, String value) {
        ArrayList<Patron> result = new ArrayList<>();

        if (value == null) {
            return result;
        }

        String searchedValue = value.trim();

        for (Patron patron : patronList) {
            switch (command.toUpperCase()) {
                case "NAME":
                    if (patron.getName().equalsIgnoreCase(searchedValue)) {
                        result.add(patron);
                    }
                    break;

                case "ID":
                    if (patron.getID().equalsIgnoreCase(searchedValue)) {
                        result.add(patron);
                    }
                    break;
            }
        }

        return result;
    }

    /**
     * Checks whether a text is null or empty.
     *
     * @param value text to check
     * @return true if the text is null or empty
     */
    public boolean isEmptyInput(String value) {
        return value == null || value.trim().isEmpty();
    }

    /**
     * Validates a patron ID.
     *
     * <p>The ID is stored as String, but every character must be a digit.</p>
     *
     * @param id ID to validate
     * @return true if the ID is not empty and contains only digits
     */
    public boolean isValidPatronID(String id) {
        if (isEmptyInput(id)) {
            return false;
        }

        String cleanID = id.trim();

        for (int i = 0; i < cleanID.length(); i++) {
            if (!Character.isDigit(cleanID.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if a patron ID already exists.
     *
     * @param patronlist list of patrons
     * @param id ID to check
     * @return true if the ID already exists
     */
    public boolean idExists(ArrayList<Patron> patronlist, String id) {
        return idExists(patronlist, id, null);
    }

    /**
     * Checks if a patron ID already exists, ignoring one patron when necessary.
     *
     * <p>This is useful when editing a patron, because the selected patron
     * should be allowed to keep their own ID.</p>
     *
     * @param patronlist list of patrons
     * @param id ID to check
     * @param ignoredPatron patron ignored during the check
     * @return true if another patron already has this ID
     */
    public boolean idExists(ArrayList<Patron> patronlist, String id, Patron ignoredPatron) {
        if (id == null) {
            return false;
        }

        String cleanID = id.trim();

        for (Patron patron : patronlist) {
            if (patron != ignoredPatron && patron.getID().equals(cleanID)) {
                return true;
            }
        }

        return false;
    }
}
