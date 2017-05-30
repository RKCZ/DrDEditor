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
        switch (this) {
            case WARRIOR:
                return java.util.ResourceBundle.getBundle("drdeditor/Bundle").getString("WARRIOR");
            case RANGER:
                return java.util.ResourceBundle.getBundle("drdeditor/Bundle").getString("RANGER");
            case SORCERER:
                return java.util.ResourceBundle.getBundle("drdeditor/Bundle").getString("SORCERER");
            case ALCHEMIST:
                return java.util.ResourceBundle.getBundle("drdeditor/Bundle").getString("ALCHEMIST");
            case THIEF:
                return java.util.ResourceBundle.getBundle("drdeditor/Bundle").getString("THIEF");
        }
        return java.util.ResourceBundle.getBundle("drdeditor/Bundle").getString("UNDEFINED");
    }
}
