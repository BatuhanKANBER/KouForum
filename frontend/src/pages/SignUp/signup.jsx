import { useEffect, useState } from 'react'
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
        <div className="container pt-5">
            <div className="card">
                <div className="card-body">
                    <form onSubmit={onSubmit}>
                        <div className="mb-3 d-flex justify-content-center">
                            <h3>KAYIT OL</h3>
                        </div>
                        <div className="mb-3">
                            <input id="username" className={errors.username ? "form-control is-invalid" : "form-control is-valid"} onChange={(event) => setUsername(event.target.value)} type="text" placeholder="Kullanıcı Adı" />
                            <div className="invalid-feedback">{errors.username}</div>
                        </div>
                        <div className="mb-3">

                            <input id="email" className="form-control" onChange={(event) => setEmail(event.target.value)} type="email" placeholder="Email" />
                        </div>  <div className="mb-3">

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
                        {successMessage && <div className="alert alert-success" role="alert">{successMessage}</div>}
                    </form>
                </div>
            </div>
        </div>

        {/* <div className="card">
            <div className="card-body">
                <div className="card-header d-flex justify-content-center">
                    KAYIT OL
                </div>
                <form onSubmit={onSubmit} className=''>
                    <div className="mb-3">

                        <input id="username" className={errors.username ? "form-control is-invalid" : "form-control is-valid"} onChange={(event) => setUsername(event.target.value)} type="text" placeholder="Kullanıcı Adı" />
                        <div className="invalid-feedback">{errors.username}</div>
                    </div>
                    <div className="mb-3">

                        <input id="email" onChange={(event) => setEmail(event.target.value)} type="email" placeholder="Email" />
                    </div>  <div className="mb-3">

                        <input id="password" onChange={(event) => setPassword(event.target.value)} type="password" placeholder="Parola" />
                    </div>  <div className="mb-3">

                        <input id="passwordConfirm" onChange={(event) => setPasswordConfirm(event.target.value)} type="password" placeholder="Parolayı Onayla" />
                    </div>  <div className="mb-3">

                        <button disabled={apiProgress || (password != passwordConfirm || !password)} type='submit'>
                            {apiProgress && <span className="spinner-border spinner-border-sm" aria-hidden="true"></span>}
                            Kayıt Ol
                        </button>
                    </div>
                    <br></br>
                    {successMessage && <div className="alert alert-success" role="alert">{successMessage}</div>}
                </form>
            </div>
        </div> */}

    </>
}