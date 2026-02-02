package net.highfi.bnpparibasfortis.bookstore.utils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class DateUtils {

  private DateUtils() {
  }

  public static final DateTimeFormatter YYYY_MM_DD_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  public static String format(LocalDate date) {
    if (date != null) {
      return date.format(YYYY_MM_DD_FORMATTER);
    }
    return null;
  }

  public static String format(Timestamp date) {
    if (date != null) {
      return date.toLocalDateTime().format(YYYY_MM_DD_FORMATTER);
    }
    return null;
  }
}
