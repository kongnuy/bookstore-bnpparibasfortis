import { Constants } from "@/_constants";
import type { Account } from "@/_entities";
import { goTo, log, storage } from "@/_helpers";
import { createStore } from "zustand-x";

export type AuthStoreState = {
  jwtToken: string | undefined;
  account: Account | undefined;
};

export const authStore = createStore<AuthStoreState>(
  {
    jwtToken: undefined,
    account: undefined,
  },
  {
    name: "auth",
    devtools: true,
    mutative: true,
    persist: true,
  },
).extendActions(({ set }) => ({
  updateAccountInfo: (data: any) => {
    log(data, "== updateAccountInfo == ");
    return set("state", (state: any) => {
      state.account = data;
      state.jwtToken = storage.get(Constants.JWT_TOKEN_KEY);
    });
  },
  logOut: () =>
    set("state", (state: any) => {
      state.account = undefined;
      state.jwtToken = undefined;
      storage.logOut();
      goTo(Constants.AUTH_LOGIN_URL);
    }),
}));
