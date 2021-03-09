package git.stella.model.characters;

/**
 * Calculates offset based on calculated index and key value
 */
public class Offsets {

    /**
     * offsetX where X = key. Closest thing to array of methods I found on Java.
     * @param index tempIndex from CharacterContainer method
     * @return the offset
     */
    public byte offset1(int index) { return 0; }
    public byte offset2(int index) { return 26; }
    public byte offset4(int index) { return 52; }
    public byte offset5(int index) { return (byte) ((((index - 26) >>> 31)^1) * 26);}
    public byte offset8(int index) { return 62; }
    public byte offset9(int index) { return (byte) ((((index - 26) >>> 31)^1) * 36); }
    public byte offset10(int index) { return (byte) (26 + (((index - 26) >>> 31)^1) * 10); }
    public byte offset11(int index) { return (byte) ((((index - 52) >>> 31)^1) * 10);}

    interface getOffset {
        byte get(int index);
    }

    private final getOffset[] offsets = new getOffset[] {
            this::offset1,    // offset for key: 1
            this::offset2,    // offset for key: 2
            this::offset1,    // offset for key: 3
            this::offset4,    // offset for key: 4
            this::offset5,    // offset for key: 5
            this::offset2,    // offset for key: 6
            this::offset1,    // offset for key: 7
            this::offset8,    // offset for key: 8
            this::offset9,    // offset for key: 9
            this::offset10,   // offset for key: 10
            this::offset11,   // offset for key: 11
            this::offset4,    // offset for key: 12
            this::offset5,    // offset for key: 13
            this::offset2,    // offset for key: 14
            this::offset1     // offset for key: 15
    };

    /**
     * Returns the offset value depending on key and index
     * @param offset key - 1 which represents function-index for offset function
     * @param index the calculated index
     * @return the offset value
     */
    public byte get(byte offset, byte index) {
        return offsets[offset].get(index);
    }
}
