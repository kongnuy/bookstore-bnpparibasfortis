package net.highfi.bnpparibasfortis.bookstore.dtos.out.shared;

import net.highfi.bnpparibasfortis.bookstore.constants.ApiMessageTypes;
import lombok.Data;

@Data
public class ApiMessage {
  protected String code;
  protected String message;
  protected ApiMessageTypes type;
}
