import { useState } from "react"
import { useAuthState } from "../../Shared/State/context"
import { ProfileImage } from "../../Shared/Components/ProfileImage";
import { UserEditForm } from "./UserEditForm";

export function ProfileCard({ user }) {
    const authState = useAuthState()
    const [editMode, setEditMode] = useState(false)
    const [tempImage, setTempImage] = useState()

    const isEditButtonVisible = !editMode && authState.id === user.id

    const visibleUsername = authState.id === user.id ? authState.username : user.username
    return <>
        <div className="container mt-5">
            <div className="card">
                <div className="card-header text-center">
                    <ProfileImage width={"200"} tempImage={tempImage} image={user.image}/>
                </div>
                <div className="card-body">
                    {!editMode && <p><b>USERNAME: </b>{visibleUsername}</p>}
                    {isEditButtonVisible && <button onClick={() => setEditMode(true)} className="btn btn-primary text-white w-100" type='submit'>
                        Güncelle
                    </button>}
                    {editMode &&
                        <UserEditForm setEditMode={setEditMode} setTempImage={setTempImage} />
                    }
                </div>
            </div>
        </div>
    </>
}