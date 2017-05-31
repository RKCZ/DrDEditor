package model;

import java.util.ResourceBundle;

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
                return ResourceBundle.getBundle("drdeditor/Bundle").getString("WARRIOR");
            case RANGER:
                return ResourceBundle.getBundle("drdeditor/Bundle").getString("RANGER");
            case SORCERER:
                return ResourceBundle.getBundle("drdeditor/Bundle").getString("SORCERER");
            case ALCHEMIST:
                return ResourceBundle.getBundle("drdeditor/Bundle").getString("ALCHEMIST");
            case THIEF:
                return ResourceBundle.getBundle("drdeditor/Bundle").getString("THIEF");
        }
        return ResourceBundle.getBundle("drdeditor/Bundle").getString("UNDEFINED");
    }
}
