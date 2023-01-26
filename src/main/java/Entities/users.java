package Entities;

/**
 * this Object is for System users...
 * @author Mirza
 */

public class users {

    public String name;
    public String family;
    private String kou /*kind_of_user*/;

    public users(){}

    public users(String name, String family, String kou) {
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

    public void setKou(String kou) {
        this.kou = kou;
    }

    public String getName() {
        return name;
    }

    public String getFamily() {
        return family;
    }

    public String getKou() {
        return kou;
    }
}
