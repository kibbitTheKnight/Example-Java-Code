import java.util.ArrayList;
/**
 * Creates a village object.
 * 
 * @Keira Taylor 
 */
public class Village
{
    // instance variables - replace the example below with your own
    public static int numVillagers, numFood, numWood, numStone;
    public static int resDifficulty; //difficulty to get resources
    public static String myName;
    public static ArrayList buildings = new ArrayList();
    public static ArrayList<Villager> villagers = new ArrayList<Villager>();
    public static GenerateVillager genVill = new GenerateVillager();
    public static Randomness ran = new Randomness();
    public static int[] statistics = new int[4];
    /**
     * Constructor for objects of class Village
     */
    public Village()
    {
        String gender = "";
        for(int i = 0; i < 10; i++)
        {
            gender = genVill.generateGender();
            villagers.add(new Villager(genVill.generateAge(), genVill.generateName(gender), "Jobless",
            gender));
        }
        numVillagers = villagers.size();
        numFood = 1000;
        numWood = 0;
        numStone = 0;
        resDifficulty = 5; //make this changeable
    }
    public String toString()
    {
        String villName = myName + "\n";
        villName += "Villagers: " + numVillagers + "\n";
        for(Object villager : villagers)
        {
            villName += villager + "\n";
        }
        return villName;
    }
    
    public static String theString()
    {
        String villName = myName + "\n";
        villName += "Villagers: " + numVillagers + "\n";
        for(Object villager : villagers)
        {
            villName += villager + "\n";
        }
        return villName;
    }
    
    public static void addVillager(Villager villager)
    {
        villager.refresh();
        villagers.add(villager);
        refresh();
    }
    public static void addVillager(String job) //testing purposes only
    {
        String gender = genVill.generateGender();
        villagers.add(new Villager(genVill.generateAge(), genVill.generateName(gender), job,
                                   gender));
        refresh();
    }
    public static void addVillager()
    {
        String gender = genVill.generateGender();
        villagers.add(new Villager(0, genVill.createName(gender), "Child",
                                   gender));
        refresh();
    }
    public static void removeVillager(Villager villager)
    {
        villagers.remove(villager);
        refresh();
    }
    public static Villager getRealVillager(int number)
    {
        return villagers.get(number);
    }
    
    public static void refresh()
    {
        numVillagers = villagers.size();
    }
    
    public static void next()
    {
        advance();
    }
    public static void setName(String name)
    {
        myName = name;
    }
    public static void setResDifficulty(int setting)
    {
        resDifficulty = setting;
    }
    public static void addFood(int food)
    {
        numFood += food;
    }
    public static void addStone(int stone)
    {
        numStone += stone;
    }
    public static ArrayList getVillagers()
    {
        return villagers;
    }
    public static Object getVillager(int num)
    {
        return villagers.get(num);
    }
    public static int getFood()
    {
        return numFood;
    }
    public static int getStone()
    {
        return numStone;
    }
    
    //advancing
    public static int foodPro, woodPro, stonePro, minerPro, builders, teachers, students, unemployed;
    public static int[] skills = new int[6]; //how many worker types; unemployed does not count
    public static void advance()
    {
        //reset
        foodPro = 0; woodPro = 0; stonePro = 0; builders = 0; unemployed = 0; //pro = producing people
        for(int i = 0; i < skills.length; i++)
        {
            skills[i] = 0;
        }
        //gather
        for(Villager vil : villagers)
        {
            if(vil.getJobType().equals("Food Production"))
            {
                foodPro++;
                skills[0] += vil.getSkill();
                vil.learnSkills();
            }
            else if(vil.getJobType().equals("Raw Material Production"))
            {
                if(vil.getJob().equals("Lumberjack"))
                {
                    woodPro++;
                    skills[1] += vil.getSkill();
                    vil.learnSkills();
                }
                else if(vil.getJob().equals("Stonecutter"))
                {
                    stonePro++;
                    skills[2] += vil.getSkill();
                    vil.learnSkills();
                }
                else
                {
                    minerPro++;
                    skills[3] += vil.getSkill();
                    vil.learnSkills();
                }
            }
            else if(vil.getJobType().equals("Construction"))
            {
                builders++;
                skills[4] += vil.getSkill();
                vil.learnSkills();
            }
            else if(vil.getTeacher() == true)
            {
                teachers++;
                skills[5] += vil.getSkill();
                vil.learnSkills();
            }
            else if(vil.getJobType().equals("Student"))
            {
                students++;
                if(vil.getJob().equals("Apprentice")) ///students learn less than apprentices
                {
                    vil.learnSkills(2,3);
                }
                else
                {
                    vil.learnSkills(1,2);
                }
            }
            else
            {
                unemployed++;
            }
        }
        //add
        //food
        int foodProduced = setAmount(foodPro, skills[0], 1, 9);
        addFood(foodProduced);
        //System.out.println("Food Produced: " + foodProduced);
        //stone
        int stoneProduced = setAmount(stonePro, skills[2], 3, 6);
        addStone(stoneProduced);
        //consume
        int foodEaten = (setAmount(villagers.size(), 0, 2, resDifficulty));
        addFood(-foodEaten);
        //System.out.println("Food Eaten: " + foodEaten);
        
        //villager stuff
        for(Villager person : villagers)
        {
            person.ageUp();
        }
        ArrayList<Long> deceased = new ArrayList<Long>();
        ArrayList<Villager> toBeRemoved = new ArrayList<Villager>();
        for(int i = 0; i < numVillagers; i++)
        {
            if(villagers.get(i).getLife() == false)
            {
                deceased.add(villagers.get(i).getID());
                toBeRemoved.add(villagers.get(i));
            }
        }
        for(Villager person : toBeRemoved)
        {
            //System.out.println("$$$$" + person + " is going to be removed");
        }
        for(int i = 0; i < deceased.size(); i++)
        {
            System.out.println(toBeRemoved.get(i).getName() + " has died of natural causes =(");
            villagers.remove(toBeRemoved.get(i));
            numVillagers--;
        }
        
        
        //maintenance
        refresh();
    }
    public static int setAmount(int producers, int skill, int x, int y)
    {
        double totalProduced = 0;
        int[] amountPer = new int[producers];
        for(int i = 0; i < producers; i++)
        {
            amountPer[i] = ran.generate(x,y); //each producer can make anywhere from x to y products + skill
        }
        for(int amount : amountPer)
        {
            totalProduced += amount;
        }
        return (int)(totalProduced + (skill * 0.05)); //every 20 skill points = +1 product
    }
}
