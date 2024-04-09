import { useParams } from "react-router-dom"
import { useEffect, useState } from "react"
import { activateUser } from "./api"
import { Spinner } from "../Shared/Components/Spinner"
import { Alert } from "../Shared/Components/Alert"

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
    }, [token])

    return <>
        {apiProgress &&
            <Spinner />}
        {successMessage && (<Alert>{successMessage}</Alert>)}
        {errorMessage && (<Alert type="danger">{errorMessage}</Alert>)}

    </>
}