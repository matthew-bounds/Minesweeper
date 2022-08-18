import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        Game game = new Game(10,10, .2);

        Scanner in = new Scanner(System.in);

        System.out.println("\nPlease enter the row,column you'd like to move");
        String input = in.nextLine();
        int row = Integer.parseInt(input.substring(0, input.indexOf(',')));
        int col = Integer.parseInt(input.substring(input.indexOf(',')+1));
        game.firstMove(row, col);

        while (!game.isGameOver())
        {
            game.print();
            System.out.println("Please enter the row,column you'd like to move");
            input = in.nextLine();
            row = Integer.parseInt(input.substring(0, input.indexOf(',')));
            col = Integer.parseInt(input.substring(input.indexOf(',')+1));
            if(game.move(row, col))
            {
                System.out.println("You LOST!!!");
                game.print();
                break;
            }

        }

        if(game.isGameOver()) {
            game.print();
            System.out.println("YOU WON!!!");
        }

    }
}
