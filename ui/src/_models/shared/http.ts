import type { AxiosError, AxiosResponse } from "axios";
import { Constants } from "@/_constants";
import type { IMessage, GenericResponse } from "@/_types";

const {
  API_REQUESTS_DEFAULTS: { DISPLAY_ERROR_KEYS, ERR_NETWORK },
} = Constants;

export class HttpInstance<D, P> {
  method: string;
  axiosResponse: AxiosResponse<GenericResponse<D>, P> | undefined;
  axiosError: AxiosError | undefined;
  messages: IMessage[] = [];
  payload: P | undefined;
  path: string;

  constructor(path: string, payload?: P, method?: string) {
    this.path = path;
    this.payload = payload;
    this.method = method ?? "GET";
  }

  public get data(): D | undefined {
    return this.axiosResponse?.data?.data;
  }

  public get errorMessage(): string | undefined {
    const msg = this.messages.find(
      (m) => DISPLAY_ERROR_KEYS.indexOf(m.key) !== -1,
    );
    return msg?.content;
  }

  public withResponse(
    axiosResponse: AxiosResponse<GenericResponse<D>, P> | undefined,
  ) {
    this.axiosResponse = axiosResponse;

    if (Array.isArray(axiosResponse?.data?.messages)) {
      axiosResponse?.data?.messages.forEach((m: any) => this.messages.push(m));
    }

    return this;
  }

  public withError(axiosError: AxiosError | undefined) {
    this.axiosError = axiosError;

    if (axiosError?.code === "ERR_NETWORK") {
      this.messages.push(ERR_NETWORK);
    } else if (Array.isArray((axiosError?.response?.data as any)?.messages)) {
      (axiosError?.response?.data as any)?.messages.forEach((m: any) =>
        this.messages.push(m),
      );
    }

    return this;
  }
}
