package com.lambdaschool.piggybank.controllers;

import com.lambdaschool.piggybank.models.Coin;

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
}
