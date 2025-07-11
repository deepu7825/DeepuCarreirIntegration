package com.logisticscloudco.app;

public class CreatePDF {
    public static void run(String base64) {
        /*if (args == null || args.length < 2) {
            System.out.println("Usage: java CreatePDF <base64_string> <UUID>");
            return;
    }
        if (args.length < 3 || args[0] == null || args[0].isEmpty()) {
            System.out.println("No base64 string provided.");
            return;
        }*/

        String base64EncodedPdf = base64;        //String base64EncodedPdf = args[0];
        String UUID = "1";

        // Decode the Base64 string
        byte[] pdfBytes = java.util.Base64.getDecoder().decode(base64EncodedPdf);

        // Save the decoded bytes as a PDF file
        try (java.io.FileOutputStream fos = new java.io.FileOutputStream(UUID + ".pdf")) {
            fos.write(pdfBytes);
            System.out.println("PDF file saved as " + UUID + ".pdf");
        } catch (java.io.IOException e) {
            System.err.println("Error saving PDF file: " + e.getMessage());
        }
    }
}
