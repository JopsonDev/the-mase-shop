package com.pluralsight.order;

import com.pluralsight.menu.IChargable;
import com.pluralsight.ui.UserInterface;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class ReceiptHandler {


    //Creates a folder if one doesn't already exist then adds a file
    public void fileReceipt(Order order, int x){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String dateTime = LocalDateTime.now().format(formatter);

        File folder = new File("Receipts");

        if (!folder.exists()) {
            folder.mkdir();
        }
        File receiptFile = new File("Receipts", dateTime + ".csv");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(receiptFile, true));

            writer.write("****RECEIPT****\n");
            writer.write("Order ID: " + order.getIdNumber() + "\n");
            writer.write(dateTime + "\n");
            writer.write(order.getItems().stream().map(IChargable::toString).collect(Collectors.joining("\n")));

            writer.close();
        } catch (Exception e) {
            System.out.println("failed to add transaction");
        }
    }
}
