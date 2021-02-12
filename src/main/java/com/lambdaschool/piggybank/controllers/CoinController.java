package com.lambdaschool.piggybank.controllers;

import com.lambdaschool.piggybank.models.Coin;
import com.lambdaschool.piggybank.repositories.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CoinController
{
    @Autowired
    CoinRepository coinRepository;

    //Routes
    // Prints the contents of the Piggy Bank to the console
    // Returns HTTP status OK
    //http://localhost:2019/total
    @GetMapping(value="/total", produces = {"application/json"})
    public ResponseEntity<?> printContents()
    {
        List<Coin> coinList = new ArrayList<>();
        coinRepository.findAll().iterator().forEachRemaining(coinList::add);
        // create accumulator for total value
        double value = 0;
        for (Coin c : coinList)
        {
            String formattedData = HelperFunctions.formatData(c);
            System.out.println(formattedData);
            value += c.getQuantity() * c.getValue();
        }
        System.out.println("The piggy bank holds " + value);


        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Allow user to remove coins from piggy bank
    // uses route param for amount
    // prevents user from removing more than is present
    // removes and update the appropriate fields
    // logs updated amounts, using $ for dollars
    // if money cannnot be removed, log Money not available

    // Returns HTTP status OK
    //http://localhost:2019/money/{amount}
    @GetMapping(value="/money/{amount}", produces = {"application/json"})
    public ResponseEntity<?> removeMoney(@PathVariable double amount)
    {
        List<Coin> piggyBank = new ArrayList<>();
        coinRepository.findAll().iterator().forEachRemaining(piggyBank::add);

        // check to see if amount exceed funds
        boolean isEnoughFunds = HelperFunctions.checkFunds(amount, piggyBank);
        if (!isEnoughFunds)
        {
            System.out.println("Money not available");

        }
        else
        {
            System.out.println("Working on your transaction...");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
