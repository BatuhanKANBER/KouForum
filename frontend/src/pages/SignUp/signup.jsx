import { useEffect, useState } from 'react'
import { signUp } from './api'
import { Input } from './components/Input'
import { useMemo } from 'react'
import { Spinner } from '../Shared/Components/Spinner'
import { Alert } from "../Shared/Components/Alert"

export function SignUp() {
    const [username, setUsername] = useState()
    const [email, setEmail] = useState()
    const [password, setPassword] = useState()
    const [passwordConfirm, setPasswordConfirm] = useState()
    const [apiProgress, setApiProgress] = useState(false)
    const [successMessage, setSuccessMessage] = useState()
    const [errors, setErrors] = useState({})
    const [generalErrors, setGeneralErrors] = useState()

    useEffect(() => {
        setErrors(function (lastErrors) {
            return {
                ...lastErrors,
                username: undefined
            }
        });
    }, [username])

    useEffect(() => {
        setErrors(function (lastErrors) {
            return {
                ...lastErrors,
                email: undefined
            }
        });
    }, [email])

    useEffect(() => {
        setErrors(function (lastErrors) {
            return {
                ...lastErrors,
                password: undefined
            }
        });
    }, [password])

    const onSubmit = async (event) => {
        event.preventDefault();
        setSuccessMessage();
        setGeneralErrors();
        setApiProgress(true);

        try {
            const response = await signUp({
                username,
                email,
                password
            })
            setSuccessMessage(response.data.message)
        } catch (axsiosError) {
            console.log(axsiosError)
            if (axsiosError.response?.data) {
                if (axsiosError.response.data.status === 400) {
                    setErrors(axsiosError.response.data.validationErrors)
                } else {
                    setGeneralErrors(axsiosError.response.data.message)
                }
            } else {
                setGeneralErrors("Unexpected error occured, please try again.")
            }
        } finally {
            setApiProgress(false)
        }
    }

    const passwordConfirmError = useMemo(() => {
        if (password && password !== passwordConfirm) {
            console.log("always running")
            return "password mismatch"
        }
        return
    }, [password, passwordConfirm])

    return <>
        <div className="container pt-5">

            <form onSubmit={onSubmit}>
                <div className="mb-3 d-flex justify-content-center">
                    <h1>KAYIT OL</h1>
                </div>
                <Input id="username" error={errors.username} onChange={(event) => setUsername(event.target.value)} placeholder="Kullanıcı Adı" type="text" />
                <Input id="email" error={errors.email} onChange={(event) => setEmail(event.target.value)} placeholder="Email" type="text" />
                <Input id="password" error={errors.password} onChange={(event) => setPassword(event.target.value)} placeholder="Parola" type="password" />
                <Input id="passwordConfirm" error={passwordConfirmError} onChange={(event) => setPasswordConfirm(event.target.value)} placeholder="Parolayı Onayla" type="password" />
                <div className="mb-3 d-flex justify-content-between w-100">
                    <button disabled={password !== passwordConfirm} className="btn btn-primary text-white" type='submit'>
                        {apiProgress &&
                            (<Spinner sm />)}
                        Kayıt Ol
                    </button>
                    <a href="#">Giriş Yap</a>
                </div>
                <br></br>
                {successMessage && (<Alert>{successMessage}</Alert>)}
                {generalErrors && (<Alert type="danger">{generalErrors}</Alert>)}
            </form>
        </div >
    </>
}