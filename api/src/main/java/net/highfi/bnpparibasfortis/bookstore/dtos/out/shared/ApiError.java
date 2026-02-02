package net.highfi.bnpparibasfortis.bookstore.dtos.out.shared;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ApiError extends ApiMessage {
  protected String internalMessage;
}
