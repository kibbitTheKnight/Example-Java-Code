import java.util.ArrayList;
/**
 * This controls a house, which is a specialized village.
 * 
 * @Keira Taylor 
 */
public class House extends Building
{
    public int myFood;
    public ArrayList<Villager> myResidents = new ArrayList<Villager>();
    /**
     * Constructor for objects of class House
     */
    public House(int limit)
    {
        super(limit, "House");
    }
    public String toString()
    {
        String residentList = "";
        for(Villager vill: myResidents)
        {
            residentList += vill + "\n";
        }
        String name = String.format("House %nResidents:%n%s", residentList);
        return name;
    }
    public void addFood(int food)
    {
        myFood += food;
    }
    public void addResident(Villager villager)
    {
        myResidents.add(villager);
    }
    public void removeResident(Villager villager)
    {
        myResidents.remove(villager);
    }
}
