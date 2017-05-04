import java.util.ArrayList;
/**
 * All the information for a villager.
 * 
 * @Keira Taylor
 */
public class Villager
{
    public int myAge, mySkill, myHealth;
    public double myDeathChance, myDeathChanceAdd;
    public long myID;
    public String myName, myJob, mySex, myJobType, myStory;
    public boolean isLiving, isTeacher;
    public String[] myFeatures, myFavorites;
    public ArrayList<Villager> myFamily = new ArrayList<Villager>();
    public Randomness ran = new Randomness();
    GenerateVillager gen = new GenerateVillager();
    public double x = 0; public double y = 80;
    /**
     * Constructor for objects of class Villager
     */
    public Villager(int age, String name, String job, String gender)
    {
        myAge = age;
        myName = name;
        myJob = job;
        setJobType();
        mySkill = 0;
        mySex = gender;
        myDeathChance = (0.5 * age);
        isLiving = true;
        myHealth = 100;
        myDeathChanceAdd = 0.3;
        myID = gen.generateID();
        isTeacher = false;
    }
    public Villager()
    {
        myAge = 0;
        myName = "Nothing";
        myJob = "Jobless";
        setJobType();
        mySkill = 0;
        mySex = "gender";
        myDeathChance = 0;
        isLiving = true;
        myHealth = 100;
        myDeathChanceAdd = 0.3;
        myID = gen.generateID();
        isTeacher = false;
    }
    public String toString()
    {
        String name = String.format("%-10s ( %6s, %3d Years Old) the %s",
                                    myName, mySex, myAge, myJob);
        if(isTeacher)
            name += " (Educated)";
        return name;
    }
    public String toStringCasual()
    {
        String name = myName + " ( " + mySex + ", " + myAge + " Years Old ) the " + myJob;
        if(isTeacher)
            name += " (Educated)";
        return name;
    }
    
    public void ageUp()
    {
        //life and death
        myAge++;
        if(myHealth < 100)
        {
            myDeathChanceAdd += ((100 - myHealth) * .15);
        }
        if(Village.numFood > 0 && myHealth < 100)
        {
            myHealth++; //gradually get healthier if food is present
        }
        if(Village.numFood < 0)
        {
            myHealth -= 2; //lose two health for each year food is not present
        }
        myDeathChance += myDeathChanceAdd;
        
        //if blah blah deathchance
        int chance = ran.generate((int)x, (int)y); //designates chance to die naturally (1-100 + deathchance)
        if(chance + (myDeathChance * .5) > 99 || myHealth <= 0)
        {
            //System.out.println(myName + " " + chance + " + " + myDeathChance * .5 + " = " + 
                               //(chance + (myDeathChance * .5)));
            isLiving = false;
            //System.out.println("x " + x + " y " + y);
        }
        x+= .1;
        y+= .1;
        
        //improvement
        if(mySkill >= 10)
        {
            isTeacher = true;
        }
        if(myAge == 15 && (myJobType.equals("Child") || myJobType.equals("Student")))
        {
            setJob("Jobless");
        }
    }
    public void addHealth(int health)
    {
        myHealth += health;
    }
    public void setJob(String job)
    {
        if(myAge >= 15)
        {
            myJob = job;
            setJobType(myJob);
        }
        else if(myAge < 15 && myAge > 3 && job.equals("Student"))
        {
            myJob = job;
            setJobType(myJob);
        }
    }
    public void setJobType() //refreshes
    {
        setJobType(myJob);
    }
    public void setJobType(String job)
    {
        if(job.equals("Jobless"))
        {
            myJobType = "Unemployed";
        }
        else if(job.equals("Gatherer") || job.equals("Hunter"))
        {
            myJobType = "Food Production";
        }
        else if(job.equals("Teacher") || job.equals("Master"))
        {
            myJobType = "Education"; //more than educated, specific field of teaching maybe
        }
        else if(job.equals("Student") || job.equals("Apprentice"))
        {
            myJobType = "Student";
        }
        else if(job.equals("Miner") || job.equals("Stonecutter") || job.equals("Logger"))
        {
            myJobType = "Raw Material Production";
        }
        else
        {
            myJobType = "Child";
        }
    }
    public void refresh()
    {
        setJobType(myJob);
    }
    public void setMisc()
    {
        GenerateVillager gen = new GenerateVillager();
        
        myFeatures = gen.generateFeatures();
        myFavorites = gen.generateFavorites();
        
    }
    
    //job methods
    public void learnSkills()
    {
        mySkill += ran.generate(0, 2);
    }
    public void learnSkills(int min, int max)
    {
        mySkill += ran.generate(min, max);
    }
    
    public String getJob()
    {
        return myJob;
    }
    public String getJobType()
    {
        return myJobType;
    }
    public int getSkill()
    {
        return mySkill;
    }
    public Object getMe()
    {
        return this;
    }
    public long getID()
    {
        return myID;
    }
    public int getAge()
    {
        return myAge;
    }
    public String getStory() // change this to display in user friendly narrative w/ format
    {
        String stuff = "";
        if(myFeatures[0] != null && myFavorites[0] != null)
        {
            stuff = myFeatures[0] + " " + myFeatures[1] + " ";
            stuff+= myFavorites[0] + " " + myFavorites[1];
        }
        else
        {
            stuff = "This person has no story, yet...";
        }
        return stuff;
    }
    public String getName()
    {
        return myName;
    }
    public boolean getLife()
    {
        return isLiving;
    }
    public boolean getTeacher()
    {
        return isTeacher;
    }
    public String toSave()
    {
        String saving;
        saving = String.format("%s %s %s %s %s", myName, myJob, mySex, myJobType, myStory);
        saving += String.format("%d %d %d", myAge, mySkill, myHealth);
        saving += String.format("%f", myDeathChance);
        for(String item : myFeatures)
        {
            saving += item;
        }
        for(String item : myFavorites)
        {
            saving += item;
        }
        for(Villager person : myFamily)
        {
            saving += person;
        }
        return saving;
    }
}
