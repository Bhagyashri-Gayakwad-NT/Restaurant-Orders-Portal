package com.nt.user.microservice.service;

import com.nt.user.microservice.dto.EmailRequestDTO;
import com.nt.user.microservice.exceptions.InvalidRequestException;
import com.nt.user.microservice.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Service class responsible for sending emails and validating email parameters.
 */
@Service
public class EmailService {

  /**
   * JavaMailSender used to send emails.
   * This is automatically injected by Spring's Dependency Injection framework.
   */
  @Autowired
  private JavaMailSender javaMailSender;

  /**
   * Regular expression pattern for validating email addresses.
   * This pattern ensures that the email follows the format: local-part@domain.extension.
   */
  private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,4}$");

  /**
   * Sends an email to the specified recipients.
   *
   * @param from    the email address of the sender
   * @param emailInDTO contains subject and body of the email
   * @param to      a list of recipient email addresses
   * @throws InvalidRequestException if any of the parameters are invalid
   */
  public void sendMail(final String from, @Valid final EmailRequestDTO emailInDTO, final List<String> to) {
    validateRecipientList(to);

    try {
      MimeMessage message = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message);
      helper.setFrom(from);
      helper.setSubject(emailInDTO.getSubject());
      helper.setTo(to.toArray(new String[0]));
      helper.setText(emailInDTO.getText());
      System.out.println(emailInDTO.getText());
      javaMailSender.send(message);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Validates the recipient list.
   *
   * @param to a list of recipient email addresses
   * @throws InvalidRequestException if any of the parameters are invalid
   */
  private void validateRecipientList(final List<String> to) {
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
  }
}
