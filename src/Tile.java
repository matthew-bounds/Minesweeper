public class Tile {
    private int number;
    private boolean hasBeenRevealed;

    public Tile(int newNumber)
    {
        number = newNumber;
        hasBeenRevealed = false;
    }

    public int getNumber() {
        return number;
    }

    public void increment()
    {
        number++;
    }

    public boolean getHasBeenRevealed() {
        return hasBeenRevealed;
    }

    public void setHasBeenRevealed(boolean newHasBeenRevealed)
    {
        hasBeenRevealed = newHasBeenRevealed;
    }

    public String print()
    {
        if(!hasBeenRevealed)
            return "O";//"▉";
        else if(number == -1)
            return "*";
        else if(number == 0)
            return " ";

        return number + "";
    }
}
