package ua.training.model.dao.impl;

import org.junit.*;
import ua.training.model.entity.Report;
import ua.training.model.entity.TaxableItem;
import ua.training.model.service.ReportService;
import ua.training.model.service.UserService;

import java.sql.*;
import java.time.Instant;

import static org.junit.Assert.assertEquals;


public class JdbcTaxableItemDaoTest {

    private static Connection connection;

    private static long idPerson = -1;
    private static long idItem = 1;
    private static int quantity = 1;
    private static int price;

    @BeforeClass
    public static void setUp() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tax_report_system", "root", "PASSWORD");
            Statement stmt = connection.createStatement();
            //set taxable item
            stmt.execute("INSERT IGNORE INTO persons_has_taxable_items " +
                    "SET id_person = " + idPerson +
                    ", id_item = " + idItem +
                    ", quantity = " + quantity);

            //get price
            ResultSet rs = stmt.executeQuery("select price from taxable_items where id_item = " + idItem);
            if (rs.next()) {
                price = rs.getInt("price");
            }


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @AfterClass
    public static void tearDown() throws Exception {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void makeReport() {
        UserService userService = UserService.getInstance();
        ReportService reportService = ReportService.getInstance();

        Report report = new Report();
        report.setPerson(userService.getUserById(-1));
        report.setCompletionTime(Timestamp.from(Instant.now()));
        report.setCompanyName("test");
        report.setTaxpayerCode("0");

        reportService.submitReportAction(report);
    }

    @Test
    public void setTaxableThenCalcTotalAmount() {
        try {
            makeReport();
            Statement stmt = connection.createStatement();
            //get price
            ResultSet rs = stmt.executeQuery("select total_amount_of_property from reports " +
                    " join persons p on reports.id_person = p.id_person" +
                    " where p.id_person = " + idPerson);

            int totalAmount = 0;
            if (rs.next()) {
                totalAmount = rs.getInt("total_amount_of_property");
            }

            int expectedSum = quantity * price;
            assertEquals(expectedSum, totalAmount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}