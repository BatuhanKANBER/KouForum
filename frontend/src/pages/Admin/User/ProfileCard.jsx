import defaultProfileImage from "../../../assets/default_user.png"

export function ProfileCard({ user }) {
    return <>
        <div className="container mt-5">
            <div className="card">
                <div className="card-header text-center">
                    <img src={defaultProfileImage} width="200"></img>
                </div>
                <div className="card-body">
                    <p><b>USERNAME: </b>{user.username}</p>
                    <p><b>EMAIL: </b>{user.email}</p>
                </div>
            </div>
        </div>
    </>
}