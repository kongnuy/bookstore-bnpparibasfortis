import { store } from "@/_stores";
import { useStoreState } from "zustand-x";

export const useAuth = () => {
  const [account, setAccount] = useStoreState(store.auth, "account");

  return { account, setAccount };
};
