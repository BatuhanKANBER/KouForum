import { useParams } from "react-router-dom"
import { useEffect, useState } from "react"
import { activateUser } from "./api"

export function Activation() {
    const { token } = useParams()
    const [apiProgress, setApiProgress] = useState()
    const [successMessage, setSuccessMessage] = useState()
    const [errorMessage, setErrorMessage] = useState()

    useEffect(() => {
        async function activate() {
            setApiProgress(true)
            try {
                const response = await activateUser(token)
                setSuccessMessage(response.data.message)
            } catch (axiosError) {
                setErrorMessage(axiosError.response.data.message)
            } finally {
                setApiProgress(false)
            }
        }

        activate()
    }, [])

    return <>
        {apiProgress && (<span
            className="spinner-border"
            aria-hidden="true"></span>)}
        {successMessage && (<div className="alert alert-success" role="alert">{successMessage}</div>)}
        {errorMessage && (<div className="alert alert-danger" role="alert">{errorMessage}</div>)}

    </>
}