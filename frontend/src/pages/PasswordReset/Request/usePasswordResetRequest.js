import { useCallback, useState } from "react"
import { passwordResetRequest } from "./api"

export function usePasswordResetRequest() {
    const [apiProgress, setApiProgress] = useState(false)
    const [email, setEmail] = useState()
    const [success, setSuccuss] = useState()
    const [generalError, setGeneralError] = useState()
    const [errors, setErrors] = useState({})

    const onSubmit = useCallback(async (event) => {
        event.preventDefault()
        setApiProgress(true)
        setSuccuss()
        setErrors({})
        setGeneralError()
        try {
            const response = await passwordResetRequest({ email })
            setSuccuss(response.data.message)
        } catch (axiosError) {
            if (axiosError.response.data.status === 400) {
                setErrors(axiosError.response?.data.validationErrors)
            } else {
                setGeneralError(axiosError.response.data.message)
            }
        } finally {
            setApiProgress(false)
        }
    }, [email])

    return {
        apiProgress,
        onChangeEmail: (event) => setEmail(event.target.value),
        onSubmit,
        success,
        error: errors.email,
        generalError
    }


}