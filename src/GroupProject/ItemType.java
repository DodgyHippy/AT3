package GroupProject;

/*
Author: Sebastion

This enum stores the two item categories used by the inventory system.
Primary items are used for scoring, while secondary items are used by the player.

Bug: Arose when the player could softlock themselves by discarding a valuable prospect,
making it so that they would never score enough to end the game.
Fix: Ensure that all scoring prospects were classified differently from expendables to prevent
players from softlocking themselves.
*/

public enum ItemType {
    PRIMARY, SECONDARY
}
