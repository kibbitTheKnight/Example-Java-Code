
public class Randomness
{
    public int generate(int min, int max)
    {
        double randomNum = Math.random();
        int difference = (max + 1) - min;
        double step = difference * randomNum;
        int answer = (int)(step + min);
        return answer;
    }
}