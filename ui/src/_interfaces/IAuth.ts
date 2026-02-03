export interface IAuthLogin {
  identifier: string;
  password: string;
}

export interface IAuthLoginResponse {
  jwtToken: string;
}
