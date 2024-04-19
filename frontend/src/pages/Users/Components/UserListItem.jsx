import { Link } from "react-router-dom"
import { ProfileImage } from "../../Shared/Components/ProfileImage"

export function UserTableItem({ user }) {
    return <>
        <Link to={`/user/${user.id}`} className="list-group-item d-flex justify-content-between" style={{ textDecoration: "none" }}>
            <ProfileImage width={"30"} image={user.image}/><p>{user.username}</p><p>{user.email}</p>
        </Link >
    </>
}