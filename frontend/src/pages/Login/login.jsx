import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { Spinner } from '../Shared/Components/Spinner'
import { Input } from '../Shared/Components/Input';
import { login } from './api';
import { Alert } from '../Shared/Components/Alert';

export function Login() {

    const [apiProgress, setApiProgress] = useState(false)
    const [email, setEmail] = useState()
    const [password, setPassword] = useState()
    const [errors, setErrors] = useState({})
    const [generalErrors, setGeneralErrors] = useState()


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
        setGeneralErrors();
        setApiProgress(true);

        try {
            await login({ email, password })
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
        <div className="container mt-5">
            <form onSubmit={onSubmit}>
                <div className="mb-3 d-flex justify-content-center">
                    <h1>GİRİŞ YAP</h1>
                </div>
                <Input id="email" error={errors.email} onChange={(event) => setEmail(event.target.value)} placeholder="Email" type="text" />
                <Input id="password" error={errors.password} onChange={(event) => setPassword(event.target.value)} placeholder="Parola" type="password" />
                <div className="mb-3 d-flex justify-content-between w-100">
                    <button className="btn btn-primary text-white" type='submit'>
                        {apiProgress &&
                            (<Spinner sm />)}
                        Giriş Yap
                    </button>
                    <Link to="/SignUp"> Kayıt Ol</Link>
                </div>
                {generalErrors && (<Alert type="danger">{generalErrors}</Alert>)}
            </form>
        </div >
    </>
}