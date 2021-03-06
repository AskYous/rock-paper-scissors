package RockPaperScissors;

import java.util.Comparator;
import java.util.Scanner;

/**
 *Calls the RPS methods to display information
 *Calls the compare method from the weapon class.
 *Updates the fields of the Information class
 */
public class GameControl {

    public static void main(String[] args) {
        Information info = new Information();
        RPSDisplayer displayer = new RPSDisplayer();
        
        int rounds = Integer.parseInt(args[0]);

        CPUThrower cpu = new CPUThrower();
        PlayerThrower user = new PlayerThrower();
        String input;
        Comparator<Weapon> comparator = new WeaponComparator();
        info.setRounds(rounds);

        while (!info.matchOver()) {
            Scanner scan = new Scanner(System.in);
            displayer.printRoundMessage(info.getRound());

            input = scan.nextLine();

            if (input.equals("h")) {
                displayer.printHelpInfo();
            } else if (input.equals("e")) {
                info.end();
                break;
            } else if (input.equals("m")) {
                displayer.printMatchInfo(info.getMatchInfo());
            } else if (input.equals("r") || input.equals("p") || input.equals("s")) {
                user.generateWeapon(input);
                if (info.getRound() < 3) {
                    cpu.generateWeapon(1, info.getRound());
                } else {
                    cpu.generateWeapon(2, info.getRound());
                }

                int result = comparator.compare(user.getWeapon(), cpu.getWeapon());
                switch (result) {
                    //user wins
                    case (1):
                        displayer.printWin(user.getWeapon(), cpu.getWeapon());
                        break;
                    //tie
                    case (0):
                        displayer.printTie(user.getWeapon());
                        break;
                    //user loss
                    case (-1):
                        displayer.printLoss(user.getWeapon(), cpu.getWeapon());
                        break;
                }
                info.updateScores(result);

                info.updateMatchInfo(user.getWeapon(), cpu.getWeapon());
                cpu.appendWeapon(user.getWeapon());
            } else {
                displayer.printInvalid();
            }

        }
        info.updateMatchInfo();
        displayer.printMatchInfo(info.getMatchInfo());
    }
}