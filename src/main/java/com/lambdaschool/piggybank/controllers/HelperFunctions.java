package com.lambdaschool.piggybank.controllers;

import com.lambdaschool.piggybank.models.Coin;
import java.util.List;

public class HelperFunctions
{
    // This function returns data formatted for total get request
    public static String formatData(Coin c)
    {
        String rtnString = "";
        int quantity = c.getQuantity();
        rtnString += quantity;
        rtnString += " ";
        if (quantity > 1){
            rtnString += c.getNameplural();
        }
        else
        {
            rtnString += c.getName();
        }
        return rtnString;
    }

    // This function checks to see if there are enough funds for withdrawal
    public static boolean checkFunds(double amount, List<Coin> piggyBank)
    {
        double totalFunds = 0;
        for (Coin c : piggyBank)
        {
            totalFunds += c.getQuantity() * c.getValue();
        }

        if (amount < totalFunds)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    // This function removes money from the piggy bank
    public static void removeMoney(double amount, List<Coin> piggyBank)
    {
        // convert money to dollar and change amount variables
        int dollarAmount = (int) amount;
        double change = amount - dollarAmount;

        if (dollarAmount > 0)
        {
            change += removeDollars(dollarAmount, piggyBank);
        }

        if (change > 0)
        {
            removeChange(change, piggyBank);
        }
    }

    // This function removes whole dollars from piggy bank and returns any
    // additional dollars still to be found as change
    public static double removeDollars(int dollars,  List<Coin> piggyBank)
    {
        double changeAmount = 0;
        for (Coin c : piggyBank)
        {
            if (c.getName().equals("Dollar"))
            {
                if (dollars <= c.getQuantity())
                {
                    c.setQuantity((c.getQuantity() - dollars));
                    dollars = 0;
                }
                else if (dollars > c.getQuantity())
                {
                    dollars -= c.getQuantity();
                    c.setQuantity(0);
                }
            }
        }
        //convert any remaining dollars to change
        if (dollars > 0)
        {
            changeAmount +=  dollars * 1.0;
        }
        return changeAmount;
    }

    // This function removes remaining change from piggy bank withdrawal
    public static void removeChange(double change,  List<Coin> piggyBank)
    {
       // convert change into cents
        change = Math.round(change * 100);
        if (change > 0)
        {
            piggyBank.sort((c1, c2) -> (int) (c2.getValue() - c1.getValue()));
            for (Coin c : piggyBank)
            {
                if (c.getName().equals("Quarter"))
                {
                    while (change >= 25 && c.getQuantity() > 0)
                    {
                        c.setQuantity((c.getQuantity() - 1));
                        change -= 25;
                    }
                }
                if (c.getName().equals("Dime"))
                {
                    while (change >= 10 && c.getQuantity() > 0)
                    {
                        c.setQuantity((c.getQuantity() - 1));
                        change -= 10;
                    }
                }
                if (c.getName().equals("Nickel"))
                {
                    while (change >= 5 && c.getQuantity() > 0)
                    {
                        c.setQuantity((c.getQuantity() - 1));
                        change -= 5;
                    }
                }
                if (c.getName().equals("Penny"))
                {
                    while (change >= 1 && c.getQuantity() > 0)
                    {
                        c.setQuantity((c.getQuantity() - 1));
                        change -= 1;
                    }
                }
            }
        }
    }

    // This function formats the log for withdrawals
    public static String formatWithdrawal(Coin c)
    {
        String withdrawalString = "";
        int quantity = c.getQuantity();
        if (c.getName().equals("Dollar"))
        {
            withdrawalString += "$";
            withdrawalString += quantity;
        }
        else
        {
            withdrawalString += quantity;
            withdrawalString += " ";
            if (quantity > 1){
                withdrawalString += c.getNameplural();
            }
            else
            {
                withdrawalString += c.getName();
            }
        }
        return withdrawalString;
    }
}
