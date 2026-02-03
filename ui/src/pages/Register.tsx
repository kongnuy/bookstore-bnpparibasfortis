import { AuthModel } from "@/_models";
import { RegisterForm } from "@/components/register-form";
import { useState } from "react";

function Register() {
  const [state, setState] = useState<any>({
    username: "",
    password: "",
    password2: "",
    phoneNumber: "",
    email: "",
    firstName: "",
    lastName: "",
    loading: false,
  });

  const onValueChange = (field: string, newValue: any) => {
    setState((state: any) => ({ ...state, [field]: newValue }));
  };

  const onFormSubmit = async () => {
    setState({ ...state, loading: true });
    await AuthModel.getInstance().register(state);
    setState({ ...state, loading: false });
  };

  return (
    <div
      className={
        "flex min-h-svh w-full items-center justify-center p-6 md:p-10  " +
        (state.loading ? " cursor-not-allowed opacity-50 " : "")
      }
    >
      <div className="w-full max-w-md">
        <RegisterForm
          onFormSubmit={onFormSubmit}
          onValueChange={onValueChange}
          state={state}
          className={state.loading ? " cursor-not-allowed opacity-50 " : ""}
        />
      </div>
    </div>
  );
}

export default Register;
