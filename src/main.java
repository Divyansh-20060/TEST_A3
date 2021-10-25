import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

//Player
class Player{
    String Name;
    int Score;
    int Position;
    Boolean Start_Flag = false;
    public int roll(){
        Scanner sc = new Scanner(System.in);
        int roll;
        System.out.print("-------------------------------------------------------" +"\n");
        System.out.print("Hit enter to roll the dice");
        sc.nextLine();
        Random rnd = new Random();
        roll = rnd.nextInt(2) + 1;
        return roll;
    }
}

//special Floors
abstract class Special_Floor{
    String Name;
    int Position;
    int Points;
    public abstract void effect(Player player);
}

class Snake extends Special_Floor{
    Snake(){
        super();
    }
    int Penalty;
    public void effect(Player player){
        player.Score = player.Score - this.Points;
        player.Position = this.Penalty;
    }
}

class Ladder extends Special_Floor{
    Ladder(){
        super();
    }
    int Reward;
    public void effect(Player player){
        player.Score = player.Score + this.Points;
        player.Position = this.Reward;
    }
}

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> SFP = new ArrayList<>();

        Snake Cobra = new Snake();
        Cobra.Name = "King Cobra Floor";
        Cobra.Points = 4;
        Cobra.Position = 11;
        Cobra.Penalty = 3;
        SFP.add(Cobra.Position);

        Snake RSnake = new Snake();
        RSnake.Name = "Snake Floor";
        RSnake.Points = 2;
        RSnake.Position = 5;
        RSnake.Penalty = 1;
        SFP.add(RSnake.Position);

        Ladder RLadder = new Ladder();
        RLadder.Name = "Ladder Floor";
        RLadder.Points = 2;
        RLadder.Position = 8;
        RLadder.Reward = 12;
        SFP.add(RLadder.Position);

        Ladder Elevator = new Ladder();
        Elevator.Name = "Elevator Floor";
        Elevator.Points = 4;
        Elevator.Position = 2;
        Elevator.Reward = 10;
        SFP.add(Elevator.Position);

        Special_Floor[] SF = {Cobra,RSnake , RLadder, Elevator};

        System.out.print("Enter the player name and hit enter" + "\n");
        String name = sc.next();
        Player player = new Player();
        player.Name = name;

        System.out.print("The game setup is ready" +"\n");

        while (player.Position != 12){
            while (player.Start_Flag != true){
                int roll_val = player.roll();
                if (roll_val == 1){
                    player.Start_Flag = true;
                    player.Position = 0;
                    player.Score = 1;
                    System.out.print("Dice gave 1"+ "\n" +
                            "Player position Floor-0" +"\n"+
                            player.Name + " has reached an Empty Floor" +"\n"+
                            "Total points " + player.Score + "\n");
                }

                else{
                    System.out.print("Game cannot start until you get 1" +"\n");
                }
            }

            if (player.Start_Flag == true){

                int roll_val = player.roll();
                System.out.print("Dice gave " + roll_val +"\n");
                player.Position = player.Position + roll_val;

                int p = SFP.indexOf(player.Position);
                if(p != -1){
                    System.out.print(player.Name +" has reached an " + SF[p].Name +"\n");
                    SF[p].effect(player);
                    System.out.print("Total points " + player.Score +"\n");
                    System.out.print("Player position Floor-" + player.Position +"\n");
                    System.out.print(player.Name +" has reached an empty floor" +"\n");
                    player.Score = player.Score + 1;
                    System.out.print("Total points " + player.Score +"\n");

                }

                else {
                    System.out.print("Player position Floor-" + player.Position +"\n");
                    System.out.print(player.Name +" has reached an empty floor" +"\n");
                    player.Score = player.Score + 1;
                    System.out.print("Total points " + player.Score +"\n");
                }

            }
        }

        while (player.Position != 13){
            int p = player.roll();
            if (p == 2){
                System.out.print("Dice gave 2" +"\n");
                System.out.print("Player cannot move" +"\n");
            }
            else{
                player.Position = 13;
                System.out.print(
                        "Dice gave 1" +"\n"+
                                "Player position Floor-13" +"\n"+
                                player.Name+" has reached an Empty Floor" +"\n"+
                                "Total points " + player.Score +"\n"+
                                "Game over" + "\n"+
                                player.Name + " accumulated "+ player.Score +" points");
            }
        }
    }
}

