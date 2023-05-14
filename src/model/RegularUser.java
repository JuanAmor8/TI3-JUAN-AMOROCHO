package model;

import java.util.Date;

public class RegularUser extends User{
    public static int MAXBOOKS = 5;
    public static int MAXMAGAZINES = 2;

    public RegularUser(String name, String id, Date bindingDate, boolean premium) {
        super(name, id, bindingDate, premium);
    }
}
