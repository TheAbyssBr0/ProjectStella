package git.stella.model.utils;

public class KeyContainer {

    /** stores the four booleans */
    byte keys;

    /**
     * Constructor class for KeyContainer
     * @param lower boolean of if lowercase letters are included
     * @param upper boolean of if uppercase letters are included
     * @param num boolean of if numbers are included
     * @param sym boolean of if symbols are included
     */
    public KeyContainer(boolean lower, boolean upper, boolean num, boolean sym) {
        boolean[] arr = {lower, upper, num, sym};
        for(int i = 0; i < 4; ++i)
            if (arr[i]) this.keys |= 1 << i;
    }

    /**
     * Getter for the byte stored in keys
     * @return the byte stored in keys
     */
    public byte getByte() {
        return this.keys;
    }
}
