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
                return "Human";
            case ELF:
                return "Elf";
            case DWARF:
                return "Dwarf";
            case KUDUK:
                return "Kuduk";
            case HOBBIT:
                return "Hobbit";
            case BARBAR:
                return "Barbar";
            case KROLL:
                return "Kroll";
        }
        return "Undefined";
    }
}
