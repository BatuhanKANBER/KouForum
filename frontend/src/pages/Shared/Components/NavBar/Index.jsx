import { Link, useNavigate } from "react-router-dom";
import { useAuthDispatch, useAuthState } from "../../State/context";
import { ProfileImage } from "../ProfileImage";
import { logout } from "./api";

export function Navbar() {
    const authState = useAuthState()
    const dispatch = useAuthDispatch()
    const navigate = useNavigate()
    const onClickLogout = async () => {
        const result = confirm("Are you sure?")
        if (result) {
            try {
                await logout();
                navigate("/login")
            } catch {

            } finally {
                dispatch({ type: 'logout-success' })
            }
        }
    }
    return <>
        <nav className="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
            <Link class="d-flex align-items-center justify-content-center ml-5 link-underline link-underline-opacity-0" to="/">
            <h1 className="h3 mb-0 text-primary-800">KOUFORUM</h1>
            </Link>
            {/* Sidebar Toggle (Topbar) */}
            <button id="sidebarToggleTop" className="btn btn-link d-md-none rounded-circle mr-3">
                <i className="fa fa-bars" />
            </button>
            <ul className="navbar-nav ml-auto">
                <div className="topbar-divider d-none d-sm-block" />
                {/* Nav Item - User Information */}
                {authState.id > 0 && (<li className="nav-item dropdown no-arrow">

                    <a className="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <span className="mr-2 d-none d-lg-inline text-gray-600 small">{authState.username}</span>
                        <ProfileImage width={30} image={authState.image} />
                    </a>


                    <div className="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                        <Link className="dropdown-item" to={`/user/${authState.id}`}><i className="fas fa-solid fa-user mr-2  text-gray-400" />Profilim</Link>
                        <div className="dropdown-divider" />
                        <Link onClick={onClickLogout} className="dropdown-item" data-toggle="modal" data-target="#logoutModal"><i className="fas fa-sign-out-alt fa-sm fa-fw mr-2 s text-gray-400" />
                            Çıkış Yap</Link>
                    </div>
                </li>)}
            </ul>
        </nav>
    </>
}