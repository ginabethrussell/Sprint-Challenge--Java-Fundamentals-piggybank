package com.lambdaschool.piggybank.controllers;

import com.lambdaschool.piggybank.models.Coin;
import com.lambdaschool.piggybank.repositories.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
