package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Users {
    public String login;
    public String password;

    File file = new File("src/sample/users.txt");

    public boolean checkPsw() throws FileNotFoundException  {
        boolean flag = false;
        String tookStr = this.login  + " " + this.password;
        String str;

        try (Scanner sc = new Scanner(file)) {
            do {
                str = sc.nextLine();

                if (str.equals(tookStr)) {
                    flag = true;
                }
            } while (sc.hasNextLine() && flag == false);
        }
        return flag;
    }
}
