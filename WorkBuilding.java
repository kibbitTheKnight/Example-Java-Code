import java.util.ArrayList;
/**
 * This controls a WorkBuilding, which is a specialized building.
 * 
 * @Keira Taylor
 */
public class WorkBuilding extends Building
{
    public ArrayList<Villager> myWorkers = new ArrayList<Villager>();
    /**
     * Constructor for objects of class WorkBuilding
     */
    public WorkBuilding(int limit, String type)
    {
        super(limit, type);
    }
    public void addResident(Villager villager)
    {
        myWorkers.add(villager);
    }
    public void removeResident(Villager villager)
    {
        myWorkers.remove(villager);
    }
    
    public String toString()
    {
        return String.format("%15s : %d / %d workers", getType(), getCurrent(), getLimit());
    }
}
