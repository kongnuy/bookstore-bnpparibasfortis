package net.highfi.bnpparibasfortis.bookstore.dtos.in.shared;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseSearchParams {

  protected Integer offset;

  protected Integer limit;

  protected String search;

  public static BaseSearchParams fromFindAll(Integer offset, Integer limit, String search) {
    return new BaseSearchParams(offset, limit, search);
  }
}
