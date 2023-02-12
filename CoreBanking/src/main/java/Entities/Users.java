package Entities;

/**
 * this Object is for System users...
 * @author Mirza
 */

public class Users {

    public String name;
    public String family;
    private char kou /*kind_of_user*/;

    public Users(){}

    public Users(String name, String family, char kou) {
        this.name = name;
        this.family = family;
        this.kou = kou;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public void setKou(char kou) {
        this.kou = kou;
    }

    public String getName() {
        return name;
    }

    public String getFamily() {
        return family;
    }

    public char getKou() {
        return kou;
    }
}
