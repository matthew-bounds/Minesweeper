import java.util.Random;

public class Game {

    private Tile[][] board;
    private double percentBomb;

    public Game(int width, int height, double percentBomb)
    {
        board = new Tile[height][width];
        this.percentBomb = percentBomb;

        print();
    }

    public void firstMove(int row, int col)
    {
        Random rand = new Random();

        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Tile(0);
            }
        }

        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                if(i == row && j == col)
                {
                    board[i][j] = new Tile(0);
                }
                else if(rand.nextInt(board.length * board[i].length) / (double)(board.length * board[i].length) <= percentBomb)
                {
                    // make sure the immediate vicinity of the chosen row, col is not chosen as a bomb
                    if( (i < row -1 || i > row +1) || (j < col -1 || j > col + 1) )
                    {
                        board[i][j] = new Tile(-1);
                        int startRow = Math.max(i - 1, 0);

                        for (int r = startRow; r < i + 1 + 1 && r < board.length; r++) {
                            int startCol = Math.max(j - 1, 0);

                            for (int c = startCol; c < j + 1 + 1 && c < board[i].length; c++) {
                                if (board[r][c].getNumber() != -1)
                                    board[r][c].increment();
                            }
                        }
                    }
                }

            }
        }
        move(row,col);
    }

    public boolean move(int row, int col)
    {
        board[row][col].setHasBeenRevealed(true);
        if(board[row][col].getNumber() == -1)
            return true;

        if(board[row][col].getNumber() == 0)
        {
            int startRow = Math.max(row - 1, 0);

            for (int r = startRow; r < row + 2 && r < board.length; r++) {
                int startCol = Math.max(col - 1, 0);

                for (int c = startCol; c < col + 2 && c < board[row].length; c++) {
                    if (board[r][c].getNumber() != -1) {
                        if (board[r][c].getNumber() == 0 && !board[r][c].getHasBeenRevealed())
                            move(r, c);
                        board[r][c].setHasBeenRevealed(true);
                    }
                }
            }
        }

        return false;
    }

    public void print()
    {
        String columns = "   ";
        String columnBar = "  │";
        for(int i = 0; i < board[0].length; i++) {
            columns = columns + i+ " ";
            columnBar = columnBar + "--";
        }
        System.out.println(columns);
        System.out.println(columnBar);

        for(int i = 0; i < board.length; i++)
        {
            System.out.printf("%2d│",i);
            for(int j = 0; j < board[i].length; j++)
            {
                if(board[i][j] != null)
                    System.out.print(board[i][j].print() + " ");
                else
                    System.out.print("O ");
            }
            System.out.println();
        }
    }

    public boolean isGameOver()
    {
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                if(!board[i][j].getHasBeenRevealed() && board[i][j].getNumber() != -1)
                    return false;
            }
        }
        return true;
    }

}
