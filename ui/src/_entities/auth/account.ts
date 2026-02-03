import type { IAccount } from "@/_interfaces";
import { BaseEntity } from "../base";

export class Account extends BaseEntity implements IAccount {
  phoneNumber?: string;
  firstName?: string;
  userName?: string;
  lastName?: string;
  gender?: string;
  email?: string;
  dateOfBirth?: string;
  status?: string;
  emailVerified?: boolean;
  phoneNumberVerified?: boolean;

  constructor(account?: IAccount) {
    super();
    if (account) {
      for (const [key, value] of Object.entries(account)) {
        if (Object.hasOwn(this, key)) {
          (this as any)[key] = value;
        }
      }
    }
  }

  public get fullName(): string {
    if (this.firstName && this.lastName) {
      return `${this.firstName} ${this.lastName}`;
    }
    return this.email ? this.email.split("@")[0] : "";
  }
}
