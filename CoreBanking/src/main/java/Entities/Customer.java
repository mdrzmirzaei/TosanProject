package Entities;

public class Customer {
    private int idCustomer;
    private String customer_name;
    private String customer_family;
    private String customer_address;

    public Customer(int idCustomer, String customer_name, String customer_family, String customer_address) {
        this.idCustomer = idCustomer;
        this.customer_name = customer_name;
        this.customer_family = customer_family;
        this.customer_address = customer_address;
    }
        public Customer() {

    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_family() {
        return customer_family;
    }

    public void setCustomer_family(String customer_family) {
        this.customer_family = customer_family;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    @Override
    public String toString() {
        return "the Customer name is :" + this.customer_name + "   the Customer family is :" + this.customer_family;
    }
}
