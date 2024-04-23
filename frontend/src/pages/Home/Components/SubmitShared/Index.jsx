import { useEffect, useState } from "react"
import { useAuthState } from "../../../Shared/State/context"
import { ProfileImage } from "../../../Shared/Components/ProfileImage"
import { Alert } from "../../../Shared/Components/Alert"
import { Spinner } from "../../../Shared/Components/Spinner"
import { submitAttachment, submitShared } from "./api"
import { Input } from "../../../Shared/Components/Input"

export function SubmitShared() {
    const authState = useAuthState()
    const [focused, setFocused] = useState(false)
    const [content, setContent] = useState("")
    const [errors, setErrors] = useState({})
    const [generalErrors, setGeneralErrors] = useState()
    const [apiProgress, setApiProgress] = useState(false)
    const [newImage, setNewImage] = useState()
    const [attachmentId, setAttechmentId] = useState()

    useEffect(() => {
        if (!focused) {
            setContent("")
            setErrors({})
            setNewImage()
            setAttechmentId()
        }
    }, [focused])

    useEffect(() => {
        setErrors(function (lastErrors) {
            return {
                ...lastErrors,
                content: undefined
            }
        });
    }, [content])

    const onSubmit = async (event) => {
        event.preventDefault();
        setGeneralErrors();
        setApiProgress(true);

        try {
            await submitShared({ content, attachmentId: attachmentId })
            setFocused(false)
        } catch (axiosError) {
            console.log(axiosError)
            if (axiosError.response?.data) {
                if (axiosError.response.data.status === 400) {
                    setErrors(axiosError.response.data.validationErrors)
                } else {
                    setGeneralErrors(axiosError.response.data.message)
                }
            } else {
                setGeneralErrors("Unexpected error occured, please try again.")
            }
        } finally {
            setApiProgress(false)
        }
    }

    const onSelectImage = (event) => {
        if (event.target.files.length < 1) return;
        const file = event.target.files[0]
        const fileReader = new FileReader();
        fileReader.onloadend = () => {
            const data = fileReader.result
            setNewImage(data);
            uploadFile(file)
        }
        fileReader.readAsDataURL(file);
    }

    const uploadFile = async file => {
        const attachment = new FormData()
        attachment.append('file', file)
        const response = await submitAttachment(attachment)
        setAttechmentId(response.data.id)
    }


    return <>
        <div className="card">
            <div className="card-header">
                <div className="text-left">
                    <ProfileImage width={30} image={authState.image} />
                </div>
            </div>
            <div className="col-12 mt-2 mb-2">
                <textarea disabled={apiProgress} id="content" value={content} onChange={(event) => setContent(event.target.value)} onFocus={() => setFocused(true)} className={errors.content ? "form-control is-invalid" : "form-control"} placeholder="Bir şeyler paylaş..." type="text"></textarea>
                <div className="invalid-feedback">{errors.content}</div>
            </div>
            {
                focused && (<>
                    <div className="col-12">
                        <Input type="file" onChange={onSelectImage} />
                    </div>
                    <div className="col-12 d-flex justify-content-center">
                        {newImage && <img className="img-thumbnail" src={newImage}></img>}
                    </div>
                    <div className="col-12 mt-1 mb-1 d-flex justify-content-between">
                        <button disabled={apiProgress} type="button" onClick={() => setFocused(false)} className="btn btn-secondary w-25">Vazgeç</button>
                        <button disabled={apiProgress} type="submit" onClick={onSubmit} className="btn btn-primary w-25">
                            {apiProgress &&
                                (<Spinner sm />)}
                            Paylaş
                        </button>

                    </div>
                </>
                )
            }
            <div className="col-12">
                {generalErrors && (<Alert type="danger">{generalErrors}</Alert>)}

            </div>
        </div>
    </>
}