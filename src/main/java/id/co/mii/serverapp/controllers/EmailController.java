package id.co.mii.serverapp.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.mii.serverapp.models.dto.request.EmailRequest;
import id.co.mii.serverapp.services.EmailService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/email")
@AllArgsConstructor
public class EmailController {

  private EmailService emailService;

  @PostMapping("/simple")
  public EmailRequest sendSimpleMessage(@RequestBody EmailRequest emailRequest) {
    return emailService.sendSimpleMessage(emailRequest);
  }

  @PostMapping("/attach")
  public EmailRequest sendMessageWithAttacment(@RequestBody EmailRequest emailRequest) {
    return emailService.sendMessageWithAttachment(emailRequest);
  }

  // @PostMapping("/template")
  // public EmailRequest sendMessageWithTemplate(@RequestBody EmailRequest
  // emailRequest) {
  // return emailService.sendMessageWithTemplate(emailRequest);
  // }

  @PostMapping("/optional")
  public EmailRequest sendMessageWithTextAttachTemplate(@RequestBody EmailRequest emailRequest) {
    return emailService.sendMessageWithTextAttachTemplate(emailRequest);
  }

}
