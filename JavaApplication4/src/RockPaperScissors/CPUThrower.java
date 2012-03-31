package RockPaperScissors;

import java.util.Random;

/**
 * Simulates the computer as a player of rock paper scissors game.
 */
public class CPUThrower extends Thrower {

    private CalculateWeapon cw;

    /**
     * Generates computer's next weapon.
     *
     * @param ALG_TYPE Type of algorithm to generate cpu's next weapon. Used
     * later.
     * @return Cpu's next weapon.
     */
    public Weapon generateWeapon(int ALG_TYPE) {
        cw = CalculateWeapon.makeCalc(ALG_TYPE);
        return cw.calculateWeapon();
    }
}