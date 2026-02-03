import axios, { type AxiosInstance, type AxiosResponse } from "axios";
import { Constants } from "../_constants";
import { storage } from "./storage";
import { toString, log, logError, extractServerErrorMessage } from "./utils";

const {
  API_URL,
  JWT_TOKEN_KEY,
  API_REQUESTS_DEFAULTS: { TIMEOUT, HEADERS },
} = Constants;

export const axiosInstance = axios.create({
  timeout: TIMEOUT,
  headers: HEADERS,
  baseURL: API_URL,
});

axiosInstance.interceptors.request.use(
  function (config) {
    const token = storage.get(JWT_TOKEN_KEY);
    if (token) {
      const sanitizeToken = token.replace(/['"]+/g, "");
      config.headers["Authorization"] = `Bearer ${sanitizeToken}`;
    }
    console.log("== config ==> ", config);
    return config;
  },
  function (error) {
    console.error("== error ==> ", error);
    return Promise.reject(error);
  },
);

axiosInstance.interceptors.response.use(
  function (response) {
    console.log("== response ==> ", response);
    return response;
  },
  function (error) {
    console.error("== error ==> ", error);
    return Promise.reject(error);
  },
);

export const http = {
  get,
  post,
  put,
  remove,
  patch,
  getInstance,
};

async function getInstance(multipart?: string): Promise<AxiosInstance> {
  return new Promise<AxiosInstance>(async (resolve) => {
    const headers = await getHeaders(multipart);
    resolve(
      axios.create({
        timeout: 60000,
        headers: headers,
        baseURL: API_URL,
      }),
    );
  });
}

async function getHeaders(multipart?: string) {
  const headers = {
    Accept: "application/json",
    "Content-Type": multipart || "application/json",
  };
  const token =
    (await storage.get(JWT_TOKEN_KEY)) || (await storage.get(JWT_TOKEN_KEY));
  if (token) {
    Object.assign(headers, {
      Authorization: `Bearer ${token.replace(/['"]+/g, "")}`,
    });
  }
  return headers;
}

function getBody(params: any) {
  return params;
}

async function get(path: string): Promise<any> {
  const axiosInstance = await getInstance();
  return axiosInstance
    .get(path)
    .then((res: any) => handleResponse(res))
    .catch((err: any) => handleError(err));
}

async function post(path: string, data?: any, multipart?: any): Promise<any> {
  const axiosInstance = await getInstance(multipart);
  return axiosInstance
    .post(path, multipart ? data : getBody(data))
    .then((res: any) => handleResponse(res))
    .catch((err: any) => handleError(err));
}

async function put(path: string, data?: any): Promise<any> {
  const axiosInstance = await getInstance();
  return axiosInstance
    .put(path, getBody(data))
    .then((res: any) => handleResponse(res))
    .catch((err: any) => handleError(err));
}

async function patch(path: string, data?: any): Promise<any> {
  const axiosInstance = await getInstance();
  return axiosInstance
    .patch(path, getBody(data))
    .then((res: any) => handleResponse(res))
    .catch((err: any) => handleError(err));
}

async function remove(path: string, data?: any): Promise<any> {
  const axiosInstance = await getInstance();
  return axiosInstance
    .delete(path, getBody(data))
    .then((res: any) => handleResponse(res))
    .catch((err: any) => handleError(err));
}

function handleResponse(res: AxiosResponse) {
  if (res.config.data) {
    const body = toString(res.config.data);
    log("Request Body data ==> " + body, "info");
  }

  const data = toString(res.data);
  log(
    "Request response data ==> " +
      (data && data.length < 100 ? data : data.substring(0, 100)),
    "info",
  );

  return res.data;
}

function handleError(err: any) {
  let data = null;
  let status = 500;
  let message =
    "Une érreur est survenu lors de l'opération. Merci de reéssayer !";

  logError("<== Request Error ==> ");
  logError(err.message);
  logError(err.stack);
  logError(err.status);
  logError(err.code);
  logError(err.response);
  if (err && err.response) {
    logError(err.response.data);
    logError(err.response.status);
    logError(err.response.config);
    data = err.response.data;
    status = err.response.status;
    if (err && err.response && err.response.data && err.response.data.message) {
      message = err.response.data.message;
    } else if (err && err.response && err.response.message) {
      message = err.response.message;
    } else if (data) {
      const msg = extractServerErrorMessage(data.messages);
      if (msg) {
        message = msg;
      }
    }
  } else if (err && err.request) {
    logError(err.request);
  } else {
    logError(err.message);
  }
  logError("<== End request Error ==> ");
  logError("==> status " + status);

  return { success: false, status, data, message };
}
