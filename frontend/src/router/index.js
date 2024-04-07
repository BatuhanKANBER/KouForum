import { SignUp } from "../pages/SignUp/signup.jsx"
import { Login } from "../pages/Login/login.jsx"
import {createBrowserRouter} from "react-router-dom"
import { Activation } from "../pages/Activation/activation.jsx"

export default createBrowserRouter([
    {
      path: "/login",
      Component: Login
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