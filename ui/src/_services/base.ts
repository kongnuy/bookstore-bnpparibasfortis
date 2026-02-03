import { http, storage } from "@/_helpers";
import { HttpService } from "./http";
import { HttpInstance } from "@/_models";

export abstract class BaseService {
  protected get http(): HttpService {
    return HttpService.getInstance();
  }

  protected async get<D>(path: string) {
    return this.http.execute<D, any>(new HttpInstance<D, any>(path));
  }

  protected async post<D, P>(path: string, payload: P) {
    return this.http.execute<D, P>(
      new HttpInstance<D, P>(path, payload, "POST"),
    );
  }

  protected put(path: string, data: any) {
    return http.put(path, data);
  }

  protected patch(path: string, data: any) {
    return http.patch(path, data);
  }

  protected remove(path: string) {
    return http.remove(path);
  }

  protected saveLocal(key: string, data: any, engine?: string) {
    storage.set(key, data, engine);
  }

  protected deleteLocal(key: string, engine?: string) {
    storage.remove(key, engine);
  }
}
