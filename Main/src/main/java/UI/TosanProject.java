package UI;

import CoreBankingManager.*;
import Entities.BankAccount;
import Entities.Customer;
import Entities.Installments;
import Entities.Transaction;
import LoanManager.InstallmentCalculate;
import LoanManager.InstallmentPayment;
import LoanManager.LoanManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class TosanProject extends JFrame {

    CustomerManager customerManager = new CustomerManager();
    Customer customerSwing = new Customer();
    CommandSQL cmd = new CommandSQL();
    BankAccountManager bankAccountManager = new BankAccountManager();
    ArrayList<Entities.BankAccount> bankAccountArrayList = new ArrayList<>();
    BankAccount selectedBankAccount = new BankAccount();
    LoanManager loanManager = new LoanManager();
    public  JPanel Main;
    private JTextField userName;
    private JButton enter;
    private JTextField password;
    private JLabel userl;
    private JLabel passl;
    private JPanel Login;
    private JButton createCustomer;
    private JButton editcusotmer;
    private JButton deleteCustomer;
    private JButton ShowCustomers;
    private JPanel Customer;
    private JPanel CustomercCreate;
    private JTextField customer_name;
    private JTextField customer_family;
    private JTextField address;
    private JLabel customerName;
    private JLabel customerFamily;
    private JLabel customerAddress;
    private JButton create_customer;
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
    private JLabel cname_depo;
    private JLabel idbank_accout_deposit;
    private JLabel AmountDeposit;
    private JTextField UI_idCustomer;
    private JTextField UI_bankAccount;
    private JTextField UI_Amount;
    private JButton deposittobankaccont;
    private JPanel deposittocustomer;
    private JTabbedPane Loan;
    private JPanel createLoan;
    private JPanel showinstallments;
    private JPanel giveainstallments;
    private JButton givetoloan;
    private JTextField Loanidcustomer;
    private JTextField loanAmount;
    private JTextField Loanmonths;
    private JTable table2;
    private JButton loanok;
    private JTextField UI_Installment_idcustomer;
    private JButton UI_ShowInstallments;
    private JTable tableInstallments;
    private JButton UI_checkInstallments;
    private JLabel countofinstallment;
    private JLabel sumOfAmount;
    private JTextField UI_installment_id_customer;
    private JTextField IU_installment_idbankaccount;
    private JTextField IU_installment_sumOfAmount;
    private JTextField IU_installment_countofinstallments;
    private JTable depsitinstallments;
    private JTabbedPane Manager;
    private JPanel ShowBankresource;
    private JTextField bankresource;
    private JTextField rate;
    private JButton showresource;
    private JButton editresource;
    private JButton editrate;
    private JButton fivetransaction;
    private JScrollPane jscrollpane;
    private JTable five;
    private JPanel fiveLast;
    private JPanel fullReport;
    private JPanel Excel;
    private JButton fullreportinterest;
    private JScrollPane scroll;
    private JTable fullInterest;
    private JButton excel;
    private char kou;


    public TosanProject() {

        setContentPane(Main);
        Main.setBackground(Color.DARK_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1027, 768);
        setResizable(false);
        setLocation(100, 100);
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
        deposittocustomer.setBackground(Color.DARK_GRAY);
        createLoan.setBackground(Color.DARK_GRAY);
        showinstallments.setBackground(Color.DARK_GRAY);
        giveainstallments.setBackground(Color.DARK_GRAY);
        Manager.setVisible(false);
        ShowBankresource.setBackground(Color.DARK_GRAY);
        fiveLast.setBackground(Color.DARK_GRAY);


        tabbedPane1.setVisible(false);
        Loan.setVisible(false);
        BankAccount.setVisible(false);
        Customer.setVisible(false);
        ShowBankresource.setVisible(false);



        currncy.addItem("ریال");
        currncy.addItem("دلار");


        setVisible(true);


        enter.addActionListener(e -> {
            if (UserLogin.login(userName.getText(), password.getText()) != null) {
                JOptionPane.showMessageDialog(Main, (UserLogin.getUser().getName() + " " + UserLogin.getUser().getFamily() + "   خوش آمدید "));
                kou = UserLogin.getUser().getKou();
                if (UserLogin.getUser().getKou() == 'E') {

                    tabbedPane1.setVisible(true);
                    Loan.setVisible(true);
                    BankAccount.setVisible(true);
                    Customer.setVisible(true);
                    Manager.setVisible(false);

                }
                if (UserLogin.getUser().getKou() == 'M') {
                    tabbedPane1.setVisible(false);
                    Loan.setVisible(false);
                    BankAccount.setVisible(false);
                    Customer.setVisible(false);
                    Manager.setVisible(true);

                }
                Login.setVisible(Boolean.FALSE);

            } else {
                JOptionPane.showMessageDialog(Main, "نام کاربری یا رمز عبورتان اشتباه است","اخطار",JOptionPane.OK_OPTION);
                userName.setText("");
                password.setText("");
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

                    UI_idCustomer.setText(String.valueOf(customerSwing.getIdCustomer()));

                    Loanidcustomer.setText(String.valueOf(customerSwing.getIdCustomer()));


                    UI_Installment_idcustomer.setText(customerSwing.getCustomer_name() + "  " + customerSwing.getCustomer_family());


                    UI_installment_id_customer.setText(String.valueOf(customerSwing.getIdCustomer()));

                    //******************************* full Tables
                    if (depsitinstallments.getRowCount() == 0) {
                        DefaultTableModel model = new DefaultTableModel();
                        bankAccountArrayList = cmd.select_bank_accounts("bank_account_customer_id", "=", UI_installment_id_customer.getText());


                        Object[] ColumnsName = new Object[2];
                        ColumnsName[0] = "شماره حساب";
                        ColumnsName[1] = "موجودی";
                        model.setColumnIdentifiers(ColumnsName);
                        Object[] rowData = new Object[4];

                        for (int i = 0; i < bankAccountArrayList.size(); i++) {

                            rowData[0] = bankAccountArrayList.get(i).getIdbank_account();
                            rowData[1] = bankAccountArrayList.get(i).getBank_account_balance();
                            model.addRow(rowData);
                        }
                        depsitinstallments.setModel(model);
                    }


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
                    JOptionPane.showMessageDialog(UICreateBankAccount, "حساب مشتری با موفقیت ایجاد گردید");

            }
        });
        deposittobankaccont.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (bankAccountManager.deposit(UI_idCustomer.getText(), UI_bankAccount.getText(), UI_Amount.getText()))
                    JOptionPane.showMessageDialog(BankAccount, "واریز مبلغ انجام گردید");
            }
        });
        givetoloan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (loanManager.loanRequest(Double.valueOf(loanAmount.getText()), Integer.valueOf(Loanmonths.getText()))) {
                    double rate = loanManager.getLoanRate();

                    JOptionPane.showMessageDialog(Loan, "مشتری واجد دریافت وام می باشد" + "درصد محاسبه سود وام نیز " + rate + "   می باشد");

                    if (table2.getRowCount() == 0) {
                        DefaultTableModel model = new DefaultTableModel();
                        bankAccountArrayList = cmd.select_bank_accounts("bank_account_customer_id", "=", Loanidcustomer.getText());


                        Object[] ColumnsName = new Object[2];
                        ColumnsName[0] = "شماره حساب";
                        ColumnsName[1] = "موجودی";
                        model.setColumnIdentifiers(ColumnsName);
                        Object[] rowData = new Object[4];

                        for (int i = 0; i < bankAccountArrayList.size(); i++) {

                            rowData[0] = bankAccountArrayList.get(i).getIdbank_account();
                            rowData[1] = bankAccountArrayList.get(i).getBank_account_balance();
                            model.addRow(rowData);
                        }
                        table2.setModel(model);
                    }


                } else
                    JOptionPane.showMessageDialog(Loan, "مشتری واجد دریافت وام نمی باشد");


            }
        });
        loanok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int row = table2.getSelectedRow();
                System.out.println(table2.getValueAt(row, 0));

                selectedBankAccount = cmd.select_one_bank_account(bankAccountArrayList, String.valueOf(table2.getValueAt(row, 0)));
                System.out.println(selectedBankAccount.getIdbank_account());
                //for installments payment
                IU_installment_idbankaccount.setText(String.valueOf(selectedBankAccount.getIdbank_account()));

                LoanManager loanManager = new LoanManager();
                InstallmentCalculate installmentCalculate = new InstallmentCalculate(loanManager.getLoanRate(), Integer.valueOf(Loanmonths.getText()), Double.valueOf(loanAmount.getText().toString()));


                if (installmentCalculate.accountToaccount(customerSwing) && installmentCalculate.fillInstallmentsData(selectedBankAccount)) {
                    JOptionPane.showMessageDialog(Main, "عملیات واریز تسهیلات انجام شد و جدول مربوط به اقساط تشکیل گردید");
                } else
                    JOptionPane.showMessageDialog(Main, "متاسفانه عملیات واریز تسهیلات ناموفق بود");

            }
        });
        UI_ShowInstallments.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableInstallments.getRowCount() == 0) {
                    DefaultTableModel model = new DefaultTableModel();
                    ArrayList<Installments> installmentsArrayList = cmd.select_installment_cmd(customerSwing);

                    Object[] ColumnsName = new Object[6];
                    ColumnsName[0] = "شماره ماه پرداختی";
                    ColumnsName[1] = "مبلغ اصلی پول";
                    ColumnsName[2] = "مبلغ سود";
                    ColumnsName[3] = "حاصلجمع سود و اصل پول";
                    ColumnsName[4] = "وضعیت اقساط";
                    ColumnsName[5] = "تاریخ موثر";

                    model.setColumnIdentifiers(ColumnsName);
                    Object[] rowData = new Object[6];

                    for (int i = 0; i < installmentsArrayList.size(); i++) {

                        rowData[0] = installmentsArrayList.get(i).getInstallments_months();
                        rowData[1] = installmentsArrayList.get(i).getInstallments_principle_amount();
                        rowData[2] = installmentsArrayList.get(i).getInstallments_interest();
                        rowData[3] = installmentsArrayList.get(i).getInstallments_sum_pi_amount();
                        rowData[4] = installmentsArrayList.get(i).getInstallments_status();
                        rowData[5] = installmentsArrayList.get(i).getInstallments_dueDate();

                        model.addRow(rowData);
                    }

                    tableInstallments.setModel(model);
                }

            }
        });
        UI_checkInstallments.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = depsitinstallments.getSelectedRow();
                IU_installment_idbankaccount.setText(String.valueOf(depsitinstallments.getValueAt(row, 0)));
//                bankAccountArrayList.clear();
//                bankAccountArrayList = cmd.select_bank_accounts("bank_account_customer_id", "=", UI_installment_id_customer.getText());
                selectedBankAccount = cmd.select_one_bank_account(bankAccountArrayList, String.valueOf(depsitinstallments.getValueAt(row, 0)));
                IU_installment_idbankaccount.setText(String.valueOf(selectedBankAccount.getIdbank_account()));
                InstallmentPayment installmentPayment = new InstallmentPayment(IU_installment_idbankaccount.getText(), Integer.valueOf(IU_installment_countofinstallments.getText()));
                System.out.println(selectedBankAccount.getIdbank_account());
                IU_installment_sumOfAmount.setText(String.valueOf(installmentPayment.getInstallmentAmount()));


                if (installmentPayment.checkInstallments(selectedBankAccount) != 0d && String.valueOf(installmentPayment.getInstallmentAmount()) != null) {
                    int answer = 0;
                    JOptionPane.showConfirmDialog(null, "آیا مشتری موافق پرداخت این مبلغ است؟ " + installmentPayment.getInstallmentAmount(), "تایید پرداخت اقساط", JOptionPane.YES_NO_OPTION);
                    switch (answer) {
                        case JOptionPane.YES_OPTION:
                            System.out.println("عملیات پرداخت اقساط انجام شود");
                            installmentPayment.accountToaccount(customerSwing);
                            break;

                        case JOptionPane.NO_OPTION:
                            System.out.println("مشتری منصرف شده است ");
                            break;
                    }

                }
            }
        });
        showresource.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                bankresource.setText(String.valueOf(cmd.get_financial_ressource_cmd()));
                rate.setText(String.valueOf(loanManager.getLoanRate()));


            }
        });
        editresource.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (bankresource!= null)
                cmd.update_cmd("financial_ressource",1,"financial_amount",bankresource.getText());
            }
        });
        editrate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rate!= null)
                cmd.update_cmd("loan",1,"loanRate",rate.getText());
            }
        });
        fivetransaction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (five.getRowCount() == 0) {
                    DefaultTableModel model = new DefaultTableModel();
                    ArrayList<Transaction> fiveLastarray = new ArrayList<>();
                    fiveLastarray= cmd.getTransacitons('F');

                    Object[] ColumnsName = new Object[5];
                    ColumnsName[0] = "تاریخ تراکنش";
                    ColumnsName[1] = "ساعت تراکنش";
                    ColumnsName[2] = "مبلغ تراکنش";
                    ColumnsName[3] = "وضعیت تراکنش";
                    ColumnsName[4] = "نوع تراکنش";

                    model.setColumnIdentifiers(ColumnsName);
                    Object[] rowData = new Object[5];

                    for (int i = 0; i < fiveLastarray.size(); i++) {

                        rowData[0] = fiveLastarray.get(i).getTransaction_date();
                        rowData[1] = fiveLastarray.get(i).getTransaction_time();
                        rowData[2] = fiveLastarray.get(i).getTransaction_amount();
                        rowData[3] = fiveLastarray.get(i).getTransaction_amount();
                        rowData[4] = fiveLastarray.get(i).getKind_of_transaction();
                        model.addRow(rowData);
                    }
                    five.setModel(model);
                }
            }
        });
        fullreportinterest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (fullInterest.getRowCount() == 0) {
                    DefaultTableModel model = new DefaultTableModel();
                    HashMap<String, String> hm = new HashMap<>();
                    hm = cmd.getTotalInterests();


                    Object[] ColumnsName = new Object[2];
                    ColumnsName[0] = "شاسه مشتری";
                    ColumnsName[1] = "جمع سود بانک";


                    model.setColumnIdentifiers(ColumnsName);
                    Object[] rowData = new Object[2];


                    for (Map.Entry me : hm.entrySet()
                    ) {
                        rowData[0] = me.getKey().toString();
                        rowData[1] = me.getValue().toString();
                        model.addRow(rowData);
                    }
                    fullInterest.setModel(model);
                }
            }
        });

        excel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BankManager bankManager=new BankManager();
                bankManager.exportExcel();
                JOptionPane.showMessageDialog(null,"تولید فایل خروجی در حال انجام است می توانید پس از آن به دسکتاپ رفته و فایل را مشاهده کنید");
            }
        });
    }
}
