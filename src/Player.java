 public class Player {
     private String name;
     private int playerNumber = 1;

     public Player(String name, int playerNumber) {

         this.name = name;
         this.playerNumber = playerNumber;
     }

     public String getName() {
         return name;
     }

     public int getPlayerNumber() { return playerNumber; }
 }

