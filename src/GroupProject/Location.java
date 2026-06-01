/*
Author: Oliver

This handles the location object.
Each Location is a game tile assigned to the game map array.
*/

package GroupProject;

public class Location {

    private int locationPos;
    private boolean isHomeBase;
    private boolean isSurveySite;
    private boolean hasWeapon;
    private boolean hasWreckage;

    private boolean isSiteSurveyed = false;

    private boolean isPlayerPos = false;
    private boolean isMonsterPos = false;
    private Item item;
    private String name;
    private String description;



    // Constructor
    public Location(boolean argHomeBase, boolean argSurveySite, boolean argHasWeapon, boolean argHasWreckage, String name, String description, Item item, int argLocationPos) {
        isHomeBase = argHomeBase;
        isSurveySite = argSurveySite;
        hasWeapon = argHasWeapon;
        hasWreckage = argHasWreckage;
        locationPos = argLocationPos;
        this.name = name;
        this.description = description;
        this.item = item;
    }

    public int getLocationInt() {
        return locationPos;
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

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Item getItem() {
        return item;
    }

    public void removeItem() {
        item = null;
    }
}
