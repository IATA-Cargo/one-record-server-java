package org.iata.util;

import java.util.Random;
import java.util.UUID;

public class Utils {

  public static int increment(int index, int value) {
    return index + value;
  }

  public static String generateUuid() {
    return UUID.randomUUID().toString().replace("-", "");
  }

  /**
   * Generate 6 digit random Number from 0 to 999999
   *
   * @return 6 digit random Number from 0 to 999999
   */
  public static String getRandomNumberString() {
    Random rnd = new Random();
    int number = rnd.nextInt(999999);

    // this will convert any number sequence into 6 character.
    return String.format("%06d", number);
  }

}
