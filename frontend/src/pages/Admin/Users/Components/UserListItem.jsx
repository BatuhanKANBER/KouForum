import { Link } from "react-router-dom"
import defaultProfileImage from "../../../../assets/default_user.png"

export function UserTableItem({ user }) {
    return <>
        <Link to={`/user/${user.id}`} className="list-group-item d-flex justify-content-between" style={{ textDecoration: "none" }}>
            <img src={defaultProfileImage} width="30"></img><p>{user.username}</p><p>{user.email}</p>
        </Link >
    </>
}