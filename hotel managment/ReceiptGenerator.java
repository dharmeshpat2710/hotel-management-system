package com.mycompany.project;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.*;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.element.Image;
import com.itextpdf.io.image.ImageDataFactory;

public class ReceiptGenerator {

    public static String generateReceipt(
        String name,
        String roomType,
        String plan,
        String checkIn,
        String checkOut,
        int guests,
        int total
) {
    try {
        String fileName = "receipt_" + System.currentTimeMillis() + ".pdf";

        PdfWriter writer = new PdfWriter(fileName);
        PdfDocument pdf = new PdfDocument(writer);
       Document doc = new Document(pdf);

        com.itextpdf.kernel.colors.Color blue =
                new com.itextpdf.kernel.colors.DeviceRgb(20, 60, 120);

        Table headerTop = new Table(new float[]{100, 300});
        headerTop.setWidth(com.itextpdf.layout.properties.UnitValue.createPercentValue(100));

        java.net.URL logoUrl = ReceiptGenerator.class.getResource("/images/logofinal.png");

        if (logoUrl != null) {
            Image logo = new Image(ImageDataFactory.create(logoUrl));
            logo.setWidth(80);
            headerTop.addCell(new Cell().add(logo).setBorder(null));
        } else {
            headerTop.addCell(new Cell().add(new Paragraph("")).setBorder(null));
        }

        headerTop.addCell(new Cell()
            .add(new Paragraph("HOTEL GRAND PARADISE")
                .setBold()
                .setFontSize(22)
                .setFontColor(blue))
            .setVerticalAlignment(com.itextpdf.layout.properties.VerticalAlignment.MIDDLE)
            .setBorder(null)
        );

        doc.add(headerTop);
        
        doc.add(new Paragraph("\n"));
        
        doc.add(new Paragraph("📍 Surat | 📞 7986785678 | ✉ HotelGrandParadise@gmail.com")
                .setFontSize(10)
                .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.CENTER));

        doc.add(new Paragraph("------------------------------------------------------------")
                .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.CENTER));

        doc.add(new Paragraph("\n"));

        Table header = new Table(new float[]{1, 1});
        header.setWidth(com.itextpdf.layout.properties.UnitValue.createPercentValue(100));

        header.addCell(new Cell()
                .add(new Paragraph("BOOKING DETAILS")
                        .setBold()
                        .setFontColor(blue)
                        .setFontSize(14))
                .setBorder(null));

        header.addCell(new Cell()
                .add(new Paragraph("RECEIPT")
                        .setBold()
                        .setFontColor(blue)
                        .setFontSize(18))
                .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.RIGHT)
                .setBorder(null));

        doc.add(header);

        doc.add(new Paragraph("\n"));

        doc.add(new Paragraph("Booked By: " + name));
        doc.add(new Paragraph("Room: " + roomType));
        doc.add(new Paragraph("Plan: " + plan));
        doc.add(new Paragraph("Guests: " + guests));
        doc.add(new Paragraph("Check-in: " + checkIn));
        doc.add(new Paragraph("Check-out: " + checkOut));

        doc.add(new Paragraph("\n"));

        Table table = new Table(new float[]{100, 200, 100, 100});
        table.setWidth(com.itextpdf.layout.properties.UnitValue.createPercentValue(100));

        table.addHeaderCell(new Cell().add(new Paragraph("Qty").setBold())
                .setBackgroundColor(blue)
                .setFontColor(com.itextpdf.kernel.colors.ColorConstants.WHITE));

        table.addHeaderCell(new Cell().add(new Paragraph("Description").setBold())
                .setBackgroundColor(blue)
                .setFontColor(com.itextpdf.kernel.colors.ColorConstants.WHITE));

        table.addHeaderCell(new Cell().add(new Paragraph("Unit Price").setBold())
                .setBackgroundColor(blue)
                .setFontColor(com.itextpdf.kernel.colors.ColorConstants.WHITE));

        table.addHeaderCell(new Cell().add(new Paragraph("Amount").setBold())
                .setBackgroundColor(blue)
                .setFontColor(com.itextpdf.kernel.colors.ColorConstants.WHITE));

        table.addCell("1");
        table.addCell("Room Booking (" + roomType + ")");
        table.addCell("₹" + total);
        table.addCell("₹" + total);

        doc.add(table);

        doc.add(new Paragraph("\n"));

        doc.add(new Paragraph("------------------------------------------------------------"));

        doc.add(new Paragraph("\n"));

        double tax = total * 0.075;
        double finalTotal = total + tax;

        Table summary = new Table(new float[]{300, 100});
        summary.setWidth(com.itextpdf.layout.properties.UnitValue.createPercentValue(100));

        summary.addCell(new Cell().add(new Paragraph("Subtotal")).setBorder(null));
        summary.addCell(new Cell().add(new Paragraph("₹" + total))
                .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.RIGHT)
                .setBorder(null));

        summary.addCell(new Cell().add(new Paragraph("Tax (7.5%)")).setBorder(null));
        summary.addCell(new Cell().add(new Paragraph("₹" + (int) tax))
                .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.RIGHT)
                .setBorder(null));

        summary.addCell(new Cell()
                .add(new Paragraph("TOTAL")
                        .setBold()
                        .setFontColor(blue)));

        summary.addCell(new Cell()
                .add(new Paragraph("₹" + (int) finalTotal)
                        .setBold()
                        .setFontColor(blue))
                .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.RIGHT));

        doc.add(summary);

        doc.add(new Paragraph("\n"));

        doc.add(new Paragraph("Thank you for staying with us!")
                .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.CENTER));

        doc.close();

        return fileName;

    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
    }
}