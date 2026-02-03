export const Constants = {
  JWT_TOKEN_KEY: "jwttoken",
  JWT_ACCOUNT_KEY: "jwttoken_account",
  AUTH_LOGIN_URL: "/login",
  DASHBOARD_URL: "/dashboard",
  UI_GRAVATAR_URL: "https://ui-avatars.com/api/",
  ENV: "dev",
  BUILD: "1.0.0",
  API_URL: "http://localhost:8096/api",
  withOutTokenPaths: [],
  COPY_RIGHT: `Copyright ${new Date().getFullYear()}, HighFi`,
  API_REQUESTS_DEFAULTS: {
    TIMEOUT: 60000,
    HEADERS: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    ERR_NETWORK: {
      type: "error",
      key: "network_error",
      content:
        "A network error occured during the request. Please try again later",
    },
    DISPLAY_ERROR_KEYS: [
      "client_error",
      "network_error",
      "server_error",
      "auth_error",
    ],
  },
};
