import { Input } from "../../Shared/Components/Input";
import { Spinner } from "../../Shared/Components/Spinner";
import { Alert } from "../../Shared/Components/Alert";
import { useSetPassword } from "./useSetPassword";

export function SetPassword() {
    const { apiProgress, errors, generalErrors, onChangePassword, onChangePasswordRepeat, onSubmit, success, disabled } = useSetPassword();

    return <>
        <div className="container mt-5">
            <form onSubmit={onSubmit}>
                <div className="mb-3 d-flex justify-content-center">
                    <h1>ŞİFRENİ YENİLE</h1>
                </div>
                <Input id="password" error={errors.password} onChange={onChangePassword} placeholder="Parola" type="password" />
                <Input id="passwordConfirm" error={errors.passwordRepeat} onChange={onChangePasswordRepeat} placeholder="Parolayı Onayla" type="password" />
                <div className="mb-3 d-flex justify-content-between w-100">
                    <button disabled={disabled} className="btn btn-primary text-white" type='submit'>
                        {apiProgress &&
                            (<Spinner sm />)}
                        Kayıt Ol
                    </button>
                </div>
                <br></br>
                {success && (<Alert>{success}</Alert>)}
                {generalErrors && (<Alert type="danger">{generalErrors}</Alert>)}
            </form>
        </div >
    </>
}