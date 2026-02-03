import type { IAccount, IAuthLogin, IAuthLoginResponse } from "@/_interfaces";
import { BaseService } from "./base";
import { Constants } from "@/_constants";

export class AuthService extends BaseService {
  private static _instance: AuthService;

  constructor() {
    if (AuthService._instance) {
      throw new Error(
        "Singleton classes can't be instantiated more than once. Please use AuthService.getInstance ",
      );
    }
    super();
    AuthService._instance = this;
  }

  public static getInstance(): AuthService {
    if (!AuthService._instance) {
      AuthService._instance = new AuthService();
    }
    return AuthService._instance;
  }

  /**
   * login
   */
  public async login(payload: IAuthLogin) {
    const result = await this.post<IAuthLoginResponse, IAuthLogin>(
      "/auth/login",
      payload,
    );

    if (result.data?.jwtToken) {
      this.saveLocal(Constants.JWT_TOKEN_KEY, result.data?.jwtToken);
      this.saveLocal(Constants.JWT_ACCOUNT_KEY, result.data);
    }

    return result;
  }

  /**
   * register
   */
  public async register(payload: any) {
    return await this.post<any, any>("/auth/register", payload);
  }

  /**
   * info
   */
  public async info() {
    return this.get<IAccount>("/auth/info");
  }
}
