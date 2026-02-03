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

export function RegisterForm({
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
          <CardTitle>Create your account</CardTitle>
          <CardDescription>
            Enter your informations below to create your account
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
                <Field className="grid grid-cols-2 gap-4">
                  <Field>
                    <FieldLabel htmlFor="firstName">First name</FieldLabel>
                    <Input
                      onChange={(e) =>
                        onValueChange("firstName", e.target.value)
                      }
                      value={state.firstName}
                      id="firstName"
                      type="text"
                      required
                    />
                  </Field>
                  <Field>
                    <FieldLabel htmlFor="lastName">Last name</FieldLabel>
                    <Input
                      onChange={(e) =>
                        onValueChange("lastName", e.target.value)
                      }
                      value={state.lastName}
                      id="lastName"
                      type="text"
                      required
                    />
                  </Field>
                </Field>
              </Field>
              <Field>
                <Field className="grid grid-cols-2 gap-4">
                  <Field>
                    <FieldLabel htmlFor="email">Email</FieldLabel>
                    <Input
                      id="email"
                      type="email"
                      placeholder="m@example.com"
                      required
                      onChange={(e) => onValueChange("email", e.target.value)}
                      value={state.email}
                    />
                  </Field>
                  <Field>
                    <FieldLabel htmlFor="phoneNumber">Phone number</FieldLabel>
                    <Input
                      id="phoneNumber"
                      type="phoneNumber"
                      placeholder="06XXX"
                      required
                      onChange={(e) =>
                        onValueChange("phoneNumber", e.target.value)
                      }
                      value={state.phone}
                    />
                  </Field>
                </Field>
              </Field>

              <Field>
                <FieldLabel htmlFor="username">Username</FieldLabel>
                <Input
                  id="username"
                  type="text"
                  placeholder="john.doe"
                  required
                  onChange={(e) => onValueChange("username", e.target.value)}
                  value={state.username}
                />
              </Field>
              <Field>
                <Field className="grid grid-cols-2 gap-4">
                  <Field>
                    <FieldLabel htmlFor="password">Password</FieldLabel>
                    <Input
                      onChange={(e) =>
                        onValueChange("password", e.target.value)
                      }
                      value={state.password}
                      id="password"
                      type="password"
                      required
                    />
                  </Field>
                  <Field>
                    <FieldLabel htmlFor="confirm-password">
                      Confirm Password
                    </FieldLabel>
                    <Input
                      onChange={(e) =>
                        onValueChange("password2", e.target.value)
                      }
                      value={state.password2}
                      id="confirm-password"
                      type="password"
                      required
                    />
                  </Field>
                </Field>
              </Field>
              <Field>
                <Button className="cursor-pointer" type="submit">
                  Sign up
                </Button>

                <FieldDescription className="text-center">
                  Already have an account? <NavLink to="/login">Login</NavLink>
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
