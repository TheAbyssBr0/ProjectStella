package git.stella.model.characters;

/**
 * Used to return character at calculated index
 */
public class CharacterContainer {
    /** array of all legal characters */
    private static final char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
            'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', '"', ' ', '!', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', ':',
            ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^', '_', '`', '{', '|', '}', '~'};

    /** lengths of all different subclasses of characters in chars array */
    private static final byte[] lengths = {26, 26, 10, 33};
    /** stores the key value used to create object */
    private final byte key;
    /** used to verify if password has every kind of required character */
    private byte containsChars = 0;

    /**
     * Used to create the object
     * @param key the unique key which refers to legal characters
     */
    public CharacterContainer(byte key) {
        this.key = key;
    }

    /**
     * Return total number of characters for given key
     * @return total number of characters as byte
     */
    private byte getTotal() {
        byte total = 0;
        for(byte i = 0; i < 4; ++i)
            total += ((key >> i) & 1) * lengths[i];

        return total;
    }

    /**
     * Updates the containsChars byte
      * @param index index of the output character
     */
    private void updateContainsChars(byte index) {
        byte notAnIfStatement = 1;
        byte lengthTotal = 95;
        for(byte i = 3; i >= 0; --i) {
            lengthTotal -= lengths[i];
            containsChars |= (byte) ((((key >> i) & 1) & ((lengthTotal - index) >>> 7) & notAnIfStatement) << i);
            notAnIfStatement = (byte)((index - lengthTotal) >>> 31);
        }
    }

    /**
     * Returns next character from given index and legal character pool
     * @param charInt the int from which index is calculated
     * @return the next character
     */
    public char nextChar(int charInt) {
        int index = charInt % getTotal();
        Offsets offset = new Offsets();
        index += offset.get((byte) (key - 1), (byte) index);
        updateContainsChars((byte) index);
        return chars[index];
    }

    public byte getCharsUsed() {
        return containsChars;
    }
}
