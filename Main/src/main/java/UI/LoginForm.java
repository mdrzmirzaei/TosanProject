package UI;

import CoreBankingManager.BankAccountManager;
import CoreBankingManager.CommandSQL;
import CoreBankingManager.CustomerManager;
import CoreBankingManager.UserLogin;
import Entities.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class LoginForm extends JFrame {

    CustomerManager customerManager = new CustomerManager();
    Customer customerSwing = new Customer();
    CommandSQL cmd = new CommandSQL();
    BankAccountManager bankAccountManager = new BankAccountManager();
    private JPanel Main;
    private JTextField userName;
    private JButton enter;
    private JTextField password;
    private JLabel userl;
    private JLabel passl;
    private JPanel Login;
    private JPanel Employee;
    private JButton createCustomer;
    private JButton editcusotmer;
    private JButton deleteCustomer;
    private JButton createBankAccount;
    private JButton ShowCustomers;
    private JButton deposit;
    private JButton CreateLoan;
    private JButton Showinstallments;
    private JButton giveInstallments;
    private JPanel Manager;
    private JPanel Customer;
    private JPanel CustomercCreate;
    private JTextField customer_name;
    private JTextField customer_family;
    private JTextField address;
    private JLabel customerName;
    private JLabel customerFamily;
    private JLabel customerAddress;
    private JButton create_customer;
    private JPanel statusBar;
    private JButton backtoMain;
    private JPanel Editc;
    private JButton editc;
    private JTextField editcname;
    private JTextField editcfamily;
    private JTextField editcaddress;
    private JTextField search_idcustomer;
    private JLabel tip1;
    private JButton SearchCustomer;
    private JTable table1;
    private JPanel Showc;
    private JTabbedPane tabbedPane1;
    private JButton searchCustomer;
    private JTextField sfamilyc;
    private JTextField saddressc;
    private JTextField snamec;
    private JPanel searchcustomer;
    private JTabbedPane BankAccount;
    private JPanel UICreateBankAccount;
    private JTextField UIcustomercreateba;
    private JLabel bacn_UI;
    private JLabel bab_UI;
    private JLabel baa_UI;
    private JTextField baba_UI;
    private JButton BT_createBA;
    private JComboBox currncy;
    private char kou;


    public LoginForm() {

        setContentPane(Main);
        Main.setBackground(Color.DARK_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);
        setLocation(512, 381);
        setTitle("پروژه توسن - ورژن 1.0");
        userl.setText("نام کاربری");
        userl.setForeground(Color.WHITE);
        passl.setText("رمز عبور");
        passl.setForeground(Color.WHITE);

        for (Component c : Main.getComponents()
        ) {
            if (c instanceof JPanel) {
                c.setBackground(Color.DARK_GRAY);
                c.setVisible(Boolean.FALSE);
            }
            if (c instanceof JLabel) {
                c.setForeground(Color.WHITE);
            }
        }
        Login.setVisible(Boolean.TRUE);
        CustomercCreate.setBackground(Color.DARK_GRAY);
        tip1.setForeground(Color.WHITE);
        Showc.setBackground(Color.DARK_GRAY);
        searchcustomer.setBackground(Color.DARK_GRAY);
        Editc.setBackground(Color.DARK_GRAY);
        UICreateBankAccount.setBackground(Color.DARK_GRAY);

        currncy.addItem("ریال");
        currncy.addItem("دلار");


        setVisible(true);


        enter.addActionListener(e -> {
            if (UserLogin.login(userName.getText(), password.getText()) != null) {
                JOptionPane.showMessageDialog(Main, (UserLogin.getUser().getName() + " " + UserLogin.getUser().getFamily() + "   خوش آمدید "));
                kou = UserLogin.getUser().getKou();
                if (UserLogin.getUser().getKou() == 'E') {
                    Employee.setBackground(Color.DARK_GRAY);
                    //   Manager.setVisible(false);
                    Employee.setVisible(Boolean.TRUE);
                }
                if (UserLogin.getUser().getKou() == 'M') {
                    //         Manager.setBackground(Color.DARK_GRAY);
                    //        Manager.setVisible(Boolean.TRUE);
                    Employee.setVisible(true);
                }
                Login.setVisible(Boolean.FALSE);
                Customer.setVisible(true);


            } else {
                JOptionPane.showMessageDialog(Main, "نام کاربری یا رمز عبورتان اشتباه است");
                userName.setText("");
                password.setText("");
            }
        });
        backtoMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (kou == 'E') {
                    for (Component c : Main.getComponents()
                    ) {
                        if (c instanceof JPanel) {
                            c.setBackground(Color.DARK_GRAY);
                            c.setVisible(Boolean.FALSE);
                        }
                    }
                    Employee.setVisible(true);
                }

                if (kou == 'M')
                    for (Component c : Main.getComponents()
                    ) {
                        if (c instanceof JPanel) {
                            c.setBackground(Color.DARK_GRAY);
                            c.setVisible(Boolean.FALSE);
                        }
                    }
                Manager.setVisible(true);
            }
        });
        create_customer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (customer_name.getText().length() > 0 && customer_family.getText().length() > 0 && address.getText().length() > 0) {

                    if (customerManager.insertCustomer(customer_name.getText(), customer_family.getText(), address.getText()) == true)
                        JOptionPane.showMessageDialog(Customer, "اطلاعات مشتری با موفقیت ذخیره شد");
                } else
                    JOptionPane.showMessageDialog(Customer, "باید تمامی فیلدها پر باشند");
            }
        });
        editc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (editcfamily.getText().length() > 0 && editcname.getText().length() > 0 && editcaddress.getText().length() > 0) {
                    customerManager.customerEditor(search_idcustomer.getText(), editcname.getText(), editcfamily.getText(), editcaddress.getText());
                    JOptionPane.showMessageDialog(Customer, "اطلاعات مشتری مورد نظر شما ویرایش شد");
                }
            }
        });
        SearchCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (search_idcustomer == null) {
                    JOptionPane.showMessageDialog(Customer, "فیلد شناسه برای جستجو خالی می باشد");
                }
                customerSwing = null;
                customerSwing = cmd.select_customer_cmd("idcustomer", "=", search_idcustomer.getText());
                if (customerSwing != null) {
                    editcname.setText(customerSwing.getCustomer_name());
                    editcfamily.setText(customerSwing.getCustomer_family());
                    editcaddress.setText(customerSwing.getCustomer_address());

                    snamec.setText(customerSwing.getCustomer_name());
                    sfamilyc.setText(customerSwing.getCustomer_family());
                    saddressc.setText(customerSwing.getCustomer_address());

                    UIcustomercreateba.setText(customerSwing.getCustomer_name() + "   " + customerSwing.getCustomer_family());


                } else if (customerSwing == null) {
                    JOptionPane.showMessageDialog(Customer, "متاسفانه فرد مورد نظر پیدا نشد");
                    editcaddress.setText("");
                    editcfamily.setText("");
                    editcname.setText("");
                    snamec.setText("");
                    sfamilyc.setText("");
                    saddressc.setText("");
                }


            }
        });

        deleteCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (editcfamily.getText().length() > 0 && editcname.getText().length() > 0 && editcaddress.getText().length() > 0 && customerManager.customerDelete(search_idcustomer.getText())) {
                    JOptionPane.showMessageDialog(Customer, "اطلاعات مشتری مورد نظر شما حذف گردید");
                } else
                    JOptionPane.showMessageDialog(Customer, "اطلاعات برای حذف مشتری کافی نیست!");
            }
        });


        ShowCustomers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table1.removeAll();
                if (table1.getRowCount() == 0) {
                    DefaultTableModel model = new DefaultTableModel();
                    ArrayList<Customer> customerArryaList = customerManager.showCustomers();


                    Object[] ColumnsName = new Object[3];
                    ColumnsName[0] = "آدرس";
                    ColumnsName[1] = "نام خانوادگی";
                    ColumnsName[2] = "نام";
                    model.setColumnIdentifiers(ColumnsName);
                    Object[] rowData = new Object[3];

                    for (int i = 0; i < customerArryaList.size(); i++) {

                        rowData[0] = customerArryaList.get(i).getCustomer_address();
                        rowData[1] = customerArryaList.get(i).getCustomer_family();
                        rowData[2] = customerArryaList.get(i).getCustomer_name();
                        model.addRow(rowData);
                    }

                    table1.setModel(model);
                }


            }
        });

        BT_createBA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                char cur;
                if (currncy.getSelectedItem() == "ریال")
                    cur = 'R';
                else
                    cur = '$';

                if (customerManager.createCustomerAccount(search_idcustomer.getText(), cur, baba_UI.getText().toString()))
                    JOptionPane.showMessageDialog(UICreateBankAccount,"حساب مشتری با موفقیت ایجاد گردید");

            }
        });
    }
}
