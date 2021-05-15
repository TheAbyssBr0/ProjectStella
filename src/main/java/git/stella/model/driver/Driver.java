package git.stella.model.driver;

import git.stella.model.generator.Generator;
import git.stella.model.key.KeyContainer;
import git.stella.model.user.User;

public class Driver {
    public static void main(String[] args) {
        // inline tester
        String username = "Security maniac";
        String password = "1234password";
        String service = "4chan";
        KeyContainer keyContainer = new KeyContainer(true, true, true, true);
        int len = 32;
        int num = 1;

        User user = new User(username, password);
        Generator generator = new Generator(user);
        System.out.println("Password generated is: " + generator.getPassword(service, keyContainer, len, num));
    }
}
