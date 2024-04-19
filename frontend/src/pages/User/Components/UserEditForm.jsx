import { useState } from "react";
import { Input } from "../../Shared/Components/Input";
import { Spinner } from "../../Shared/Components/Spinner";
import { useAuthDispatch, useAuthState } from "../../Shared/State/context";
import { updateUser } from "./api";
import { Alert } from "../../Shared/Components/Alert";

export function UserEditForm({ setEditMode }) {
    const authState = useAuthState()
    const [apiProgress, setApiProgress] = useState(false)
    const [errors, setErrors] = useState({})
    const [newUserName, setNewUsername] = useState(authState.username)
    const [generalErrors, setGeneralErrors] = useState()
    const dispatch = useAuthDispatch()

    const onChangeUsername = (event) => {
        setNewUsername(event.target.value)
        setErrors({})
    }

    const onClickCancel = () => {
        setEditMode(false)
        setNewUsername(authState.username)
    }

    const onSubmit = async (event) => {
        event.preventDefault()
        setApiProgress(true)
        setErrors({})
        setGeneralErrors()
        try {
            await updateUser(authState.id, { username: newUserName })
            dispatch({ type: 'user-update-success', data: { username: newUserName } })
            setEditMode(false)
        } catch (axiosError) {
            if (axiosError.response?.data) {
                if (axiosError.response.data.status === 400) {
                    setErrors(axiosError.response.data.validationErrors);
                } else {
                    setGeneralError(axiosError.response.data.message);
                }
            } else {
                setGeneralError(t("genericError"));
            }
        } finally {
            setApiProgress(false);
        }
    }

    return <>
        <form onSubmit={onSubmit}>
            <Input error={errors.username} onChange={onChangeUsername} defaultValue={authState.username} placeholder="Kullanıcı Adı"></Input>
            <div className="d-flex justify-content-between">
                <button onClick={onClickCancel} className="btn btn-outline-secondary" type='button'>
                    Vazgeç
                </button>
                <button className="btn btn-primary" type='submit'>
                    {apiProgress &&
                        (<Spinner sm />)}
                    Kaydet
                </button>
            </div>
            {generalErrors && (<Alert type="danger">{generalErrors}</Alert>)}
        </form>
    </>
}