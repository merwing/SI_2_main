import java.util.ArrayList;

public class NHetmansProblem
{
    private int board[][];
    private int n_size;
    private ArrayList<Integer> iteration_list;

    public NHetmansProblem() {}
    public NHetmansProblem(int n_size)
    {
        this.n_size = n_size;
        this.board = new int [n_size][n_size];
        iteration_list = new ArrayList<Integer>();
        printMessage();
        fillBoard();
    }

    public void fillBoard()
    {
        for(int i = 0; i < n_size; i++)
        {
            for(int j = 0; j < n_size; j++)
            {
                board[i][j] = 0;
            }
        }
    }

    public void printMessage()
    {
        if(n_size < 4)
        {
            System.out.println("For " + n_size + "x" + n_size + " there is no solution");
        }
        else
            System.out.println("All good your size is: " + n_size + "\n");
    }

    public void displayBoard()
    {
        for(int i = 0; i < n_size; i++)
        {
            for(int j = 0; j < n_size; j++)
            {
                System.out.print(" " + board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean canBePlaced(int [][]board, int row, int col)
    {
        //check in the same row
        for(int i = col -1; i >= 0; i--)
        {
            if(board[row][i] == 1 || board[row][i] == 55)
                return false;
        }

        //left upper diagonal
        for(int i = row -1, j = col - 1; i >= 0 && j >=0; i--, j--)
        {
            if (board[i][j] == 1 || board[row][i] == 55)
                return false;
        }

        //left down diagonal
        for(int i = row+1, j = col-1; i < n_size && j >= 0; i++, j--)
        {
            if(board[i][j] == 1 || board[row][i] == 55)
                return false;
        }
        return true;
    }

    public boolean btPlaceHetmans(int board[][], int col)
    {
        if(col == n_size)
            return true;

        int itera = 0;
        boolean all_placed = false;
        for(int i = 0; i < n_size; i++)
        {
            if(canBePlaced(board, i, col))
            {
                board[i][col] = 1;
                all_placed = btPlaceHetmans(board, col+1);
            }

            if(all_placed)
            {
                break;
            }
            else
            {
                board[i][col] = 0;
            }
            itera ++;
        }
        iteration_list.add(itera);
//        System.out.println(itera);
        return all_placed;
    }

    public void startBT()
    {
        long start_time = System.nanoTime();
        displayBoard();
        btPlaceHetmans(board, 0);
        long finish_time = System.nanoTime();
        long difference = finish_time - start_time;
        displayBoard();
        calculateIterations();
        System.out.println("Time: " + difference * Math.pow(10, 6) + " [ms]");
    }

    public void calculateIterations()
    {
        int sum = 0;
        for(Integer i : iteration_list)
        {
            sum += i;
        }

        System.out.println("Iterations: " + sum);
    }

    public void clearBoard()
    {
        this.board = new int[n_size][n_size];
    }


    //point at row col
    public boolean canBePlacedFC(int board [][], int row, int col)
    {
        //check in the same row
        for(int i = col -1; i >= 0; i--)
        {
            if(board[row][i] == 1 || board[row][i] == 55)
                return false;
        }

        //left upper diagonal
        for(int i = row -1, j = col - 1; i >= 0 && j >=0; i--, j--)
        {
            if (board[i][j] == 1 || board[row][i] == 55)
                return false;
        }

        //left down diagonal
        for(int i = row+1, j = col-1; i < n_size && j >= 0; i++, j--)
        {
            if(board[i][j] == 1 || board[row][i] == 55)
                return false;
        }

        //right upper diagonal
        for(int i = row -1, j = col + 1; i >= 0 && j < n_size; i--, j++)
        {
            if(board[i][j] == 1 || board[row][i] == 55)
                return false;
        }

        //right down diagonal
        for(int i = row + 1, j = col + 1; i < n_size && j < n_size; i++, j++)
        {
            if(board[i][j] == 1 || board[row][i] == 55)
                return false;
        }
        return true;
    }

    public void insertPoint(int board[][], int row, int col)
    {
        for(int j = row + 1; j < n_size; j++)
        {
            board[j][col] = 55;
        }

        //right upped diagonal
        for(int i = row - 1, j = col +1; i >= 0 && j <n_size; i--, j++)
        {
            board[i][j] = 55;
        }

        //left down diagonal
        for(int i = row + 1, j = col + 1; i < n_size && j < n_size; i++, j++)
        {
            board[i][j] = 55;
        }
    }
    public void replaceValues()
    {
        for(int i=0; i<n_size; i++)
        {
            for(int j=0; j<n_size; j++)
            {
                if(board[i][j] == 55)
                {
                    board[i][j] = 0;
                }
            }
        }
    }

    public ArrayList<Integer> getListOfPossibilities(int col)
    {
        ArrayList<Integer> possibilities_list = new ArrayList<Integer>();
        for(int i=0; i<n_size; i++)
        {
            if(board[i][col] != 1 || board[i][col] != 55)
            {
                possibilities_list.add(i);
            }
        }
        return possibilities_list;
    }

//    public boolean FcPlaceHetmans(int board[][], int col)
//    {
//        if(col == n_size)
//            return true;
//
//        int itera = 0;
//        boolean all_placed = false;
//        for(int i=0; i < n_size; i++)
//        {
//            if(canBePlacedFC(board, i, col))
//            {
//                board[i][col] = 1;
//                insertPoint(board, i, col);
//                all_placed = FcPlaceHetmans(board, col+1);
//            }
//
//            if(all_placed)
//            {
//                replaceValues();
//                break;
//            }
//            else
//            {
//                board[i][col] = 0;
//                replaceValues();
//            }
//            itera++;
//        }
//        iteration_list.add(itera);
//        return all_placed;
//    }

    public boolean FcPlaceHetmans(int board[][], int col)
    {
        if(col == n_size)
            return true;

        int index = -1;
        int itera = 0;
        boolean all_placed = false;
        ArrayList<Integer> poss_list = new ArrayList<>();
        poss_list= getListOfPossibilities(col);

        for(int i = 0; i < poss_list.size(); i++)
        {
            index = poss_list.get(i);
            if(canBePlacedFC(board, index, col))
            {
                board[index][col] = 1;
                insertPoint(board, index, col);
                all_placed = FcPlaceHetmans(board, col+1);
                itera++;
            }

            if(all_placed)
            {
                replaceValues();
                break;
            }
            else
            {
                board[index][col] = 0;
                replaceValues();
//                poss_list.clear();
            }
        }
        poss_list.clear();
        iteration_list.add(itera);
        return all_placed;
    }

//    public boolean FcPlaceHetmans(int board[][], int col)
//    {
//        if(col >= n_size)
//            return true;
//
//        int index = -1;
//        int itera = 0;
//        boolean all_placed = false;
//        ArrayList<Integer> poss_list = new ArrayList<>();
//        poss_list= getListOfPossibilities(col);
//
//        for(int i = 0; i < poss_list.size(); i++)
//        {
//            index = poss_list.get(i);
//            if(canBePlacedFC(board, index, col))
//            {
//                board[index][col] = 1;
//                insertPoint(board, index, col);
//                all_placed = FcPlaceHetmans(board, col+1);
//            }
//
//            if(all_placed)
//            {
//                replaceValues();
//                break;
//            }
//            else
//            {
//                board[index][col] = 0;
//                replaceValues();
////                poss_list.clear();
//            }
//                itera++;
//        }
//        poss_list.clear();
//        iteration_list.add(itera);
//        return all_placed;
//    }

    public void startFc()
    {
        clearBoard();
        iteration_list.clear();
        long start_time = System.nanoTime();
        displayBoard();
        FcPlaceHetmans(board, 0);
        long finish_time = System.nanoTime();
        long difference = finish_time - start_time;
        displayBoard();
        calculateIterations();
        System.out.println("Time: " + difference * Math.pow(10, 6) + " [ms]");
    }
}
