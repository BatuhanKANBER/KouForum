import { useState } from 'react'
import axios from 'axios'
import './signup.css'
export function SignUp() {
    const [username, setUsername] = useState()
    const [email, setEmail] = useState()
    const [password, setPassword] = useState()
    const [passwordConfirm, setPasswordConfirm] = useState()

    console.log(username)
    console.log(email)
    console.log(password)
    console.log(passwordConfirm)

    const onSubmit = (event) =>{
        event.preventDefault();
       axios.post("/api/users/create", {
        username,
        email,
        password
       })
    }


    return <>
        <div className="container" id="container">
            <div className="form-container sign-up-container">
                <form onSubmit={onSubmit}>
                    <input id="username" onChange={(event) => setUsername(event.target.value)} type="text" placeholder="Kullanıcı Adı" />
                    <input id="email" onChange={(event) => setEmail(event.target.value)} type="email" placeholder="Email" />
                    <input id="password" onChange={(event) => setPassword(event.target.value)} type="password" placeholder="Parola" />
                    <input id="passwordConfirm" onChange={(event) => setPasswordConfirm(event.target.value)} type="password" placeholder="Parolayı Onayla" />
                    <button disabled={password!=passwordConfirm && password!=null} type='submit'>Kayıt Ol</button>
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