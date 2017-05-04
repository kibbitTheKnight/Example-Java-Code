
/**
 * This class controls buildings.
 * 
 * @Keira Taylor
 */
public class Building
{
    private int myLimit, myCurrent;
    public String myType;
    /**
     * Constructor for objects of class Building
     */
    public Building(int limit, String type)
    {
        myLimit = limit;
        myCurrent = 0;
        myType = type;
    }
    public String toString()
    {
        String name = String.format("%s: %d/%d Workers", myType, myCurrent, myLimit);
        return name;
    }
    
    public String getType()
    {
        return myType;
    }
    public int getLimit()
    {
        return myLimit;
    }
    public int getCurrent()
    {
        return myCurrent;
    }
}
