import { SignUp } from "../pages/SignUp/signup.jsx"
import { createBrowserRouter } from "react-router-dom"
import { Activation } from "../pages/Activation/activation.jsx"
import { Home } from "../pages/Home/Index.jsx"
import { User } from "../pages/User/Index.jsx"
import { Users } from "../pages/Users/Index.jsx"
import { Login } from "../pages/Login/login.jsx"
import App from "../pages/App.jsx"

export default createBrowserRouter([
  {
    path: "/",
    Component: App,
    children: [
      {
        path: "/",
        index: true,
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
        path: "/login",
        Component: Login
      },
      {
        path: "/activation/:token",
        Component: Activation
      }
    ]
  }
])