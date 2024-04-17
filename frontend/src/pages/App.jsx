import React from "react"
import { Navbar } from "./Shared/Components/NavBar/Index"
import { Footer } from "./Shared/Components/Footer/Index"
import { Outlet } from "react-router-dom"
import { AuthenticationContext } from "./Shared/State/context"

function App() {
    return (<AuthenticationContext>
        <div id="page-top">
            <div id="wrapper">
                <div id="content-wrapper" className="d-flex flex-column">
                    <div id="content">
                        {/* Navbar */}
                        <Navbar />
                        {/* End of Navbar */}
                        {/* Begin Page Content */}
                        <div className="container-fluid">
                            <Outlet />
                        </div>
                        {/* End of Page Content */}
                    </div>
                    {/* Footer */}
                    <Footer />
                    {/* End of Footer */}
                </div>
            </div>
            <a className="scroll-to-top rounded" href="#page-top">
                <i className="fas fa-angle-up" />
            </a>
           
        </div>
    </AuthenticationContext>

    )
}

export default App