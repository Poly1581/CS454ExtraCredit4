public class LeetCode {
    public static class HashTableProblems {
        public static class ValidSudoku {
            // Difficulty: medium
            // Solution to https://leetcode.com/problems/valid-sudoku

            // Since we know how many rows, columns, boxes, and numbers there are,
            // we can use arrays with perfect hashing instead of a hash table.
            private static class Metrics {
                // Keep track of the rows in which this number has occurred
                boolean[] rows = new boolean[9];

                // Keep track of the columns in which this number has occurred
                boolean[] cols = new boolean[9];

                // Keep track of the boxes in which this number has occurred
                boolean[] boxes = new boolean[9];

                // Get the box that (row, col) is in
                private static int box(int row, int col) {
                    return 3 * (col / 3) + (row / 3);
                }

                // Update rows, cols, and boxes
                private void mark(int row, int col) {
                    rows[row] = true;
                    cols[col] = true;
                    boxes[box(row, col)] = true;
                }

                // Check if row, col, or box already has an occurrence
                private boolean isInvalid(int row, int col) {
                    return rows[row] || cols[col] || boxes[box(row, col)];
                }
            }

            public static boolean isValidSudoku(char[][] board) {
                // Set up metrics for each number
                Metrics[] metrics = new Metrics[9];

                // Set up access variables to avoid creating repeatedly in the loop
                int valueInt;
                char valueChar;
                Metrics numberMetrics;


                for(int row = 0; row < board.length; row++) {
                    for(int col = 0; col < board[row].length; col++) {
                        // Extract cell value
                        valueChar = board[row][col];

                        // If cell doesn't have a number, ignore it
                        if(valueChar == '.') {
                            continue;
                        }

                        // Convert char to int
                        valueInt = valueChar - '1';

                        // Get corresponding metrics
                        numberMetrics = metrics[valueInt];

                        // Initialize metrics if needed
                        if(numberMetrics == null) {
                            numberMetrics = new Metrics();
                            metrics[valueInt] = numberMetrics;
                        }

                        // Check if invalid
                        if(numberMetrics.isInvalid(row, col)) {
                            return false;
                        }

                        // Update row, col, and box
                        numberMetrics.mark(row, col);
                    }
                }
                return true;
            }

        }

        public static class SetMatrixZeros {
            // Difficulty: medium
            // Solution to https://leetcode.com/problems/set-matrix-zeroes

            public static void setZeroes(int[][] matrix) {
                // Get dimensions
                int numRows = matrix.length;
                int numCols = matrix[0].length;

                // "Hash map" (array)
                boolean[] zeroRows = new boolean[numRows];
                boolean[] zeroCols = new boolean[numCols];

                // Loop and add zero rows and zero cols
                for(int row = 0; row < numRows; row++) {
                    for(int col = 0; col < numCols; col++) {
                        if(matrix[row][col] == 0) {
                            zeroRows[row] = true;
                            zeroCols[col] = true;
                        }
                    }
                }

                // Loop and set zero
                for(int row = 0; row < numRows; row++) {
                    for(int col = 0; col < numCols; col++) {
                        if(zeroRows[row] || zeroCols[col]) {
                            matrix[row][col] = 0;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        char[][] sudoku = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        System.out.println("+=====+=====+=====+");
        for(int row = 0; row < 9; row++) {
            System.out.print("‖");
            for(int col = 0; col < 9; col++) {
                char c = sudoku[row][col];
                if(c == '.') {
                    System.out.print(" ");
                } else {
                    System.out.print(c);
                }
                if(col % 3 == 2) {
                    System.out.print("‖");
                } else {
                    System.out.print("|");
                }
            }
            System.out.println();
            if(row % 3 == 2) {
                    System.out.println("+=====+=====+=====+");
            } else {
                System.out.println("+-----+-----+-----+");
            }
        }
        if(HashTableProblems.ValidSudoku.isValidSudoku(sudoku)) {
            System.out.println("Is a valid sudoku.");
        } else {
            System.out.println("Is not a valid sudoku.");
        }

        System.out.println();
        System.out.println();

        int[][] matrix = {
                {0, 0, 0, 5},
                {4, 2, 1, 4},
                {0, 1, 1, 4},
                {1, 2, 1, 3},
                {0, 0, 1, 1}
        };

        System.out.println("Initial matrix:");
        for(int row = 0; row < matrix.length; row++) {
            for(int col = 0; col < matrix[0].length; col++) {
                System.out.print(matrix[row][col]);
                if(col != matrix[0].length - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        HashTableProblems.SetMatrixZeros.setZeroes(matrix);

        System.out.println("After setting zeros:");
        for(int row = 0; row < matrix.length; row++) {
            for(int col = 0; col < matrix[0].length; col++) {
                System.out.print(matrix[row][col]);
                if(col != matrix[0].length - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }


    }
}