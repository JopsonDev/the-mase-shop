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
        Order receipt = new Order(x, order);

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
            System.out.println("Order ID: " + x);
            writer.write(dateTime + "\n");

            System.out.println("****RECEIPT****");
            System.out.println("Order ID: " + x);
            System.out.println(dateTime);

            writer.write(order.toString());
            writer.newLine();

            writer.write(receipt.toString());
            writer.newLine();
            writer.close();

            System.out.println(receipt);

        } catch (Exception e) {
            System.out.println("failed to add transaction");
        }
    }
}
