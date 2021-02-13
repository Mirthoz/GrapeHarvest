import java.util.Random;
import java.util.Scanner;

public class Main {

    static boolean thread = true;
    static char move;
    static int playerIndex = 0;
    static String[] topScores = {"", "", "", "", ""};

    public static void main(String[] args) {

        printFrame(createFrame());

    }

    public static String[][] createFrame() {
        String[][] frame = new String[11][20];

        for (int i = 0; i < frame.length; i++) {
            for (int j = 0; j < frame[i].length; j++) {
                frame[i][j] = "   ";
            }
        }
        return frame;
    }

    public static void printFrame(String[][] frame) {

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        int basketPositionX = 0;
        int basketPositionY = frame.length - 1;
        int score = 0;
        int time = 10;
        char replay;

        int grapePositionY1 = 0;
        int grapePositionX1 = random.nextInt(20);;
        int grapePositionY2 = 0;
        int grapePositionX2 = random.nextInt(20);;
        int grapePositionY3 = 0;
        int grapePositionX3 = random.nextInt(20);;
        int grapePositionY4 = 0;
        int grapePositionX4 = random.nextInt(20);;
        int grapePositionY5 = 0;
        int grapePositionX5 = random.nextInt(20);;

        new Thread(new ScanListener()).start();

        while (time != 0) {

            if (grapePositionX1 == basketPositionX && grapePositionY1 == basketPositionY){    //TODO Score equals
                score++;
            }

            if (grapePositionX2 == basketPositionX && grapePositionY2 == basketPositionY){    //TODO Score equals
                score++;
            }

            if (grapePositionX3 == basketPositionX && grapePositionY3 == basketPositionY){    //TODO Score equals
                score++;
            }

            if (grapePositionX4 == basketPositionX && grapePositionY4 == basketPositionY){    //TODO Score equals
                score++;
            }

            if (grapePositionX5 == basketPositionX && grapePositionY5 == basketPositionY){    //TODO Score equals
                score++;
            }

            frame[grapePositionY1][grapePositionX1] = " o ";
            frame[grapePositionY2][grapePositionX2] = " o ";
            frame[grapePositionY3][grapePositionX3] = " o ";
            frame[grapePositionY4][grapePositionX4] = " o ";
            frame[grapePositionY5][grapePositionX5] = " o ";

            if (grapePositionY1 != 0) {
                frame[grapePositionY1 - 1][grapePositionX1] = "   ";
            }

            if (grapePositionY2 != 0) {
                frame[grapePositionY2 - 2][grapePositionX2] = "   ";
            }

            if (grapePositionY3 != 0) {
                frame[grapePositionY3 - 1][grapePositionX3] = "   ";
            }

            if (grapePositionY4 != 0) {
                frame[grapePositionY4 - 2][grapePositionX4] = "   ";
            }

            if (grapePositionY5 != 0) {
                frame[grapePositionY5 - 1][grapePositionX5] = "   ";
            }

            if (grapePositionY1 < frame.length - 1) {  //grapeMove
                grapePositionY1++;
            } else {
                grapePositionY1 = 0;
                grapePositionX1 = random.nextInt(20);
            }

            if (grapePositionY2 < frame.length - 1) {  //grapeMove
                grapePositionY2+= 2;
            } else {
                grapePositionY2 = 0;
                grapePositionX2 = random.nextInt(20);
            }

            if (grapePositionY3 < frame.length - 1) {  //grapeMove
                grapePositionY3++;
            } else {
                grapePositionY3 = 0;
                grapePositionX3 = random.nextInt(20);
            }

            if (grapePositionY4 < frame.length - 1) {  //grapeMove
                grapePositionY4+= 2;
            } else {
                grapePositionY4 = 0;
                grapePositionX4 = random.nextInt(20);
            }

            if (grapePositionY5 < frame.length - 1) {  //grapeMove
                grapePositionY5++;
            } else {
                grapePositionY5 = 0;
                grapePositionX5 = random.nextInt(20);
            }

            for (int i = 0; i < frame[0].length; i++) { //Clear basket roll
                frame[frame.length - 1][i] = "   ";
            }

            frame[frame.length - 1][basketPositionX] = "\\_/";

            if (basketPositionX != 0) {
                frame[frame.length - 1][basketPositionX - 1] = "   ";
            }

            if (basketPositionX < frame[0].length - 1) {
                frame[frame.length - 1][basketPositionX + 1] = "   ";
            }

            frame[0][0] = "Skore: " + score + "                                            " + "Time: " + time + "\n   ";

            printGameFrame(frame);
            time--;

            if (move == 'a' && basketPositionX > 0) {
                basketPositionX--;
            } else if (move == 'd' && basketPositionX < frame[0].length - 1) {
                basketPositionX++;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cleanFrame();
        }

        thread = false;

        System.out.println("Print your name: ");
        printScore(getUserNameFromUser(), score, playerIndex, topScores);
        playerIndex++;

        if(playerIndex >= 5){
            playerIndex = 0;
        }

        System.out.println("Do you wont to repeat game y/n?");
        replay = scanner.next().charAt(0);

        if(replay == 'y'){
            thread = true;
            printFrame(createFrame());
        }

    }

    public static void printGameFrame(String[][] frame) {
        for (int i = 0; i < frame.length; i++) {    //Print frame
            System.out.println();
            for (int j = 0; j < frame[0].length; j++) {
                System.out.print(frame[i][j]);
            }
        }
    }

    public static void printScore(String name, int score, int playerIndex, String[] topScores){

        topScores[playerIndex] = name + " score: " + score;

        for (int i = 0; i < 5; i++) {
            System.out.println(topScores[i]);
        }
    }

    public static void cleanFrame(){
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
    }

    public static String getUserNameFromUser(){
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        if(name.isEmpty()){
            throw new IndexOutOfBoundsException("Line is empty!!!");
        }
        return name;
    }

    public static class ScanListener implements Runnable {

        @Override
        public void run() {
            while (thread) {
                move = new Scanner(System.in).next().charAt(0);
            }
        }
    }
}
