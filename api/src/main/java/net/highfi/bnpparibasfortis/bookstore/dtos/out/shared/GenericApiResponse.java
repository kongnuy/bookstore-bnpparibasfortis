package net.highfi.bnpparibasfortis.bookstore.dtos.out.shared;

import java.util.ArrayList;
import java.util.List;

import net.highfi.bnpparibasfortis.bookstore.constants.ApiResponseStatus;
import lombok.Data;

@Data
public class GenericApiResponse<T> {
  protected T data;
  protected ApiResponseStatus status;
  protected List<ApiMessage> messages;

  public GenericApiResponse(T data) {
    this.data = data;
    this.status = ApiResponseStatus.SUCCESS;
    this.messages = new ArrayList<>();
  }
}
