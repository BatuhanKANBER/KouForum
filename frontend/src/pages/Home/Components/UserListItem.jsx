import defaultProfileImage from "../../../assets/default_user.png"

export function UserTableItem({ user }) {
    return <>
        <button type="button" className="list-group-item d-flex justify-content-between"><img src={defaultProfileImage} width="30"></img><p>{user.username}</p><p>{user.email}</p></button>
    </>
}