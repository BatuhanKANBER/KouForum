import { Link } from "react-router-dom";

export function SideBar() {
    return (
        <ul className="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
            {/* Sidebar - Brand */}
            <Link className="sidebar-brand d-flex align-items-center justify-content-center" to={"/"}>
                <div className="sidebar-brand-text mx-3">ADMİN PANELİ</div>
            </Link>
            {/* Divider */}
            <hr className="sidebar-divider my-0" />
            {/* Linkler */}
            <li className="nav-item active">
                <Link className="nav-link" to={"/users"}>
                    <i className="fas fa-fw fa-solid fa-users"/>
                    <span>Kullanıcılar</span></Link>
            </li>
        </ul>
    )
}