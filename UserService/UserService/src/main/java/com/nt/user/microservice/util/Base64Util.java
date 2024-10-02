package com.nt.user.microservice.util;

import java.util.Base64;

/**
 * Utility class for encoding and decoding data using Base64.
 * Provides methods to encode data to Base64 and decode Base64 data back to its original form.
 */
public class Base64Util {

  /**
   * Encodes the provided data into a Base64 encoded string.
   *
   * @param data the string to be encoded
   * @return the Base64 encoded representation of the input string
   */
  public static String encode(final String data) {
    return Base64.getEncoder().encodeToString(data.getBytes());
  }

  /**
   * Decodes the provided Base64 encoded string back to its original form.
   *
   * @param data the Base64 encoded string to be decoded
   * @return the decoded string
   */
  public static String decode(final String data) {
    return new String(Base64.getDecoder().decode(data));
  }
}
