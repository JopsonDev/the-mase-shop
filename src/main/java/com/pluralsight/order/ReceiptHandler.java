package com.pluralsight.order;

import com.pluralsight.menu.IChargable;
import com.pluralsight.ui.UserInterface;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReceiptHandler {

    public void fileReceipt(List<IChargable> order, int x){
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
            writer.write("Order ID: " + x);
            writer.write(dateTime + "\n");


            writer.write(order.toString());
            writer.newLine();

            writer.close();
        } catch (Exception e) {
            System.out.println("failed to add transaction");
        }
    }
}
