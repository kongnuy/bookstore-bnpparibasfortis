import type { IForm } from "@/_interfaces";
import type { ZodE164, ZodEmail, ZodNumber, ZodString } from "zod/v4";

export type KeyGetterFunction = <T>(item: T) => string;

export type IMessage = {
  key: string;
  type: string;
  content: string;
};

export type ZodTypes = ZodEmail | ZodString | ZodE164 | ZodNumber;

export type FieldParams = {
  required?: boolean;
  minLength?: number;
  maxLength?: number;
  pattern?: string;
  zod?: ZodTypes;
  groups?: number[] | ((form: IForm) => number[]);
  [key: string]: any;
};

export type GenericResponse<D> = {
  messages: IMessage[];
  code: number;
  status: string;
  data: D;
};
