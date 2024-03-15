import { useEffect, useState } from 'react'
import { signUp } from './api'
import { Input } from './components/Input'
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
            if (axsiosError.response?.data && axsiosError.response.data.status === 400) {
                setErrors(axsiosError.response.data.validationErrors)
            }else{
                setGeneralErrors("Unexpected error occured, please try again.")
            }
        } finally {
            setApiProgress(false)
        }
    }


    return <>
        <div className="container pt-5">
            <div className="card">
                <div className="card-body">
                    <form onSubmit={onSubmit}>
                        <div className="mb-3 d-flex justify-content-center">
                            <h3>KAYIT OL</h3>
                        </div>
                        <Input id="username" error={errors.username} onChange={(event) => setUsername(event.target.value)} placeholder="Kullanıcı Adı" />
                        <Input id="email" error={errors.email} onChange={(event) => setEmail(event.target.value)} placeholder="Email" />

                        <div className="mb-3">

                            <input id="password" className="form-control" onChange={(event) => setPassword(event.target.value)} type="password" placeholder="Parola" />
                        </div>  <div className="mb-3">

                            <input id="passwordConfirm" className="form-control" onChange={(event) => setPasswordConfirm(event.target.value)} type="password" placeholder="Parolayı Onayla" />
                        </div>  <div className="mb-3">

                            <button className="btn btn-primary text-white" disabled={apiProgress || (password != passwordConfirm || !password)} type='submit'>
                                {apiProgress && <span className="spinner-border spinner-border-sm" aria-hidden="true"></span>}
                                Kayıt Ol
                            </button>
                        </div>
                        <br></br>
                        {successMessage && (<div className="alert alert-success" role="alert">{successMessage}</div>)}
                        {generalErrors && (<div className="alert alert-danger" role="alert">{generalErrors}</div>)}
                    </form>
                </div>
            </div>
        </div>
    </>
}