/**
 * This class represents a bank account whose current balance is a nonnegative
 * amount in US dollars.
 * @author Kaicheng Jia
 */

public class Account {

    public int balance;
    public Account parentAccount;

    /** Initialize an account with the given balance. */
    public Account(int balance) {
        this.balance = balance;
        this.parentAccount = null;
    }

    /** Add a two-argument constructor. */
    public Account(int balance, Account A) {
        this.balance = balance;
        this.parentAccount = A;
    }


    /** Deposits amount into the current account. */
    public void deposit(int amount) {
        if (amount < 0) {
            System.out.println("Cannot deposit negative amount.");
        } else {
            balance += amount;
        }
    }

    /**
     * Subtract amount from the account if possible. 
     * if the requested withdrawal can’t be covered by this account,
     * the difference is withdrawn from the parent account.
     */
    public boolean withdraw(int amount) {
        if (amount < 0) {
            System.out.println("Error: The amount should be positive!");
            return false;
        }
        else {
            if (amount <= balance) {
                balance -= amount;
                return true;
            }
            else {
                if (parentAccount == null) {
                    return false;
                }
                else if ((parentAccount.balance + this.balance) >= amount) {
                    parentAccount.balance -= (amount - balance);
                    balance = 0;
                    return true;
                }
            }
            return false;
        }
    }


    /**
     * Merge account other into this account by removing all money from other
     * and depositing it into this account.
     */
    public void merge(Account other) {
        this.balance += other.balance;
        other.balance = 0;
    }

     /**
     * Main method
     */   
    public static void main(String[] args) {
        Account zoe = new Account(500);
        Account matt = new Account(100, zoe);
        matt.withdraw(50);
    }
}




