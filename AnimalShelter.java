import StackQueueTree.*;
import java.util.*;
import javafx.collections.*;
import javafx.scene.control.*;

public class AnimalShelter extends BinarySearchTree<Pet>
{
    // Initialize/constructor
    public AnimalShelter()
    {
        clear();
        add(new Pet("Sparky", 1, "Dalmatian"));
        add(new Pet("Macho", 2, "Chihuahua"));
    }
    
    // Find all pets in the shelter
    public LinkedList<Pet> findAllPets()
    {
        LinkedList<Pet> petList = new LinkedList<>();
        Iterator<Pet> iter = getPostorderIterator();
        while (iter.hasNext())
        {
            petList.add(iter.next());
        }
        return petList;
    }
    
    // Method used to search for a certain pet
    public Pet searchPet(int nameTag)
    {
        Pet p = new Pet("Name", nameTag, "Type");
        Iterator<Pet> iter = getPostorderIterator();
        while (iter.hasNext())
        {
            Pet next = iter.next();
            if (p.compareTo(next) == 0)
            {
                return next;
            }
        }
        return null;
    }
    
    // Adopt a pet based on name tag number
    public Pet adopt(int nameTag, LinkedList<Pet> adopted)
    {
        Pet searched = searchPet(nameTag);
        if (searched != null)
        {
            remove(searched);
            adopted.add(searched);
        }
        return searched;
    }
    
    // Enter a name tag number for a given pet
    public Integer enterNameTag()
    {
        int nameTag;
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Enter Name Tag #");
        dialog.setHeaderText("Enter Name Tag # for a specific pet: ");
        dialog.setContentText("Name Tag #: ");
        Optional<String> optional = dialog.showAndWait();
        if (optional.isPresent())
        {
            try
            {
                nameTag = Integer.parseInt(optional.get());
            }
            catch (NumberFormatException err)
            {
                return enterNameTag();
            }
        }
        else
        {
            return enterNameTag();
        }
        return nameTag;
    }
    
    // Surrender a pet already adopted
    public Pet surrender(int nameTag, LinkedList<Pet> adopted)
    {
        if (adopted.isEmpty())
        {
            return null;
        }
        Iterator<Pet> iter  = adopted.iterator();
        Pet p = new Pet("Name", nameTag, "Type");
        while (iter.hasNext())
        {
            Pet next = iter.next();
            if (p.compareTo(next) == 0)
            {
                adopted.remove(next);
                add(next);
                return next;
            }
        }
        return null;
    }
    
    // Convert to Observable List to convert the data table-friendly
    public ObservableList<Pet> toObservableList()
    {
        ObservableList<Pet> petList = FXCollections.observableArrayList();
        Iterator<Pet> iter = getPreorderIterator();
        while (iter.hasNext())
        {
            petList.add(iter.next());
        }
        return petList;
    }
    
    // Main method to test if tree operations are working right
    public static void main(String[] args)
    {
        AnimalShelter f = new AnimalShelter();
        LinkedList list = f.findAllPets();
        Iterator<Pet> iter = list.iterator();
        while (iter.hasNext())
        {
            System.out.println(iter.next().toString());
        }
    }
}
