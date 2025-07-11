package com.logisticscloudco.app.Print;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;

public class PrintSend {
    
    public void toIP(){
        try {
            String address = "";
            int port = 9100;
            InetAddress adddress = InetAddress.getByName(address);
            Socket socket = new Socket(adddress,port);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void toUSB(){
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);

        PrintService selectedPrinter = null;
        System.out.println("Available printers:");
        for (int i = 0; i < printServices.length; i++) {
            if(printServices[i].getName().contains("ZD620")){
                selectedPrinter = printServices[i];
                System.out.println("Found printer: " + selectedPrinter.getName());
            }
        }

        File pdfFile = new File("1.pdf");
        try (InputStream pdfStream = new FileInputStream(pdfFile)) {
            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            Doc doc = new SimpleDoc(pdfStream, flavor, null);
            DocPrintJob job = selectedPrinter.createPrintJob();
            job.print(doc, new HashPrintRequestAttributeSet());
            System.out.println("PDF sent to printer.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {


    }   
}
