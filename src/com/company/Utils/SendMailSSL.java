//package com.company.Utils;
//import javax.mail.Message;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import java.util.Date;
//import java.util.Properties;
//public class SendMailSSL {
//
//    public SendMailSSL(String mesaj){
//        try{
//            String host ="smtp.gmail.com" ;
//            String user = "user;
//            String pass = "pass";
//            String to = "george.mihaila506@gmail.com";
//            String from = "catedramap@gmail.com";
//            String subject = "NOTE SI TEME";
//            String messageText = "asd";
//            boolean sessionDebug = false;
//
//            Properties props = System.getProperties();
//
//            props.put("mail.smtp.starttls.enable", "true");
//            props.put("mail.smtp.host", host);
//            props.put("mail.smtp.port", "587");
//            props.put("mail.smtp.auth", "true");
//            props.put("mail.smtp.starttls.required", "true");
//
//            //java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
//            Session mailSession = Session.getDefaultInstance(props, null);
//            mailSession.setDebug(sessionDebug);
//            Message msg = new MimeMessage(mailSession);
//            msg.setFrom(new InternetAddress(from));
//            InternetAddress[] address = {new InternetAddress(to)};
//            msg.setRecipients(Message.RecipientType.TO, address);
//            msg.setSubject(subject); msg.setSentDate(new Date());
//            msg.setText(mesaj);
//
//            Transport transport=mailSession.getTransport("smtp");
//            transport.connect(host, user, pass);
//            transport.sendMessage(msg, msg.getAllRecipients());
//            transport.close();
//            System.out.println("message send successfully");
//        }catch(Exception ex)
//        {
//            System.out.println("here");
//            System.out.println(ex);
//        }
//
//    }
//}