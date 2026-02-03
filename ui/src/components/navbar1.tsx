"use client";

import { ShoppingCart } from "lucide-react";

import { cn } from "@/lib/utils";

import { Button } from "@/components/ui/button";

import { NavLink } from "react-router";
import { useAuth } from "@/_hooks";
import { FieldDescription } from "./ui/field";
import { logOut } from "@/_actions";

interface MenuItem {
  title: string;
  url: string;
  description?: string;
  icon?: React.ReactNode;
  items?: MenuItem[];
}

interface Navbar1Props {
  className?: string;
  logo?: {
    url: string;
    src: string;
    alt: string;
    title: string;
    className?: string;
  };
  menu?: MenuItem[];
  auth?: {
    login: {
      title: string;
      url: string;
    };
    signup: {
      title: string;
      url: string;
    };
  };
}

const Navbar1 = ({
  logo = {
    url: "https://www.highfi.net/",
    src: "https://www.highfi.net/assets/images/logo.png",
    alt: "logo",
    title: "Highfi.com",
  },

  auth = {
    login: { title: "Login", url: "/login" },
    signup: { title: "Sign up", url: "register" },
  },
  className,
}: Navbar1Props) => {
  const { account } = useAuth();

  return (
    <section className={cn("py-4", className)}>
      <div className="container">
        {/* Desktop Menu */}
        <nav className=" items-center justify-between flex">
          <div className="flex items-center gap-6">
            {/* Logo */}
            <a
              href={logo.url}
              target="_blank"
              className="flex items-center gap-2"
            >
              <img
                src={logo.src}
                className="max-h-8 dark:invert"
                alt={logo.alt}
              />
              <span className="text-lg font-semibold tracking-tighter">
                {logo.title}
              </span>
            </a>
            <div className="flex items-center"></div>
          </div>
          <div className="flex gap-2">
            {account?.id ? (
              <>
                <FieldDescription className="flex items-center text-center">
                  {account.fullName}
                </FieldDescription>
                <Button asChild size="sm" variant={"destructive"}>
                  <a
                    href="/logout"
                    onClick={(e) => {
                      e.preventDefault();
                      logOut();
                    }}
                  >
                    Logout
                  </a>
                </Button>
              </>
            ) : (
              <>
                <Button asChild variant="outline" size="sm">
                  <NavLink to={auth.login.url}>{auth.login.title}</NavLink>
                </Button>
                <Button asChild size="sm">
                  <NavLink to={auth.signup.url}>{auth.signup.title}</NavLink>
                </Button>
              </>
            )}

            <NavLink to="/cart" className={"flex items-center"}>
              <ShoppingCart className="size-6" />
              <span className="inline-block px-1">
                My cart (<span>0</span>)
              </span>
            </NavLink>
          </div>
        </nav>
      </div>
    </section>
  );
};

export { Navbar1 };
