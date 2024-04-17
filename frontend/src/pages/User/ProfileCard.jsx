import defaultProfileImage from "../../assets/default_user.png"
import { useAuthState } from "../Shared/State/context"

export function ProfileCard({ user }) {
    const authState = useAuthState()
    return <>
        <div className="container mt-5">
            <div className="card">
                <div className="card-header text-center">
                    <img src={defaultProfileImage} width="200"></img>
                </div>
                <div className="card-body">
                    <p><b>USERNAME: </b>{user.username}</p>
                    <p><b>EMAIL: </b>{user.email}</p>
                    {authState.id === user.id && <button>Edit</button>}
                </div>
            </div>
        </div>
    </>
}