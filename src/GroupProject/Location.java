/*
Author: Oliver

This handles the location object.
Each Location is a game tile assigned to the game map array.
*/

package GroupProject;

import java.util.Random;
public class Location {
    Random randNum = new Random();

    private boolean isHomeBase;
    private boolean isSurveySite;
    private boolean hasWeapon;
    private boolean hasWreckage;

    private boolean isSiteSurveyed = false;

    private boolean isPlayerPos = false;
    private boolean isMonsterPos = false;



    // Constructor
    public Location(boolean argHomeBase,boolean argSurveySite,boolean argHasWeapon, boolean argHasWreckage) {
        isHomeBase = argHomeBase;
        isSurveySite = argSurveySite;
        hasWeapon = argHasWeapon;
        hasWreckage = argHasWreckage;
    }

    public boolean getHomeBase() {
        return isHomeBase;
    }

    public boolean getSurveySite() {
        return isSurveySite;
    }
    public boolean isSiteSurveyed() {
        return isSiteSurveyed;
    }
    public void setSiteSurveyed(boolean argSiteSurveyed) {
        this.isSiteSurveyed = argSiteSurveyed;
    }

    public boolean getHasWeapon() {
        return hasWeapon;
    }
    public void setHasWeapon(boolean argHasWeapon) {
        this.hasWeapon = argHasWeapon;
    }

    public boolean getHasWreck() {
        return hasWreckage;
    }
    public void setWreckExplored(boolean argWreckExplored) {
        this.hasWreckage = argWreckExplored;
    }

    public boolean isPlayerPos() {
        return isPlayerPos;
    }
    public void setPlayerPos(boolean argPlayerPos) {
        this.isPlayerPos = argPlayerPos;
    }

    public boolean isMonsterPos() {
        return isMonsterPos;
    }
    public void setMonsterPos(boolean argMonsterPos) {
        this.isMonsterPos = argMonsterPos;
    }
}
