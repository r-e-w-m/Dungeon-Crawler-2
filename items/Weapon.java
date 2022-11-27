// Weapon. Currently quite generic. May create subclasses for specific weapon types. (Probably a good idea)

package src.items;

public class Weapon extends Item {
    private int damage;
    private String slot;
    
    // Constructor
    public Weapon(String name, String description, int value, int damage, String slot) {
        super(name, description, value);
        this.damage = damage;
        this.slot = slot;
    }

    // Gets
    public int getDamage() {
        return damage;
    }
    public String getSlot() {
        return slot;
    }

    // toString
    @Override
    public String toString() {
        return getName() + ": Damage - " + damage + ", Value - " + getValue() + ", Slot - " + slot;
    }
}
