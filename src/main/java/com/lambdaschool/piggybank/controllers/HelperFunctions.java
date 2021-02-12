package com.lambdaschool.piggybank.controllers;

import com.lambdaschool.piggybank.models.Coin;

import java.util.List;

public class HelperFunctions
{
    public static String formatData(Coin c){
        String rtnString = "";
        // print quantity
        int quantity = c.getQuantity();
        rtnString += quantity;
        rtnString += " ";
        // check to see if quantity is greater than 1
        // use name or name plural
        if (quantity > 1){
            rtnString += c.getNameplural();
        }
        else
        {
            rtnString += c.getName();
        }
        return rtnString;
    }

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
}
