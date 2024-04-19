import { Link } from "react-router-dom";
import { usePasswordResetRequest } from "./usePasswordResetRequest";
import { Input } from "../../Shared/Components/Input";
import { Spinner } from "../../Shared/Components/Spinner";
import { Alert } from "../../Shared/Components/Alert";

export function PasswordReset() {
    const { onSubmit, onChangeEmail, apiProgress, success, error, generalErrors } = usePasswordResetRequest();
    return <>
        <div className="container mt-5">
            <form onSubmit={onSubmit}>
                <div className="mb-3 d-flex justify-content-center">
                    <h1>MAIL GÖNDER</h1>
                </div>
                <div className="mb-3 d-flex justify-content-center">
                    <p>Yenileme maili alabilmek için lütfen e-posta adresinizi giriniz.</p>
                </div>
                <Input id="email" error={error} onChange={onChangeEmail} placeholder="Email" type="text" />
                <div className="mb-3 d-flex justify-content-between w-100">
                    <button className="btn btn-primary text-white" type='submit'>
                        {apiProgress &&
                            (<Spinner sm />)}
                        Gönder
                    </button>
                    <Link to="/login">Giriş Yap</Link>
                </div>
                {success && (<Alert>{success}</Alert>)}
                {generalErrors && (<Alert type="danger">{generalErrors}</Alert>)}
            </form>
        </div >
    </>
}