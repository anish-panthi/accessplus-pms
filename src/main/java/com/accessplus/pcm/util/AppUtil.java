package com.accessplus.pcm.util;

import java.util.UUID;

/**
 * @author Anish Panthi
 */
public class AppUtil {

  /**
   * Generates unique UUID
   *
   * @return <code>String</code> of generated UUID
   */
  public static String generateUuid() {
    return UUID.randomUUID().toString();
  }
}
