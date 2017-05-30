package model;

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
                return java.util.ResourceBundle.getBundle("drdeditor/Bundle").getString("HUMAN");
            case ELF:
                return java.util.ResourceBundle.getBundle("drdeditor/Bundle").getString("ELF");
            case DWARF:
                return java.util.ResourceBundle.getBundle("drdeditor/Bundle").getString("DWARF");
            case KUDUK:
                return java.util.ResourceBundle.getBundle("drdeditor/Bundle").getString("KUDUK");
            case HOBBIT:
                return java.util.ResourceBundle.getBundle("drdeditor/Bundle").getString("HOBBIT");
            case BARBAR:
                return java.util.ResourceBundle.getBundle("drdeditor/Bundle").getString("BARBAR");
            case KROLL:
                return java.util.ResourceBundle.getBundle("drdeditor/Bundle").getString("KROLL");
        }
        return java.util.ResourceBundle.getBundle("drdeditor/Bundle").getString("UNDEFINED");
    }
}
