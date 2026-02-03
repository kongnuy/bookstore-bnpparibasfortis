import GridList03 from "@/components/grid-list-03";
import { Navbar1 } from "@/components/navbar1";

function Home() {
  return (
    <>
      <div className="flex bg-white shadow-sm w-full justify-center">
        <div className="w-full max-w-[80%]">
          <Navbar1 />
        </div>
      </div>
      <div className="flex text-center w-full justify-center mt-16">
        <div className="w-full max-w-[80%]">
          <h1 className="text-3xl py-8">
            KATA - DISTRIBUTED DEVELOPER - Online Bookstore
          </h1>
          <p>
            This is a simple code kata that involves creating a basic front-end
            in React and a back-end using a RESTful API. We will create a Simple
            Online Bookstore. We will display a list of books and users will
            have the possibility to add books to their cart, display the cart
            and modify the quantity of items and remove items from the
            cart.{" "}
          </p>
        </div>
      </div>
      <div className="flex  w-full items-center justify-center p-6 md:p-10">
        <div className="w-full max-w-[80%]">
          <GridList03 />
        </div>
      </div>
    </>
  );
}

export default Home;
