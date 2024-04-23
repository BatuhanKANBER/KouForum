import { useState } from "react";
import { Input } from "../../Shared/Components/Input";
import { Spinner } from "../../Shared/Components/Spinner";
import { useAuthDispatch, useAuthState } from "../../Shared/State/context";
import { updateUser } from "./api";
import { Alert } from "../../Shared/Components/Alert";

export function UserEditForm({ setEditMode, setTempImage }) {
    const authState = useAuthState()
    const [apiProgress, setApiProgress] = useState(false)
    const [errors, setErrors] = useState({})
    const [newUserName, setNewUsername] = useState(authState.username)
    const [generalErrors, setGeneralErrors] = useState()
    const dispatch = useAuthDispatch()
    const [newImage, setNewImage] = useState()

    const onChangeUsername = (event) => {
        setNewUsername(event.target.value)
        setErrors(function (lastErrors) {
            return {
                ...lastErrors,
                username: undefined
            }
        });
    }

    const onClickCancel = () => {
        setEditMode(false)
        setNewUsername(authState.username)
        setNewImage()
        setTempImage()
    }

    const onSelectImage = (event) => {
        setErrors(function (lastErrors) {
            return {
                ...lastErrors,
                image: undefined
            }
        });
        if (event.target.files.length < 1) return;
        const file = event.target.files[0]
        const fileReader = new FileReader();
        fileReader.onloadend = () => {
            const data = fileReader.result
            setNewImage(data);
            setTempImage(data);
        }
        fileReader.readAsDataURL(file);
    }

    const onSubmit = async (event) => {
        event.preventDefault()
        setApiProgress(true)
        setErrors({})
        setGeneralErrors()
        try {
            const { data } = await updateUser(authState.id, { username: newUserName, image: newImage })
            dispatch({ type: 'user-update-success', data: { username: data.username, image: data.image } })
            setEditMode(false)
        } catch (axiosError) {
            if (axiosError.response?.data) {
                if (axiosError.response.data.status === 400) {
                    setErrors(axiosError.response.data.validationErrors);
                } else {
                    setGeneralErrors(axiosError.response.data.message)
                }
            } else {
                setGeneralErrors("Unexpected error occured, please try again.")
            }
        } finally {
            setApiProgress(false);
        }
    }

    return <>
        <form onSubmit={onSubmit}>
            <Input error={errors.username} onChange={onChangeUsername} defaultValue={authState.username} placeholder="Kullanıcı Adı"></Input>
            <Input error={errors.image} type="File" onChange={onSelectImage}></Input>
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