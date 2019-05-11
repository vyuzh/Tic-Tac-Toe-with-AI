import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void oneInput (String str)
    {
        int k = 1;

        System.out.println("---------");
        for (int i = 0; i < 3; i++)
        {
            System.out.print("|");
            for (int j = 0; j < 3; j++)
            {
                System.out.print(" " + str.charAt(k));
                k++;
            }
            System.out.println(" |");
        }
        System.out.println("---------");
    }

    public static String checkStatus (String str)
    {
        int countX = 0;
        int countO = 0;
        String x = "XXX";
        String o = "OOO";
        String outSt = "";

        String hOne = "" + str.charAt(1) + str.charAt(2) + str.charAt(3);
        String hTwo = "" + str.charAt(4) + str.charAt(5) + str.charAt(6);
        String hThree = "" + str.charAt(7) + str.charAt(8) + str.charAt(9);
        String vOne = "" + str.charAt(1) + str.charAt(4) + str.charAt(7);
        String vTwo = "" + str.charAt(2) + str.charAt(5) + str.charAt(8);
        String vThree = "" + str.charAt(3) + str.charAt(6) + str.charAt(9);
        String dOne = "" + str.charAt(1) + str.charAt(5) + str.charAt(9);
        String dTwo = "" + str.charAt(7) + str.charAt(5) + str.charAt(3);

        for (int i = 1; i < str.length() - 1; i++)
        {
            if (str.charAt(i) == 'X')
            {
                countX++;
            }
            else if (str.charAt(i) == 'O')
            {
                countO++;
            }
        }
        for (int i = 1; i < str.length() - 1; i++)
        {
            if (x.equals(hOne) || hTwo.equals(x) || hThree.equals(x)
                    || vOne.equals(x) || vTwo.equals(x) || vThree.equals(x)
                    || dOne.equals(x) || dTwo.equals(x))
            {

                outSt = "X wins";
                break;
            }
            else if (hOne.equals(o) || hTwo.equals(o) || hThree.equals(o)
                    || vOne.equals(o) || vTwo.equals(o) || vThree.equals(o)
                    || dOne.equals(o) || dTwo.equals(o))
            {
                outSt = "O wins";
                break;
            }

            else if (str.charAt(i) == ' ' && i == str.length() - 2)
            {
                outSt = "Game not finished";
                break;
            }
            else if (countO + countX == 9)
            {
                outSt = "Draw";
                break;
            }
        }
        return outSt;
    }

    public static String playerStep (String stPlayer, char ticTac) {
        Scanner scan = new Scanner(System.in);
        int x;
        int y;

        while(true) {
            try {
                x = scan.nextInt();
                y = scan.nextInt();
                if (x <= 3 && x >= 1 && y <= 3 && y >= 1)
                {
                    xyInput(x, y, stPlayer, ticTac);
                    if (stPlayer.equals(xyInput(x, y, stPlayer, ticTac)))
                    {
                        System.out.println("This cell is occupied! Choose another one!");
                        System.out.print("Enter the coordinates: ");
                        continue;
                    }
                    else
                    {
                        break;
                    }
                }
                else
                {
                    System.out.println("Coordinates should be from 1 to 3!");
                    scan.nextLine();
                    System.out.print("Enter the coordinates: ");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
                scan.nextLine();
                System.out.print("Enter the coordinates: ");
                continue;
            }
        }

        return xyInput(x, y, stPlayer, ticTac);
    }

    public static String xyInput(int x, int y, String stIn, char ticTac) {

        char[] string = stIn.toCharArray();

        if (x == 1 && y == 1 && string[7] == ' ')
        {
            string[7] = ticTac;
        }
        else if (x == 2 && y == 1 && string[8] == ' ')
        {
            string[8] = ticTac;
        }
        else if (x == 3 && y == 1 && string[9] == ' ')
        {
            string[9] = ticTac;
        }
        else if (x == 1 && y == 2 && string[4] == ' ')
        {
            string[4] = ticTac;
        }
        else if (x == 2 && y == 2 && string[5] == ' ')
        {
            string[5] = ticTac;
        }
        else if (x == 3 && y == 2 && string[6] == ' ')
        {
            string[6] = ticTac;
        }
        else if (x == 1 && y == 3 && string[1] == ' ')
        {
            string[1] = ticTac;
        }
        else if (x == 2 && y == 3 && string[2] == ' ')
        {
            string[2] = ticTac;
        }
        else if (x == 3 && y == 3 && string[3] == ' ')
        {
            string[3] = ticTac;
        }

        stIn = new String(string);
        return stIn;
    }

    public static String easyDif (String firstSt, char ticTac) {

        int[] spaces = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
        int step;
        char[] outStr = firstSt.toCharArray();

        Random easyRand = new Random();
        step = easyRand.nextInt(10);
        System.out.println("Making move level \"easy\"");

        for (int i = 1, j = 0; i < outStr.length - 1; i++) {
            if (outStr[i] == ' ') {
                spaces[j] = i;
                j++;
            }
        }
        for (int i = 0; i < 9; i++)
        {
            if (spaces[i] == step) {
                outStr[spaces[i]] = ticTac;
                break;
            }
            if (i == 8) {
                step = easyRand.nextInt(10);
                i = 0;
            }
        }
        return new String(outStr);
    }

    public static void userVsUser (String usUs, char x, char o) {
        while (true) {
            System.out.print("Enter the coordinates: ");
            usUs = playerStep(usUs, x);
            oneInput(usUs);
            if (checkStatus(usUs).equals("X wins") || checkStatus(usUs).equals("O wins") || checkStatus(usUs).equals("Draw")) {
                System.out.println(checkStatus(usUs));
                break;
            }
            System.out.print("Enter the coordinates: ");
            usUs = playerStep(usUs, o);
            oneInput(usUs);
            if (checkStatus(usUs).equals("X wins") || checkStatus(usUs).equals("O wins") || checkStatus(usUs).equals("Draw")) {
                System.out.println(checkStatus(usUs));
                break;
            }
        }
    }

    public static void userVsBot (String usBot, char x, char o) {
        while (true) {
            System.out.print("Enter the coordinates: ");
            usBot = playerStep(usBot, x);
            oneInput(usBot);
            if (checkStatus(usBot).equals("X wins") || checkStatus(usBot).equals("O wins") || checkStatus(usBot).equals("Draw")) {
                System.out.println(checkStatus(usBot));
                break;
            }
            usBot = easyDif(usBot, o);
            oneInput(usBot);
            if (checkStatus(usBot).equals("X wins") || checkStatus(usBot).equals("O wins") || checkStatus(usBot).equals("Draw")) {
                System.out.println(checkStatus(usBot));
                break;
            }

        }
    }

    public static void botVsUser (String usBot, char x, char o) {
        while (true) {
            usBot = easyDif(usBot, x);
            oneInput(usBot);
            if (checkStatus(usBot).equals("X wins") || checkStatus(usBot).equals("O wins") || checkStatus(usBot).equals("Draw")) {
                System.out.println(checkStatus(usBot));
                break;
            }
            System.out.print("Enter the coordinates: ");
            usBot = playerStep(usBot, o);
            oneInput(usBot);
            if (checkStatus(usBot).equals("X wins") || checkStatus(usBot).equals("O wins") || checkStatus(usBot).equals("Draw")) {
                System.out.println(checkStatus(usBot));
                break;
            }

        }
    }

    public static void botVsBot (String botBot, char x, char o) {
        while (true) {
            botBot = easyDif(botBot, x);
            oneInput(botBot);
            if (checkStatus(botBot).equals("X wins") || checkStatus(botBot).equals("O wins") || checkStatus(botBot).equals("Draw")) {
                System.out.println(checkStatus(botBot));
                break;
            }
            botBot = easyDif(botBot, o);
            oneInput(botBot);
            if (checkStatus(botBot).equals("X wins") || checkStatus(botBot).equals("O wins") || checkStatus(botBot).equals("Draw")) {
                System.out.println(checkStatus(botBot));
                break;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String st = "\"         \"";
        char x = 'X';
        char o = 'O';
        String command;
        String userUser = "start user user";
        String userEasy = "start user easy";
        String easyUser = "start easy user";
        String easyEasy = "start easy easy";

        while(true) {
            System.out.print("Input command: ");
            command = scanner.nextLine();

            if (command.equals("exit")) {
                break;
            }
            else if (command.equals(userUser)) {
                oneInput(st);
                userVsUser(st, x, o);
                System.out.println();
            }
            else if (command.equals(easyEasy)) {
                oneInput(st);
                botVsBot(st, x, o);
                System.out.println();
            }
            else if (command.equals(userEasy)) {
                oneInput(st);
                userVsBot(st, x, o);
                System.out.println();
            }
            else if (command.equals(easyUser)) {
                oneInput(st);
                botVsUser(st, x, o);
                System.out.println();
            }
            else {
                System.out.println("Bad parameters!");
            }
        }
    }
}
