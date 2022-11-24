/**
 * purpose: quản lý thông tin tài khoản khách hàng
 * date created: 24/9/2022
 * author: Van Truong
 * version: 1.0.0
 */

package digitalbanking.model;

import java.util.Scanner;

public class Account {
    /*ATTRIBUTES*/
    private String accountNumber;
    private double balance;

    /*GETTERS & SETTERS*/
    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        if (validateAccount(accountNumber))
            this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        if (balance >= 50_000)
            this.balance = balance;
    }

    /*CONSTRUCTORS*/
    public Account() {

    }

    public Account(String accountNumber, double balance) {
        if (validateAccount(accountNumber))
            this.accountNumber = accountNumber;
        if (balance >= 50_000)
            this.balance = balance;
    }

    /*SERVICE METHODS*/
    //phương thức nhập dữ liệu
    public void input(Scanner scanner) {
        do {
            System.out.print("Nhập số tài khoản gồm 6 chữ số: ");
            this.accountNumber = scanner.nextLine();
        } while (!validateAccount(this.accountNumber));
        do {
            System.out.print("Nhập số dư tài khoản >= 50000 (VNĐ): ");
            this.balance = Double.parseDouble(scanner.nextLine());
        } while (this.balance < 50_000);
    }

    //phương thức xuất dữ liệu
    @Override
    public String toString() {
        return String.format("%-6s | %,35.2f (VNĐ)", this.accountNumber, this.balance);
    }

    //phương thức cho biết tài khoản có phải là premium hay không
    public void isPremium() {
        boolean isPremium = isAccountPremium();
        System.out.printf("Tài khoản %s %s premium\n", this.accountNumber, isPremium ? "là" : "không phải là");
    }

    //phương thức kiểm tra số tài khoản hợp lệ
    private boolean validateAccount(String accountNumber) {
        //kiểm tra kích thước chuỗi = 6
        if (accountNumber.length() != 6)
            return false;

        //kiểm tra chuỗi có phải chuỗi số
        for (int i = 0; i < accountNumber.length(); i++) {
            try {
                Integer.parseInt(accountNumber.substring(i, i + 1));
            } catch (Exception error) {
                return false;
            }
        }
        return true;
    }

    //phương thức kiểm tra tài khoản có phải là premium hay không
    public boolean isAccountPremium() {
        return this.balance >= 10_000_000;
    }
}
