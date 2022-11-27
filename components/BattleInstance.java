// An instance of battle. Takes in a player and list of enemies, handles combat, pays out rewards.

package src.components;

import java.util.ArrayList;
import java.util.Scanner;

import src.ConsoleHelper;
import src.actors.Actor;
import src.actors.Player;

public class BattleInstance {
    private Player player;
    private ArrayList<Actor> enemies;
    private int turn;
    private int expPrizePool;

    // Constructor
    public BattleInstance(Player player, ArrayList<Actor> enemies) {
        this.player = player;
        this.enemies = enemies;
        this.turn = 0;
        this.expPrizePool = 0;

        for (int i = 0; i < enemies.size(); i++) {
            this.expPrizePool += enemies.get(i).getExpValue();
        }
    }

    // General Methods
    // Returns true if you win, false if you lose
    public boolean battle() {
        while(player.getHealth() > 0 && !enemies.isEmpty())  {
            checkTurn();
        }

        if (player.getHealth() > 0) {
            System.out.println("You emerge victorious!");
            player.gainExperience(expPrizePool);
            ConsoleHelper.enterToContinue();
            return true;

        } else {
            System.out.println("You have been defeated....");
            return false;
        }

    }

    private void playerSelectAction() {
        // Find out what the player would like to do
        String query = "It is your turn.\n";
        String[] possibleAnswers = {"Attack", "View Player Information", "Examine Enemies", "Equip New Weapon"};
        String playerInput = ConsoleHelper.queryPlayer(query, possibleAnswers);

        ConsoleHelper.clear();

        switch (playerInput) {
            case "1":
                attack();
                break;

            case "2":
                viewPlayerInformation();
                break;

            case "3":
                examineEnemies();
                break;

            case "4":
                equipNewWeapon();
                break;
            
            default:
                ConsoleHelper.invalidInputMessage();
                break;
        }
    }

    private void attack() {
        Actor target = chooseTarget();
        ConsoleHelper.clear();
        player.attack(target);
        checkEnemyHealth(target);
        advanceTurn();
    }

    private void viewPlayerInformation() {
        ConsoleHelper.displayInfoScreen( new String[]{player.toString()} );
    }

    private void examineEnemies() {
        Actor targetActor = chooseTarget();
        ConsoleHelper.clear();
        System.out.println(targetActor.toString());
        ConsoleHelper.enterToContinue();
    }

    private void equipNewWeapon() {
        player.removeWeapon();
        player.equipWeapon();
    }

    public void checkTurn() {
        if (turn == 0) {
            playerSelectAction();
        } else {
            // Enemies do things
            System.out.println("\nIt is the " + enemies.get(turn-1).getName() + "\'s turn.");
            enemies.get(turn-1).attack(player);
            advanceTurn();
        }
    }

    private void advanceTurn() {
        if (turn < enemies.size()) { turn++; }
        else { 
            turn = 0;
            ConsoleHelper.enterToContinue();
        }
    }

    private Actor chooseTarget() {
        @SuppressWarnings("resource") //Can't close playerInput because it will kill System.in program-wide
        Scanner playerInput = new Scanner(System.in);
        int rawInput;

        for (int i = 0; i < enemies.size(); i++) {
            System.out.println((i+1) + ". " + enemies.get(i).getName());
        }

        System.out.print("\nChoose a target: ");
        rawInput = playerInput.nextInt();

        return enemies.get(rawInput-1);
        
    }

    private void checkEnemyHealth(Actor enemyActor) {
        if (enemyActor.getHealth() <= 0) {
            System.out.println(enemyActor.getName() + " has perished!");
            int enemyActorIndex = enemies.indexOf(enemyActor);
            enemies.remove(enemyActorIndex);
        }
    }
}
