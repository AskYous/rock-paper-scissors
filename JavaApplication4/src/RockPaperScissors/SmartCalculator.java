package RockPaperScissors;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Yousef_2
 */
public class SmartCalculator extends CalculateWeapon {

    private ArrayList<Weapon> weapons = new ArrayList<Weapon>();
    private boolean testing = false;
    private int round = 0;

    public SmartCalculator(int round){
        this.round = round;
    }
    
    @Override
    public Weapon calculateWeapon() {
        Weapon calculatedWeapon;
        weapons = CPUThrower.getWeapons();
        if (round <= 4) {
            calculatedWeapon = NCalculateWeapon(2);
        } else if (round <= 6) {
            calculatedWeapon = NCalculateWeapon(3);
        } else if (round <= 8) {
            calculatedWeapon = NCalculateWeapon(4);
        } else{
            calculatedWeapon = NCalculateWeapon(5);
        }
        return calculatedWeapon;
        
    }

    /**
     * @param occurrences array of occurrences of combinations .
     * @return position with biggest number
     */
    private Weapon count(int[] occurrences) {
        int result = 0;
        for (int i = 1; i < occurrences.length; i++) {
            if (occurrences[i] > occurrences[result]) {
                result = i;
            }
        }
        if (result == 0) {
            return Weapon.PAPER;
        } else if (result == 1) {
            return Weapon.SCISSORS;
        } else {
            return Weapon.ROCK;
        }

    }

    /**
     * 0 = R returns P 1 = P returns S 2 = S returns R Ignores first user's
     * weapon Uses last cpu weapon to generate the smart throw
     *
     * @return
     */
    private Weapon NCalculateWeapon(int N) {
        //int N;
        int[] occurrences = new int[3];

        //String of weapons, instead of List. For example: weaponString = "RPSSPRRPS";
        String weaponsString = "";

        //Adds each weapon to the weaponString Variable
        for (int i = 0; i < weapons.size(); i++) {
            weaponsString += weapons.get(i).toString();
        }

        if (testing) {
            System.out.println(weaponsString);
        }

        int tail = weaponsString.length() - 1;

        //String of only the last N weapons. 
        String lastWeaponsThrown = weaponsString.substring(tail - N, tail);

        //a list of the strings (or weapons) between each 'lastWeaponsThrown' sequence. Kinda hard to explain.
        //It pretty much removes every sequence of the 'lastWeaponsThrown' and puts all the strings before and after the removed
        //sequence, into the array. Although all we care about is just the next letter (or weapon).
        String[] temp = weaponsString.split(lastWeaponsThrown);


        //Sees the next letter in the list. Adds to the occurences[] list just like Carlos's method.
        for (int i = 1; i < temp.length; i++) {
            //If next weapon was thrown by computer, we ignore it
            if (!(weaponsString.indexOf(temp[i]) % 2 == 0)) {
                continue;
            }
            if (temp[i].startsWith("R")) {
                occurrences[0]++;
            } else if (temp[i].startsWith("P")) {
                occurrences[1]++;
            } else if (temp[i].startsWith("S")) {
                occurrences[2]++;
            } else {
                occurrences[new Random().nextInt(3)]++;
            }
        }

        //determines next weapon to throw.
        Weapon nextWeapon = count(occurrences);

        //true if no pattern was found.
        if (nextWeapon == null) {
            if (N == 2) {
                nextWeapon = new RandomCalculator().calculateWeapon();
            } else if (N > 2) {
                nextWeapon = NCalculateWeapon(N++);
            }
        }
        return nextWeapon;
    }
}
