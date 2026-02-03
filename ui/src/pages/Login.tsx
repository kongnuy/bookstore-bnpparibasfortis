import { AuthModel } from "@/_models";
import { LoginForm } from "@/components/login-form";
import { useState } from "react";

function Login() {
  const [state, setState] = useState<any>({
    identifier: "",
    password: "",
    loading: false,
  });

  const onValueChange = (field: string, newValue: any) => {
    setState((state: any) => ({ ...state, [field]: newValue }));
  };

  const onFormSubmit = async () => {
    setState({ ...state, loading: true });
    await AuthModel.getInstance().logIn(state.identifier, state.password);
    setState({ ...state, loading: false });
  };

  return (
    <div
      className={
        "flex min-h-svh w-full items-center justify-center p-6 md:p-10  " +
        (state.loading ? " cursor-not-allowed opacity-50 " : "")
      }
    >
      <div className="w-full max-w-sm">
        <LoginForm
          onFormSubmit={onFormSubmit}
          onValueChange={onValueChange}
          state={state}
          className={state.loading ? " cursor-not-allowed opacity-50 " : ""}
        />
      </div>
    </div>
  );
}

export default Login;
