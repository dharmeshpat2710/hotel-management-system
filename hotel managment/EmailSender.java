/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.project;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;
import java.io.File;

public class EmailSender {

public static boolean sendReceipt(String toEmail, String filePath) {

    final String fromEmail = "hotelgrandparadise17238@gmail.com";
    final String password = "klmmdrohyskdqvgn";

    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props,
        new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        }
    );

    try {
        Message message = new MimeMessage(session);

        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(
            Message.RecipientType.TO,
            InternetAddress.parse(toEmail)
        );

        message.setSubject("Hotel Grand Paradise - Booking Confirmation Receipt");

        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setContent(
            "<h2>Hotel Grand Paradise</h2>" +
            "<p>Dear " + toEmail + ",</p>" +
            "<p>Thank you for booking with us.</p>" +
            "<p>Your booking receipt is attached.</p>" +
            "<br>" +
            "<p>We look forward to welcoming you!</p>" +
            "<p><b>Hotel Grand Paradise</b></p>",
            "text/html"
        );

        MimeBodyPart filePart = new MimeBodyPart();
        filePart.attachFile(new File(filePath));

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(textPart);
        multipart.addBodyPart(filePart);

        message.setContent(multipart);

        Transport.send(message);
        return true;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
}