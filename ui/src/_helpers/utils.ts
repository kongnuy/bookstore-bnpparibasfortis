import { clsx, type ClassValue } from "clsx";
import { toast } from "sonner";
import { Constants } from "@/_constants";
import { twMerge } from "tailwind-merge";
import { storage } from "./storage";
import type { KeyGetterFunction } from "@/_types";

export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs));
}

export const isEnv = (env: string) => {
  return Constants.ENV !== undefined && Constants.ENV === env;
};

export const isDev = () => {
  return isEnv("dev");
};

const isError = (level: string) => {
  return level === "error";
};

const isWarm = (level: string) => {
  return level === "warm";
};
export const empty = (value: any) => {
  return value === "" || value === null || value === undefined;
};

const isInfo = (level: string) => {
  return level === "info";
};

export const toString = (data: any) => {
  return typeof data === "string" ? data : JSON.stringify(data);
};

export const withPrefix = (prefix: string, data: string) =>
  empty(prefix) ? data : prefix + data;

export const logError = (data: any, prefix = "") => log(data, prefix, "error");

export const log = (data: any, prefix = "", level = "") => {
  if (isDev()) {
    if (isError(level)) {
      console.error(prefix, data);
    } else if (isWarm(level)) {
      console.warn(prefix, data);
    } else if (isInfo(level)) {
      console.info(prefix, data);
    } else {
      console.log(prefix, data);
    }
  }
};

export const sleep = (time: number | undefined) =>
  new Promise((resolve) => setTimeout(() => resolve(true), time));

export const transformRequest = (jsonData: any = {}) =>
  Object.entries(jsonData)
    .map((x: any) => `${encodeURIComponent(x[0])}=${encodeURIComponent(x[1])}`)
    .join("&");

export const uiGravatar = (
  name: string,
  bg = "0D8ABC",
  color = "fff",
  size = 128,
  bold = true,
) =>
  `${Constants.UI_GRAVATAR_URL}?name=${name}&background=${bg}&color=${color}&size=${size}&bold=${bold}`;

export const pascalize = (phrase: string) => {
  return phrase
    .toLowerCase()
    .split(" ")
    .map((word) => word.charAt(0).toUpperCase() + word.slice(1))
    .join(" ");
};

export const getFirstLetters = (phrase?: string) => {
  if (!phrase) {
    return "";
  }
  return phrase
    .toLowerCase()
    .split(" ")
    .map((word) => word.charAt(0).toUpperCase())
    .join("");
};

export const goTo = (url: string, replace: boolean = true) =>
  replace ? window.location.replace(url) : window.location.assign(url);

export const getToken = () => storage.get(Constants.JWT_TOKEN_KEY);

export const showError = (message: string, title?: string) =>
  toast.error(title, {
    duration: 3000,
    position: "top-right",
    description: message,
    classNames: {
      icon: "!text-destructive",
      description: "!text-destructive",
    },
  });

export const showSuccess = (message: string, title?: string) =>
  toast.error(title, {
    duration: 3000,
    position: "top-right",
    description: message,
    classNames: {
      icon: "!text-success",
      description: "!text-success",
    },
  });

export function groupBy<T>(list: T[], keyGetter: KeyGetterFunction) {
  const map = new Map<string, Array<T>>();
  list.forEach((item) => {
    const key = keyGetter(item);
    const collection = map.get(key);
    if (!collection) {
      map.set(key, [item]);
    } else {
      collection.push(item);
    }
  });
  return map;
}

export const extractServerErrorMessage = (messages: any) => {
  if (Array.isArray(messages) && messages.length > 0) {
    const msg = messages.find((m: { key: string }) => m.key === "server_error");
    if (msg) {
      return msg.content;
    }
  }
  return "";
};
