package com.nt.user.microservice.service;

import com.nt.user.microservice.exceptions.InvalidRequestException;
import com.nt.user.microservice.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Service class responsible for sending emails and validating email parameters.
 */
@Service
public class EmailService {

  @Autowired
  private JavaMailSender javaMailSender;

  private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,4}$");


  /**
   * Sends an email to the specified recipients.
   *
   * @param from    the email address of the sender
   * @param subject the subject of the email
   * @param to      a list of recipient email addresses
   * @param text    the body of the email
   * @throws InvalidRequestException if any of the parameters are invalid
   */
  public void sendMail(String from, String subject, List<String> to, String text) {
    validateEmailParameters(subject, to, text);

    try {
      MimeMessage message = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message);
      helper.setFrom(from);
      helper.setSubject(subject);
      helper.setTo(to.toArray(new String[0]));
      helper.setText(text);
      System.out.println(text);
      javaMailSender.send(message);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Validates the email parameters including subject, recipient list, and body content.
   *
   * @param subject the subject of the email
   * @param to      a list of recipient email addresses
   * @param text    the body of the email
   * @throws InvalidRequestException if any of the parameters are invalid (e.g., empty subject, invalid email format)
   */
  private void validateEmailParameters(String subject, List<String> to, String text) {
    if (subject == null || subject.trim().isEmpty()) {
      throw new InvalidRequestException(Constants.SUBJECT_EMPTY_ERROR);
    }
    if (to == null || to.isEmpty()) {
      throw new InvalidRequestException(Constants.RECIPIENT_LIST_EMPTY_ERROR);
    }
    for (String recipient : to) {
      if (recipient == null || recipient.trim().isEmpty()) {
        throw new InvalidRequestException(Constants.RECIPIENT_EMAIL_EMPTY_ERROR + recipient);
      }
      if (!EMAIL_PATTERN.matcher(recipient).matches()) {
        throw new InvalidRequestException(Constants.INVALID_EMAIL_FORMAT_ERROR + recipient);
      }
    }
    if (text == null || text.trim().isEmpty()) {
      throw new InvalidRequestException(Constants.EMAIL_BODY_EMPTY_ERROR);
    }
  }
}