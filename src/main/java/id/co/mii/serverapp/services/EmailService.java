package id.co.mii.serverapp.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Scanner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import id.co.mii.serverapp.models.dto.request.EmailRequest;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailService {

  private JavaMailSender mailSender;

  public EmailRequest sendSimpleMessage(EmailRequest emailRequest) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(emailRequest.getTo());
    message.setSubject(emailRequest.getSubject());
    message.setText(emailRequest.getText());
    mailSender.send(message);
    return emailRequest;
  }

  public EmailRequest sendMessageWithAttachment(EmailRequest emailRequest) {

    try {
      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true);

      helper.setTo(emailRequest.getTo());
      helper.setSubject(emailRequest.getSubject());
      helper.setText(emailRequest.getText(), true);

      FileSystemResource file = new FileSystemResource(new File(emailRequest.getAttach()));

      helper.addAttachment(file.getFilename(), file);

      mailSender.send(message);

    } catch (Exception e) {
      throw new IllegalStateException("Email false to send!!!");
    }

    return emailRequest;
  }

  public EmailRequest sendMessageWithTemplate(EmailRequest emailRequest) {

    try {
      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true);

      helper.setTo(emailRequest.getTo());
      helper.setSubject(emailRequest.getSubject());

      FileSystemResource htmlFile = new FileSystemResource(new File(emailRequest.getText()));
      Scanner scanner = new Scanner(htmlFile.getFile());
      scanner.useDelimiter("\\Z");
      String htmlBody = scanner.next();
      scanner.close();

      helper.setText(htmlBody, true);

      mailSender.send(message);

    } catch (Exception e) {
      throw new IllegalStateException("Email false to send!!!");
    }

    return emailRequest;

  }

  public EmailRequest sendMessageWithTextAttachTemplate(EmailRequest emailRequest) {
    try {
      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true);

      helper.setTo(emailRequest.getTo());
      helper.setSubject(emailRequest.getSubject());

      if (emailRequest.getText() != null) {

        Optional<FileSystemResource> htmlFile = Optional
            .ofNullable(new FileSystemResource(new File(emailRequest.getText())));

        htmlFile.ifPresent(file -> {

          try {

            Scanner scanner = new Scanner(file.getFile());
            scanner.useDelimiter("\\Z");
            String htmlBody = scanner.next();
            scanner.close();
            helper.setText(htmlBody, true);

          } catch (Exception e) {

            if (e instanceof FileNotFoundException) {
              try {
                helper.setText(emailRequest.getText() != null ? emailRequest.getText() : "");
              } catch (MessagingException e1) {
                e1.printStackTrace();
              }
            }

          }
        });

      } else {
        helper.setText("");
      }

      if (emailRequest.getAttach() != null) {

        Optional<FileSystemResource> attachFile = Optional
            .ofNullable(new FileSystemResource(new File(emailRequest.getAttach())));

        attachFile.ifPresent(file -> {
          try {

            System.out.println(file.exists());
            if (file.exists()) {
              helper.addAttachment(file.getFilename(), file);
            }

          } catch (Exception e) {

            throw new IllegalStateException("Email false to send!");

          }

        });

      }

      mailSender.send(message);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return emailRequest;
  }

}
