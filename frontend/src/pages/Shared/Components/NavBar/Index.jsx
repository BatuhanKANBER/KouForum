import { Link } from "react-router-dom";
import { useAuthDispatch, useAuthState } from "../../State/context";
import { ProfileImage } from "../ProfileImage";

export function Navbar() {
    const authState = useAuthState()
    const dispatch = useAuthDispatch()
    const onClickLogout = () => {
        dispatch({ type: 'logout-success' })

    }
    return <>
        <nav className="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
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
                        <ProfileImage width={30}/>
                    </a>


                    <div className="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                        <Link className="dropdown-item" to={`/user/${authState.id}`}><i className="fas fa-solid fa-user mr-2  text-gray-400" />Profilim</Link>
                        <div className="dropdown-divider" />
                        <Link className="dropdown-item" data-toggle="modal" data-target="#logoutModal"><i className="fas fa-sign-out-alt fa-sm fa-fw mr-2 s text-gray-400" />
                            Çıkış Yap</Link>
                    </div>
                </li>)}
            </ul>
        </nav>
        {/* Logout Modal*/}
        <div className="modal fade" id="logoutModal" tabIndex={-1} role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div className="modal-dialog" role="document">
                <div className="modal-content">
                    <div className="modal-header">
                        <h5 className="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                        <button className="close" type="button" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div className="modal-body">Çıkış yapmak istediğinize emin misiniz?</div>
                    <div className="modal-footer">
                        <button className="btn btn-secondary" type="button" data-dismiss="modal">Hayır</button>
                        <Link className="btn btn-primary" to={"/login"} onClick={onClickLogout}>Evet</Link>
                    </div>
                </div>
            </div>
        </div>
    </>
}