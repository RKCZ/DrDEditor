package model;

/**
 *
 * @author Roman Kalivoda
 */
public enum Occupation {

    /**
     *
     */
    WARRIOR,
    
    /**
     *
     */
    RANGER,
    
    /**
     *
     */
    ALCHEMIST, 
    
    /**
     *
     */
    SORCERER,
    
    /**
     *
     */
    THIEF;
    
    @Override    
    public String toString() {
        switch(this) {
            case WARRIOR:
                return "Warrior";
            case RANGER:
                return "Ranger";
            case SORCERER:
                return "Sorcerer";
            case ALCHEMIST:
                return "Alchemist";
            case THIEF:
                return "Thief";
        }
        return "Undefined";
    }
}
