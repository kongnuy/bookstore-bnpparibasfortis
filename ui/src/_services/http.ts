import type { GenericResponse } from "@/_types";
import type { AxiosResponse } from "axios";
import { axiosInstance as http } from "@/_helpers";
import type { HttpInstance } from "@/_models";

export class HttpService {
  private static _instance: HttpService;

  constructor() {
    if (HttpService._instance) {
      throw new Error(
        "Singleton classes can't be instantiated more than once. Please use HttpService.getInstance ",
      );
    }
    HttpService._instance = this;
  }

  public static getInstance(): HttpService {
    if (!HttpService._instance) {
      HttpService._instance = new HttpService();
    }
    return HttpService._instance;
  }

  public async execute<D, P>(
    httpInstance: HttpInstance<D, P>,
  ): Promise<HttpInstance<D, P>> {
    const { path, payload, method } = httpInstance;
    let result: AxiosResponse<D, P> | undefined;

    try {
      switch (method) {
        case "GET":
          result = await http.get<D, AxiosResponse<D, P>, P>(path);
          break;
        case "POST":
          result = await http.post<D, AxiosResponse<D, P>, P>(path, payload);
          break;
        case "PUT":
          result = await http.put<D, AxiosResponse<D, P>, P>(path, payload);
          break;
        case "PATCH":
          result = await http.patch<D, AxiosResponse<D, P>, P>(path, payload);
          break;
        case "DELETE":
          result = await http.delete<D, AxiosResponse<D, P>, P>(path);
          break;
        default:
          throw new Error("Http method not supported");
      }
    } catch (error: any) {
      return httpInstance.withError(error);
    }

    return httpInstance.withResponse(
      result as AxiosResponse<GenericResponse<D>, P>,
    );
  }
}
