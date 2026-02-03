import { Constants } from "@/_constants";
import { empty, log } from "./utils";
import { isNullOrEmpty } from "./validator";

const set = (key: string, value: any, engine = "local") => {
  try {
    const jsonValue = typeof value === "string" ? value : JSON.stringify(value);
    getStorage(engine).setItem(key, jsonValue);
  } catch (e) {
    log(e);
  }
};

const getStorage = (engine: string) => {
  if (engine === "session") {
    return sessionStorage;
  }
  return localStorage;
};

const get = (key: string, engine = "local") => {
  try {
    const value = getStorage(engine).getItem(key);
    try {
      if (!empty(value) && value != null && value.startsWith("{")) {
        return JSON.parse(value);
      }
    } catch (error) {}
    return value;
  } catch (e) {
    log(e);
  }
};

const has = (key: string, engine = "local") => {
  return !isNullOrEmpty(getStorage(engine).getItem(key));
};

const hasToken = () => {
  return has(Constants.JWT_TOKEN_KEY);
};

const remove = (key: string, engine = "local") => {
  try {
    getStorage(engine).removeItemItem(key);
  } catch (e) {
    log(e);
  }
};

const clear = () => {
  localStorage.clear();
  sessionStorage.clear();
};

const logOut = () => {
  localStorage.removeItem(Constants.JWT_TOKEN_KEY);
  sessionStorage.removeItem(Constants.JWT_TOKEN_KEY);
};

export const storage = { set, get, clear, logOut, remove, has, hasToken };
