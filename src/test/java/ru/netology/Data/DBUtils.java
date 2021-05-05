package ru.netology.Data;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import ru.netology.Models.CreditModel;
import ru.netology.Models.OrderModel;
import ru.netology.Models.PaymentModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class DBUtils {
    private static Connection connection;
    private static String userBD = System.getProperty("userDB");
    private static String password = System.getProperty("password");
    private static String url = System.getProperty("db.url");

    private static String creditSQLQuery = "SELECT * FROM credit_request_entity WHERE created IN (SELECT max(created) " +
            "FROM credit_request_entity);";
    private static String orderSQLQuery = "SELECT * FROM order_entity WHERE created IN (SELECT max(created) " +
            "FROM order_entity);";
    private static String paymentSQLQuery = "SELECT * FROM payment_entity WHERE created IN (SELECT max(created) " +
            "FROM payment_entity);";

    @SneakyThrows
    private static Connection getConnection() {
        if (connection == null)
            connection = DriverManager.getConnection(url, userBD, password);
        return connection;
    }

    @SneakyThrows
    public static void clearAllData() {
        val runner = new QueryRunner();
        runner.update(getConnection(), "DELETE FROM credit_request_entity;");
        runner.update(getConnection(), "DELETE FROM payment_entity;");
        runner.update(getConnection(), "DELETE FROM order_entity;");
    }

    @SneakyThrows
    public static void checkLastPaymentStatus(String status) {
        val runner = new QueryRunner();
        val paymentRow = runner.query(getConnection(), paymentSQLQuery, new BeanHandler<>(PaymentModel.class));
        assertEquals(status, paymentRow.status, "Transaction status should be as");
    }

    @SneakyThrows
    public static void checkLastCreditStatus(String status) {
        val runner = new QueryRunner();
        val creditRow = runner.query(getConnection(), creditSQLQuery, new BeanHandler<>(CreditModel.class));
        assertEquals(status, creditRow.status, "Credit status should be as");
    }

    @SneakyThrows
    public static void checkRowCreditNotNull() {
        val runner = new QueryRunner();
        val orderRow = runner.query(getConnection(), orderSQLQuery, new BeanHandler<>(OrderModel.class));
        val creditRow = runner.query(getConnection(), creditSQLQuery, new BeanHandler<>(CreditModel.class));
        assertNotNull(orderRow);
        assertNotNull(creditRow);
    }

    @SneakyThrows
    public static void checkRowPaymentNotNull() {
        val runner = new QueryRunner();
        val orderRow = runner.query(getConnection(), orderSQLQuery, new BeanHandler<>(OrderModel.class));
        val paymentRow = runner.query(getConnection(), paymentSQLQuery, new BeanHandler<>(PaymentModel.class));
        assertNotNull(orderRow);
        assertNotNull(paymentRow);
    }

    @SneakyThrows
    public static void compareExpectedAmountWithActual(int amount) {
        val runner = new QueryRunner();
        val paymentRow = runner.query(getConnection(), paymentSQLQuery, new BeanHandler<>(PaymentModel.class));
        assertEquals(amount, paymentRow.amount, "Transaction amount should be as");
    }

    @SneakyThrows
    public static void compareIDsPaymentAndOrder() {
        val runner = new AtomicReference<>(new QueryRunner());
        val paymentRow = runner.get().query(getConnection(), paymentSQLQuery, new BeanHandler<>(PaymentModel.class));
        val orderRow = runner.get().query(getConnection(), orderSQLQuery, new BeanHandler<>(OrderModel.class));
        assertEquals(paymentRow.transaction_id, orderRow.payment_id, "Payment and Order IDs are not equal");
    }

    @SneakyThrows
    public static void compareIDsCreditAndOrder() {
        val runner = new QueryRunner();
        val creditRow = runner.query(getConnection(), creditSQLQuery, new BeanHandler<>(CreditModel.class));
        val orderRow = runner.query(getConnection(), orderSQLQuery, new BeanHandler<>(OrderModel.class));
        assertEquals(creditRow.bank_id, orderRow.payment_id, "Credit and Order IDs are not equal");
    }
}
