package CoreBankingManager;

import Entities.Transaction;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BankManager {
    public XSSFWorkbook workbook = new XSSFWorkbook();
    public XSSFSheet sheet = workbook.createSheet("Report");// i creat blank sheet
    CommandSQL cmd = new CommandSQL();

    public void exportExcel() {

        Row row = sheet.createRow(0);

        row.createCell(0).setCellValue("شماره تراکنش");
        row.createCell(1).setCellValue("تاریخ");
        row.createCell(2).setCellValue("ساعت");
        row.createCell(3).setCellValue("وضعیت");
        row.createCell(4).setCellValue("مبلغ تراکنش");
        row.createCell(5).setCellValue("نوع تراکنش");
        row.createCell(6).setCellValue("شماره مشتری");
        row.createCell(7).setCellValue("حساب مقصد");
        row.createCell(8).setCellValue("حساب مبدا");

        for (int i = 0; i < 8; i++) {
            sheet.autoSizeColumn(i);
        }


        ArrayList<Transaction> transactionList = cmd.getTransacitons('A');

        int threadCount = 4;
        int patitionSize = threadCount;
        if (threadCount > transactionList.size())
            patitionSize = transactionList.size();

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < transactionList.size(); i++) {
            int partition = transactionList.size() / patitionSize;
            int from = i * transactionList.size();
            int to = from + transactionList.size();
            executorService.submit(new ThreadRun(from, to, transactionList));
        }
        executorService.shutdown();


        try {

            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
            FileOutputStream fos = new FileOutputStream(new File(System.getProperty("user.dir") + "\\Report.xlsx"));


            workbook.write(fos);
            fos.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }

    }

    public class ThreadRun implements Runnable {
        int from;
        int to;
        ArrayList<Transaction> transactionArray = new ArrayList<>();

        public ThreadRun(int from, int to, ArrayList<Transaction> transactionArray) {
            this.from = from;
            this.to = to;
            this.transactionArray = transactionArray;
        }

        @Override
        public void run() {
            for (int i = from + 1; i < to + 1; i++) {

                Row row = sheet.createRow(i);
                row.createCell(0).setCellValue(transactionArray.get(i).getIdtransaction());
                row.createCell(1).setCellValue(transactionArray.get(i).getTransaction_date().toString());
                row.createCell(2).setCellValue(transactionArray.get(i).getTransaction_time().toString());
                row.createCell(3).setCellValue(transactionArray.get(i).getTransaction_status());
                row.createCell(4).setCellValue(transactionArray.get(i).getTransaction_amount().doubleValue());
                row.createCell(5).setCellValue(transactionArray.get(i).getKind_of_transaction());
                row.createCell(6).setCellValue(transactionArray.get(i).getTransaction_destination());
                row.createCell(7).setCellValue(transactionArray.get(i).getTransaction_customer_id());
                row.createCell(8).setCellValue(transactionArray.get(i).getTransaction_origin());

                for (int ii = 0; ii < 8; ii++) {
                    sheet.autoSizeColumn(ii);
                }

            }
        }
    }
}