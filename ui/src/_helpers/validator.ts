export const isEmpty = (value: string) => {
  return (
    value !== undefined && value !== null && String(value).trim().length === 0
  );
};

export const isNull = (value: any) => {
  return value === undefined || value === null;
};

export const isNullOrEmpty = (value: any) => {
  return isNull(value) || isEmpty(value);
};

export const isEmail = (value: string) => {
  return matches("/^[^s@]+@[^s@]+.[^s@]+$/", value);
};

export const matches = (pattern: string, value: string) => {
  return new RegExp(pattern).test(value);
};
