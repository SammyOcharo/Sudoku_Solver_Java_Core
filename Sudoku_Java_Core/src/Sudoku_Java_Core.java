public class Sudoku_Java_Core {

    private static final int sudoku_size = 9;

    public static void main(String[] args) {

        int[][] sudoku = {
                {7,0,2,0,5,0,6,0,0},
                {0,0,0,0,0,3,0,0,0},
                {1,0,0,0,0,9,5,0,0},
                {8,0,0,0,0,0,0,9,0},
                {0,4,3,0,0,0,7,5,0},
                {0,9,0,0,0,0,0,0,8},
                {0,0,9,7,0,0,0,0,5},
                {0,0,0,2,0,0,0,0,0},
                {0,0,7,0,4,0,2,0,3}
        };

        if(solvingBoard(sudoku)){
            System.out.println("Sudoku solved successfully");
        } else {
            System.out.println("Board is unsolvable");
        }

        SolvedSudoku(sudoku);
    }

    private static void SolvedSudoku(int[][] sudoku) {

        for (int row = 0; row < sudoku_size ; row++) {
            if(row % 3 ==0 && row !=0){
                System.out.println("-----------");
            }
            for (int column = 0; column < sudoku_size ; column++) {
                if (column % 3 ==0 && column !=0){
                    System.out.print("|");
                }
                System.out.print(sudoku[row][column]);
            }
            System.out.println();
        }
    }

    private static boolean numberNotInRow(int[][] block, int number, int Row){

        for (int i = 0; i < sudoku_size  ; i++) {
            if(block[Row][i]==number){

                return true;
            }
        }
        return false;
    }

    private static boolean numberNotInColumn(int[][] block, int number, int Column){

        for (int i = 0; i < sudoku_size  ; i++) {
            if(block[i][Column]==number){

                return true;
            }
        }
        return false;
    }

    private static boolean numberNotInSmallBox(int[][] block, int number, int Row, int Column){

        int localSmallBoxRow = Row - Row % 3;
        int localSmallBoxColumn = Column - Column % 3;
        for (int i = localSmallBoxRow; i < localSmallBoxRow+3; i++) {
            for (int j = localSmallBoxColumn; j < localSmallBoxColumn+3; j++) {
                if (block[i][j]==number){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean validNumberPlacement(int[][] block, int Row, int Column, int number){

        return !numberNotInRow(block, number, Row) &&
                !numberNotInColumn(block, number, Column)&&
                !numberNotInSmallBox(block,number,Row,Column);
    }

    private static boolean solvingBoard (int[][] block){

        for (int row = 0; row < sudoku_size ; row++) {
            for (int column = 0; column < sudoku_size ; column++) {
                if (block[row][column]==0){
                    for (int NumberToBeTried = 1; NumberToBeTried <= sudoku_size ; NumberToBeTried++) {
                       if (validNumberPlacement(block,row,column,NumberToBeTried)){
                           block[row][column] = NumberToBeTried;

                           if (solvingBoard(block)){
                               return true;
                           }else {

                               block[row][column] = 0;
                           }
                       }
                    }

                    return false;
                }
            }
        }
        return true;
    }
}
