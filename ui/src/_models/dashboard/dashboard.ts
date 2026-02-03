import { AuthService } from "@/_services";
import { BaseModel } from "../base";
import { Account } from "@/_entities";

export class DashboardModel extends BaseModel {
  private static _instance: DashboardModel;
  private _authService: AuthService = AuthService.getInstance();

  constructor() {
    if (DashboardModel._instance) {
      throw new Error(
        "Singleton classes can't be instantiated more than once. Please use DashboardModel.getInstance ",
      );
    }
    super();
    DashboardModel._instance = this;
  }

  public static getInstance(): DashboardModel {
    if (!DashboardModel._instance) {
      DashboardModel._instance = new DashboardModel();
    }
    return DashboardModel._instance;
  }

  /**
   * getAccountInfo
   */
  public async getAccountInfo() {
    const result = await this._authService.info();
    if (result.data?.id) {
      return new Account(result.data);
    }
  }
}
