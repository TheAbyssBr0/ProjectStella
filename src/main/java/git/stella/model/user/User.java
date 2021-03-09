package git.stella.model.user;

public class User {

    /** the string representation of the name of the User */
    private final String name;

    /** the string representation of the password for the User */
    private final String password;

    /**
     * The constructor for the User class
     * @param name String representation of name of User
     * @param password String representation of password of User
     */
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    /**
     * Returns information regarding the user in form of a string
     * @return a string
     */
    public String getInfo() {
        return "name:<" + this.name + ">pw:<" + this.password + ">";
    }
}
