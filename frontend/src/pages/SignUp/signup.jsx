import { useEffect, useState } from 'react'
import './signup.css'
import { signUp } from './api'
export function SignUp() {
    const [username, setUsername] = useState()
    const [email, setEmail] = useState()
    const [password, setPassword] = useState()
    const [passwordConfirm, setPasswordConfirm] = useState()
    const [apiProgress, setApiProgress] = useState(false)
    const [successMessage, setSuccessMessage] = useState()
    const [errors, setErrors] = useState({})

    useEffect(() => {
        setErrors({})
    }, [username])

    const onSubmit = async (event) => {
        event.preventDefault();
        setSuccessMessage();
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
            }
        } finally {
            setApiProgress(false)
        }
    }


    return <>
        <div className="container" id="container">

            <div className="form-container sign-up-container">

                <form onSubmit={onSubmit}>
                    <input id="username" className={errors.username ? "form-control is-invalid" : "form-control is-valid"} onChange={(event) => setUsername(event.target.value)} type="text" placeholder="Kullanıcı Adı" />
                    <div className="invalid-feedback">{errors.username}</div>
                    <input id="email" onChange={(event) => setEmail(event.target.value)} type="email" placeholder="Email" />
                    <input id="password" onChange={(event) => setPassword(event.target.value)} type="password" placeholder="Parola" />
                    <input id="passwordConfirm" onChange={(event) => setPasswordConfirm(event.target.value)} type="password" placeholder="Parolayı Onayla" />
                    <button disabled={apiProgress || (password != passwordConfirm || !password)} type='submit'>
                        {apiProgress && <span className="spinner-border spinner-border-sm" aria-hidden="true"></span>}
                        Kayıt Ol
                    </button>
                    <br></br>
                    {successMessage && <div className="alert alert-success" role="alert">{successMessage}</div>}
                </form>
            </div>
            <div className="overlay-container">
                <div className="overlay">
                    <div className="overlay-panel overlay-right">
                        <h1>Hoşgeldiniz!</h1>
                        <p>Aramıza katılabilmek için lütfen hesap oluşturunuz.</p>
                        <button className="ghost" id="signIn">Giriş Yap</button>
                    </div>
                </div>
            </div>
        </div>
    </>
}