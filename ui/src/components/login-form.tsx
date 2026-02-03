import { cn } from "@/lib/utils";
import { Button } from "@/components/ui/button";
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import {
  Field,
  FieldDescription,
  FieldGroup,
  FieldLabel,
} from "@/components/ui/field";
import { Input } from "@/components/ui/input";
import { NavLink } from "react-router";

export function LoginForm({
  className,
  state,
  onFormSubmit,
  onValueChange,
  ...props
}: React.ComponentProps<"div"> & {
  state: any;
  onFormSubmit: () => void;
  onValueChange: (field: string, newValue: any) => void;
}) {
  return (
    <div className={cn("flex flex-col gap-6", className)} {...props}>
      <Card>
        <CardHeader>
          <CardTitle>Login to your account</CardTitle>
          <CardDescription>
            Enter your credentials below to login to your account
          </CardDescription>
        </CardHeader>
        <CardContent>
          <form
            onSubmit={(e) => {
              e.preventDefault();
              onFormSubmit();
            }}
          >
            <FieldGroup>
              <Field>
                <FieldLabel htmlFor="identifier">Identifier</FieldLabel>
                <Input
                  id="identifier"
                  type="text"
                  placeholder="m@example.com, john.doe, 06XXXXXX"
                  required
                  onChange={(e) => onValueChange("identifier", e.target.value)}
                  value={state.identifier}
                />
              </Field>
              <Field>
                <div className="flex items-center">
                  <FieldLabel htmlFor="password">Password</FieldLabel>
                </div>
                <Input
                  value={state.password}
                  onChange={(e) => onValueChange("password", e.target.value)}
                  id="password"
                  type="password"
                  required
                />
              </Field>
              <Field>
                <Button className="cursor-pointer" type="submit">
                  Login
                </Button>

                <FieldDescription className="text-center">
                  Don&apos;t have an account?{" "}
                  <NavLink to="/register">Sign up</NavLink>
                </FieldDescription>

                <FieldDescription className="text-center text-sm">
                  <NavLink to="/">
                    <small>Back to home</small>
                  </NavLink>
                </FieldDescription>
              </Field>
            </FieldGroup>
          </form>
        </CardContent>
      </Card>
    </div>
  );
}
