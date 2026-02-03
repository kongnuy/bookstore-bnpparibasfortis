import { BookService } from "@/_services";
import { Card, CardContent } from "@/components/ui/card";
import { cn } from "@/lib/utils";
import {
  ArrowRight,
  CheckCircle,
  ContactRound,
  Hand,
  Server,
  UserCircle,
  ShoppingCart,
} from "lucide-react";
import { useEffect, useState } from "react";
import { Spinner } from "./ui/spinner";

const actions = [
  {
    title: "Book 1 : Getting Started",
    description:
      "Everything you need to know to get started and get to work in ChatCloud.",
    href: "#",
    icon: ArrowRight,
    iconForeground: "text-green-700",
    iconBackground: "bg-green-50 dark:bg-green-950/30",
    ringColorClass: "ring-green-700/30",
  },
  {
    title: "Book 2 :Admin Settings",
    description:
      "Learn how to manage your current workspace or your enterprise space.",
    href: "#",
    icon: UserCircle,
    iconForeground: "text-red-700",
    iconBackground: "bg-red-50 dark:bg-red-950/30",
    ringColorClass: "ring-red-700/30",
  },
  {
    title: "Book 3 : Server Setup",
    description:
      "Connect, simplify, and automate. Discover the power of apps and tools.",
    href: "#",
    icon: Server,
    iconForeground: "text-blue-700",
    iconBackground: "bg-blue-50 dark:bg-blue-950/30",
    ringColorClass: "ring-blue-700/30",
  },
  {
    title: "Book 4 : Login and Verification",
    description:
      "Read on to learn how to sign in with your email address, or your Apple or Google.",
    href: "#",
    icon: CheckCircle,
    iconForeground: "text-sky-700",
    iconBackground: "bg-sky-50 dark:bg-sky-950/30",
    ringColorClass: "ring-sky-700/30",
  },
  {
    title: "Book 5 : Account Setup",
    description:
      "Adjust your profile and preferences to make ChatCloud work just for you.",
    href: "#",
    icon: ContactRound,
    iconForeground: "text-pink-700",
    iconBackground: "bg-pink-50 dark:bg-pink-950/30",
    ringColorClass: "ring-pink-700/30",
  },
  {
    title: "Book 6 : Trust & Safety",
    description:
      "Trust on our current database and learn how we distribute your data.",
    href: "#",
    icon: Hand,
    iconForeground: "text-orange-700",
    iconBackground: "bg-orange-50 dark:bg-orange-950/30",
    ringColorClass: "ring-orange-700/30",
  },
];

export default function GridList03() {
  const [books, setBooks] = useState<any[]>([]);
  const [loading, setLoading] = useState(true);

  const getExtra = (i: number) => {
    if (i < actions.length) {
      return actions[i];
    }
    return actions[Math.floor(Math.random() * (actions.length - 1))];
  };

  useEffect(() => {
    BookService.getInstance()
      .getBooks()
      .then((res) =>
        setBooks(
          res.data.map((data: any, i: number) => ({ ...getExtra(i), ...data })),
        ),
      )
      .finally(() => setLoading(false));
  }, []);

  if (loading) {
    return <Spinner />;
  }
  return (
    <div className="flex items-center justify-center p-8">
      <div className=" rounded-2xl grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-3">
        {books.map((action) => (
          <Card
            key={action.uuid}
            className={cn(
              "group relative rounded-xl border-0 bg-card p-0 focus-within:ring-2 focus-within:ring-ring focus-within:ring-inset",
            )}
          >
            <CardContent className="p-6">
              <div>
                <span
                  className={cn(
                    action.iconBackground,
                    action.iconForeground,
                    "inline-flex rounded-lg p-3 ring-2 ring-inset",
                    action.ringColorClass,
                  )}
                >
                  <action.icon aria-hidden="true" className="h-6 w-6" />
                </span>
              </div>
              <div className="mt-4">
                <h3 className="text-balance text-base font-semibold text-foreground">
                  {action.title}
                </h3>
                <p className="text-pretty mt-2 text-sm text-muted-foreground">
                  {action.summary}
                </p>
                <small className="italic text-xs">By {action.authors}</small>
              </div>
              <span
                aria-hidden="true"
                className="cursor-pointer absolute bottom-6 right-6 text-muted-foreground/50 group-hover:text-muted-foreground/60"
              >
                <ShoppingCart className="h-6 w-6" />
              </span>
              <span
                aria-hidden="true"
                className="pointer-events-none absolute top-6 right-6 text-cyan-600 group-hover:text-muted-foreground/60"
              >
                {Number(action.price).toFixed(2) + " €"}
              </span>
            </CardContent>
          </Card>
        ))}
      </div>
    </div>
  );
}
