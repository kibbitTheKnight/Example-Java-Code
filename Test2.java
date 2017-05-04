import java.util.Scanner;
import java.util.Scanner;
/**
 * Activates the start of the game.
 * 
 * @Keira Taylor
 */
public class Test2
{
    private Village vil = new Village();
    private Randomness rand = new Randomness();
    public void starting()
    {
        Scanner in = new Scanner(System.in);
        String choice = "";
        
        System.out.println("Enter your selection ([N]ext, [A]dd villager, [S]how, or [Q]uit): ");
        System.out.println("([F]ive Nexts, [T]en Nexts, Denote [G]atherer, Denote St[u]dent");
        choice = in.next();
        
        if(choice.equalsIgnoreCase("n"))
        {
            next();
        }
        else if(choice.equalsIgnoreCase("a"))
        {
            addVillager();
        }
        else if(choice.equalsIgnoreCase("s"))
        {
            show();
        }
        else if(choice.equalsIgnoreCase("q"))
        {
            System.exit(0);
        }
        else if(choice.equalsIgnoreCase("f"))
        {
            for(int i = 0; i < 5; i++)
            {
                next();
            }
        }
        else if(choice.equalsIgnoreCase("t"))
        {
            for(int i = 0; i < 10; i++)
            {
                next();
            }
        }
        else if(choice.equalsIgnoreCase("g"))
        {
            gatherer();
        }
        else if(choice.equalsIgnoreCase("u"))
        {
            student();
        }
        else
        {
            System.out.println("That was not a valid choice");
        }
        starting();
    }
    public void next()
    {
        Village.next();
    }
    public void addVillager()
    {
        Village.addVillager();
    }
    public void show()
    {
        System.out.println(vil);
        System.out.println("Food: " + Village.numFood);
    }   
    public void gatherer()
    {
        int counter = -20; //to make sure this doesn't endlessly repeat //repeats villageNumber + 10
        int num = rand.generate(0, Village.numVillagers - 1);
        while(Village.getRealVillager(num).getJob() == "Gatherer" && counter < Village.numVillagers)
        {
            counter++;
            num = rand.generate(0, Village.numVillagers - 1);
            System.out.println(Village.getRealVillager(num).getJob());
            System.out.println(Village.getRealVillager(num).getName() + " is already a gatherer");
        }
        Village.getRealVillager(num).setJob("Gatherer");
        System.out.println(Village.getRealVillager(num).getName() + " has become a gatherer");
    }
    public void student()
    {
        int counter = -20; //to make sure this doesn't endlessly repeat //repeats villageNumber + 10
        int num = rand.generate(0, Village.numVillagers - 1);
        while(counter < Village.numVillagers && (Village.getRealVillager(num).getAge() > 15))
        {
            counter++;
            num = rand.generate(0, Village.numVillagers - 1);
            if(Village.getRealVillager(num).getJob().equals("Student"))
                System.out.println(Village.getRealVillager(num).getName() + " is already a student");
        }
        Village.getRealVillager(num).setJob("Student");
        System.out.println(Village.getRealVillager(num).getName() + " has become a student");
    }
}
