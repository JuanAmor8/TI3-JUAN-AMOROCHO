package model;

import java.util.Date;

public class PremiumUser extends User{
    public static int MAXBOOKS = 1000;
    public static int MAXMAGAZINES = 1000;

    public PremiumUser(String name, String id, Date bindingDate, boolean premium) {
        super(name, id, bindingDate, premium);
    }
}
