import DB.commandSQL;
import org.apache.poi.ss.formula.functions.Finance;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

public class loanRequest {


    private commandSQL cmd = new commandSQL();

    public boolean acceptRequet(BigDecimal Amount, int monthsToPay) {
        if (cmd.select_financial("financial_resource", "financial_amount", ">=", Amount.toString())) {
            System.out.println("you can get the loan");
            return true;
        } else {
            System.out.println("you can not Get Loan!!!");
            return false;
        }
    }

    public void createLoanFile(String tableName, HashMap<String, String> colval) {

        cmd.insert_cmd(tableName, colval);

    }


    public void test() {

Double ra= (18/12d)/100 ;
//Double d=Double.valueOf(String.format("%.3f",ra)) ;

        System.out.println();
        int nper = 120;
        Double pv = 30000000d;

        HashMap<Integer, String> hm = new HashMap<>();

        for (int i = 1; i <= nper; i++) {


            hm.put(i, "mablaghe ghest :" + Math.round(Finance.pmt(ra, nper, pv) * -1) + "  mablaghe asle pool:" + Math.round(Finance.ppmt(ra, i, nper, pv) * -1) + "   mablaghe sooood: " + Math.round(Finance.ipmt(ra, i, nper, pv) * -1));
        }

        for (Map.Entry me : hm.entrySet()
        ) {
            System.out.println(me.getKey() + "   " + me.getValue());

        }


    }
}
