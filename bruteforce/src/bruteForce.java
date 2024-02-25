import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class bruteForce {

    public static void main(String args[]) throws IOException 
    { 
        BufferedWriter out = new BufferedWriter(new FileWriter("game.java"));

        out.append("import java.util.Scanner;\n");
        out.append("public class game {\n");
        out.append("\tpublic static void main(String args[]) {\n");
        out.append("\tScanner scnr = new Scanner(System.in);\n");

        ///char[] positions = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'};
        //for (int i = 0; i < 9; i++) {
        //    out.append("\tchar " + positions[i] + " = ' ';\n");
        //}
        out.append("\tint x = 0;\n");
        out.append("\tint y = 0;\n");

        char[] variables = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        iterate(variables, out, 1, 'X');

        out.append("\t}\n");
        out.append("}\n");

        out.close();
    } 

    public static void iterate(char[] variables, BufferedWriter out, int depth, char turn) throws IOException {

        //protection for my poor $200 acer
        //if (depth > 4) {
        //    return;
        //}

        String tab = "";
        for (int i = 0; i < depth; i++) {
            tab += "\t";
        }

        out.append(tab + "System.out.println(\" " + variables[0] + " | " + variables[1] + " | " + variables[2] + " \");\n");
        out.append(tab + "System.out.println(\"============\");\n");
        out.append(tab + "System.out.println(\" " + variables[3] + " | " + variables[4] + " | " + variables[5] + " \");\n");
        out.append(tab + "System.out.println(\"============\");\n");
        out.append(tab + "System.out.println(\" " + variables[6] + " | " + variables[7] + " | " + variables[8] + " \");\n");

        char winState = checkWiningGame(variables);
        //out.append(tab + "//Win State: " + winState + "\n");

        if (winState != ' ') {
            if (winState != '-') {
                out.append(tab + "System.out.println(\"Player " + winState + " wins!\");");
            }
            else {
                out.append(tab + "System.out.println(\"Tie Game!\");");
            }
            return;
        }

        out.append("\n" + tab + "System.out.println(\"" + turn + "'s Turn! \");\n");

        out.append(tab + "System.out.print(\"Enter X Value: \");\n");
        out.append(tab + "x = scnr.nextInt();\n");
        out.append(tab + "System.out.print(\"Enter Y Value: \");\n");
        out.append(tab + "y = scnr.nextInt();\n");
        out.append(tab + "System.out.println(\"\\n\\n\");\n\n");

        char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'};
        for (int i = 0; i < 9; i++) {
            if (variables[i] == ' ') {
                out.append(tab + "if (x == " + Integer.toString(i % 3) + " && y == " + Integer.toString(i / 3) + ") {" + "\n");
                out.append(tab + "\t// Case where location " + letters[i] + " is selected." + "\n");

                char[] variablesCopy = new char[9]; //Make copy of variable list with item change (there's probrably a better way to make this but its 6am so i'm giving myself a pass)
                for (int i2 = 0; i2 < 9; i2++) {
                    if (i2 == i) {
                        variablesCopy[i2] = turn;
                    }
                    else {
                        variablesCopy[i2] = variables[i2];
                    }
                }
                if (turn == 'X') {
                    iterate(variablesCopy, out, depth + 1, 'O');
                }
                else {
                    iterate(variablesCopy, out, depth + 1, 'X');
                }
                out.append(tab + "}" + "\n");
            }
        }
    }

    public static char checkWiningGame(char[] variables) {
        boolean tie = true;

        for (int i = 0; i < variables.length; i++) {
            if (variables[i] == ' ') {
                tie = false;
            }
        }

        if (tie) {
            return '-';
        }

        for (int i = 0; i < 3; i++) {
            if (variables[i * 3] == variables[i * 3 + 1] && variables[i * 3] == variables[i * 3 + 2]) {
                return variables[i * 3];
            }
            if (variables[i] == variables[1 * 3 + i] && variables[1 * 3 + i] == variables[2 * 3 + i]) {
                return variables[i];
            }
            if (variables[0] == variables[4] && variables[0] == variables[8]) {
                return variables[0];
            }
            if (variables[2] == variables[4] && variables[2] == variables[6]) {
                return variables[2];
            }
        }

        return ' ';
    }
}

