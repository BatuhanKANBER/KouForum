import { SignUp } from "../pages/SignUp/signup.jsx"
import { Login } from "../pages/Login/login.jsx"
import {createBrowserRouter} from "react-router-dom"

export default createBrowserRouter([
    {
      path: "/login",
      Component: Login
    },
    {
      path: "/signup",
      Component: SignUp
    }
  ])