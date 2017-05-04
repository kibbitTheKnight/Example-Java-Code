
/**
 * Creates a villager either based off of input or purely on randomness.
 * 
 * @Keira Taylor
 */
public class GenerateVillager
{
    public static final String[] girlNames = {"Anne", "Beth", "Catharine", "Delilah", "Eve", "Felicity" , "Gail",
                                              "Haily", "Isabella", "Juliet", "Kylie", "Leila", "Marie", "Natalie",
                                              "Olivia", "Penelope", "Quin", "Rachel", "Samantha", "Taylor", 
                                              "Ulyssa", "Veronica", "Wendy", "Xamara", "Yvonne", "Zoe"};
    public static final String[] boyNames = {"Arnold", "Bob", "Cole", "Daniel", "Erin", "Frank", "George",
                                             "Henry", "Iskander", "Jeff", "Kiff", "Leander"};
    public static final String[] nameParts = {"ab", "ana", "bi", "ce", "da", "e", "fi", "gi", "ha"};
    public static final String[] girlSuffix = {"sh", "ta", "la", "ma", "sa", "ing", "ach", "dy"};
    public static final String[] boySuffix = {"sh", "to", "lo", "mo", "so", "ing", "ach", "ete"};
    public Randomness ran = new Randomness();
    public static long ID = -100000000;
    public GenerateVillager()
    {
        
    }
    //maybe make overridden classes for how many things to generate when given input (e.g., if gender is given
    //generate name; if nothing, generate gender and name
    public String generateName(String gender)
    {
        int nameChoice;
        String name = "";
        if(gender.equals("Female"))
        {
            nameChoice = ran.generate(0, (girlNames.length - 1));
            name = girlNames[nameChoice];
        }
        else
        {
            nameChoice = ran.generate(0, (boyNames.length - 1));
            name = boyNames[nameChoice];
        }
        return name;
    }
    public String createName(String gender)
    {
        String name = "";
        String firstLetter;
        for(int i = 0; i < ran.generate(1, 3); i++)
        {
            name+= nameParts[ran.generate(0, nameParts.length - 1)];
        }
        if(gender.equals("Female"))
        {
            name+= girlSuffix[ran.generate(0, girlSuffix.length - 1)]; //adds girl suffix to name
        }
        else
        {
            name+= boySuffix[ran.generate(0, boySuffix.length - 1)];
        }
        firstLetter = name.substring(0,1);
        firstLetter = firstLetter.toUpperCase(); //capitalizes first letter
        name = firstLetter + name.substring(1);
        return name;
    }
    public String generateGender()
    {
        int genChoice = ran.generate(0, 1);
        if(genChoice == 1)
        {
            return "Female";
        }
        else
        {
            return "Male";
        }
    }
    public int generateAge()
    {
        return (ran.generate(15, 30));
    }
    public String[] hair = {"Blonde", "Brown", "Black", "Red", "White"};//you can use toLowerCase
    public String[] eyes = {"Yellow-Brown", "Green", "Hazel", "Light Blue", "Dark Blue", "Purple", "Light Brown",
                            "Dark Brown", "Black", "Gray"};
    public String[] height = {"Amazingly Tiny", "Small", "Medium", "Tall", "Gigantic"};
    public String[] complexion = {"Fair", "Tan", "Dark"};
    public final String[] FEATURES = {"Hair Color", "Eye Color", "Height", "Complexion"};
    public String[] generateFeatures()
    {
        String[] features = new String[FEATURES.length];
        int choice = ran.generate(0, hair.length - 1);
        features[0] = hair[choice]; //picks random feature from list
        
        choice = ran.generate(0, eyes.length - 1);
        features[1] = eyes[choice];
        
        choice = ran.generate(0, height.length - 1);
        features[2] = height[choice];
        
        choice = ran.generate(0, complexion.length - 1);
        features[3] = complexion[choice];
        
        
        return features;
    }
    public String[] favoriteColor = {"Red", "Orange", "Yellow", "Green", "Blue", "Purple", "Brown", "Black",
                                     "Pink", "Rainbow"}; //can put an option like "none"
    public String[] favoriteActivity = {"Playing board games", "Talking with friends", "Eating", "Working",
                                        "Running", "Skipping", "Sleeping", "Fishing", "Star-Gazing", 
                                        "Daydreaming", "Sewing", "Cooking", "Cleaning", "Racing", "Climbing"}; 
                                        //maybe, eventually, favorite activities can have effects (i.e, she who
                                        //likes sewing will be a better tailor. Simple if condition
    public final String[] FAVORITES = {"Favorite Color", "Favorite Activity"};
    public String[] generateFavorites()
    {
        String[] favorites = new String[2];
        int choice = ran.generate(0, favoriteColor.length - 1);
        favorites[0] = favoriteColor[choice];
        
        choice = ran.generate(0, favoriteActivity.length - 1);
        favorites[1] = favoriteActivity[choice];
        
        
        return favorites;
    }
    public long generateID()
    {
        ID++;
        return ID;
    }
}
