import type { IAccount, IAuthLogin, IAuthLoginResponse } from "@/_interfaces";
import { BaseService } from "./base";
import { Constants } from "@/_constants";

export class BookService extends BaseService {
  private static _instance: BookService;

  constructor() {
    if (BookService._instance) {
      throw new Error(
        "Singleton classes can't be instantiated more than once. Please use BookService.getInstance ",
      );
    }
    super();
    BookService._instance = this;
  }

  public static getInstance(): BookService {
    if (!BookService._instance) {
      BookService._instance = new BookService();
    }
    return BookService._instance;
  }

  /**
   * info
   */
  public async getBooks() {
    return this.get<any>("/books");
  }
}
