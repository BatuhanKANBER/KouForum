import { SignUp } from "../pages/SignUp/signup.jsx"
import {createBrowserRouter} from "react-router-dom"
import { Activation } from "../pages/Activation/activation.jsx"
import { Home } from "../pages/Home/Index.jsx"

export default createBrowserRouter([
    {
      path: "*",
      Component: Home
    },
    {
      path: "/signup",
      Component: SignUp
    },
    {
      path: "/activation/:token",
      Component: Activation
    }
  ])