import { useEffect, useState } from "react"
import { useAuthState } from "../../../Shared/State/context"
import { ProfileImage } from "../../../Shared/Components/ProfileImage"
import { Alert } from "../../../Shared/Components/Alert"
import { Spinner } from "../../../Shared/Components/Spinner"
import { submitShared } from "./api"

export function SubmitShared() {
    const authState = useAuthState()
    const [focused, setFocused] = useState(false)
    const [content, setContent] = useState("")
    const [errors, setErrors] = useState({})
    const [generalErrors, setGeneralErrors] = useState()
    const [apiProgress, setApiProgress] = useState(false)

    useEffect(() => {
        if (!focused) {
            setContent("")
            setErrors({})
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
            await submitShared({ content })
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

    return <>

        <div className="card">
            <div className="card-header">
                <div className="text-left">
                    <ProfileImage width={30} image={authState.image} />
                </div>
            </div>
            <form onSubmit={onSubmit}>
                <div className="col-12 mt-2 mb-2">
                    <textarea disabled={apiProgress} id="content" value={content} onChange={(event) => setContent(event.target.value)} onFocus={() => setFocused(true)} className={errors.content ? "form-control is-invalid" : "form-control"} placeholder="Bir şeyler paylaş..." type="text"></textarea>
                    <div className="invalid-feedback">{errors.content}</div>
                </div>
                {
                    focused && (
                        <div className="col-12 mt-1 mb-1 d-flex justify-content-between">
                            <button disabled={apiProgress} type="button" onClick={() => setFocused(false)} className="btn btn-secondary w-25">Vazgeç</button>
                            <button disabled={apiProgress} type="submit" className="btn btn-primary w-25">
                                {apiProgress &&
                                    (<Spinner sm />)}
                                Paylaş
                            </button>

                        </div>
                    )
                }
                <div className="col-12">
                    {generalErrors && (<Alert type="danger">{generalErrors}</Alert>)}

                </div>
            </form>
        </div>

    </>
}