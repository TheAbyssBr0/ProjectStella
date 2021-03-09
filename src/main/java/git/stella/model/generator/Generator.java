package git.stella.model.generator;

import de.mkammerer.argon2.Argon2Advanced;
import de.mkammerer.argon2.Argon2Factory;
import git.stella.model.characters.CharacterContainer;
import git.stella.model.user.User;
import git.stella.model.utils.KeyContainer;

import java.nio.charset.StandardCharsets;

import static java.lang.Math.abs;

/**
 * Generates the passwords using argon2di hashing algorithm
 */
public class Generator {
    /** user object that contains user information */
    private final User user;

    /**
     * Constructor for generator
     */
    public Generator(User user) {
        this.user = user;
    }

    /**
     * Helper function for getPassword. Calculates argon hash
     * @param service String name of the service
     * @param len half length of generated byte array, length of password (2 array items -> 1 password character)
     * @param num password number. Useful to have multiple passwords for same site
     * @param var variation of password to help with password character requirements
     * @return the byte array containing the argon 2di hash
     */
    private byte[] generateHash(String service, int len, int num, int var) {
        byte[] salt = (service + num + var).getBytes(StandardCharsets.US_ASCII);
        Argon2Advanced argon2 = Argon2Factory.createAdvanced(Argon2Factory.Argon2Types.ARGON2id, salt.length,
                len*2);
        return argon2.rawHash(1, 128*1024, 1, user.getInfo().toCharArray(), salt);
    }


    /**
     * Returns password based on given parameters
     * @param service String representation of service the password is for e.g. twitter
     * @param key KeyContainer object that contains the legal character type information (upper, lower, etc.)
     * @param len length of required password
     * @param num password number. Useful for having multiple passwords under same site name
     * @return String representation of password
     */
    public String getPassword(String service, KeyContainer key, int len, int num) {
        int variation = 0;
        StringBuilder pass;
        byte[] hash;
        CharacterContainer container;
        do {
            pass = new StringBuilder();
            container = new CharacterContainer(key.getByte());
            hash = generateHash(service, len, num, variation);
            for(int i = 0, index; i < len; ++i) {
                index = abs(hash[i * 2]) | abs(hash[i * 2 | 1]) << 7;
                pass.append(container.nextChar(index));
            }

            ++variation;
        } while (container.getCharsUsed() != key.getByte());
        return pass.toString();
    }
}
