import { storage } from "@/_helpers";
import { DashboardModel } from "@/_models";
import { authStore } from "@/_stores";

export const logOut = () => authStore.set("logOut");

export const updateAccountInfo = (account: any | undefined) =>
  authStore.set("updateAccountInfo", account);

export const loadAccountInfo = async () => {
  if (storage.hasToken()) {
    const model = DashboardModel.getInstance();
    const account = await model.getAccountInfo();

    updateAccountInfo(account);

    if (!account) {
      logOut();
      return;
    }

    return account;
  }
};
