import { AuthService } from "@/_services";
import { BaseModel } from "../base";
import { goTo, showError, showSuccess, sleep } from "@/_helpers";

export class AuthModel extends BaseModel {
  public data: any;
  private static _instance: AuthModel;
  private _service: AuthService = AuthService.getInstance();

  constructor() {
    if (AuthModel._instance) {
      throw new Error(
        "Singleton classes can't be instantiated more than once. Please use AuthModel.getInstance ",
      );
    }
    super();
    AuthModel._instance = this;
  }

  public static getInstance(): AuthModel {
    if (!AuthModel._instance) {
      AuthModel._instance = new AuthModel();
    }
    return AuthModel._instance;
  }

  public async logIn(identifier: string, password: string) {
    const result = await this._service.login({ identifier, password });

    if (result.errorMessage) {
      showError(result.errorMessage);
      return;
    } else if (result.axiosError?.message) {
      showError(result.axiosError?.message);
      return;
    }

    if (result.data?.jwtToken) {
      goTo("/");
    }
  }

  public async register(payload: any) {
    const result = await this._service.register(payload);

    if (result.errorMessage) {
      showError(result.errorMessage);
      return;
    } else if (result.axiosError?.message) {
      showError(result.axiosError?.message);
      return;
    }

    if (result.data?.id) {
      showSuccess(
        "Account created successfully ! You will be redirected to login page",
      );
      await sleep(2000);
      goTo("/login");
    }
  }
}
