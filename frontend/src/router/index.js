import { SignUp } from "../pages/SignUp/signup.jsx"
import { createBrowserRouter } from "react-router-dom"
import { Activation } from "../pages/Activation/activation.jsx"
import { Home } from "../pages/Admin/Home/Index.jsx"
import { User } from "../pages/Admin/User/Index.jsx"
import { Users } from "../pages/Admin/Users/Index.jsx"

export default createBrowserRouter([
  {
    path: "*",
    Component: Home
  },
  {
    path: "/users",
    Component: Users
  },
  {
    path: "/user/:id",
    Component: User
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