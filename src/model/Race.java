package model;

import java.util.ResourceBundle;

/**
 *
 * @author Roman Kalivoda
 */
public enum Race {

    /**
     *
     */
    HUMAN,
    /**
     *
     */
    ELF,
    /**
     *
     */
    DWARF,
    /**
     *
     */
    KUDUK,
    /**
     *
     */
    HOBBIT,
    /**
     *
     */
    BARBAR,
    /**
     *
     */
    KROLL;

    @Override
    public String toString() {
        switch (this) {
            case HUMAN:
                return ResourceBundle.getBundle("drdeditor/Bundle").getString("HUMAN");
            case ELF:
                return ResourceBundle.getBundle("drdeditor/Bundle").getString("ELF");
            case DWARF:
                return ResourceBundle.getBundle("drdeditor/Bundle").getString("DWARF");
            case KUDUK:
                return ResourceBundle.getBundle("drdeditor/Bundle").getString("KUDUK");
            case HOBBIT:
                return ResourceBundle.getBundle("drdeditor/Bundle").getString("HOBBIT");
            case BARBAR:
                return ResourceBundle.getBundle("drdeditor/Bundle").getString("BARBAR");
            case KROLL:
                return ResourceBundle.getBundle("drdeditor/Bundle").getString("KROLL");
        }
        return ResourceBundle.getBundle("drdeditor/Bundle").getString("UNDEFINED");
    }
}
