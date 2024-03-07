import './signup.css'
export function SignUp() {
    return <>
        <div class="container" id="container">
            <div class="form-container sign-up-container">
                <form action="#">
                    <input type="text" placeholder="Kullanıcı Adı" />
                    <input type="email" placeholder="Email" />
                    <input type="password" placeholder="Parola" />
                    <input type="password" placeholder="Parolayı Onayla" />
                    <button>Kayıt Ol</button>
                </form>
            </div>
            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-panel overlay-right">
                        <h1>Hoşgeldiniz!</h1>
                        <p>Aramıza katılabilmek için lütfen hesap oluşturunuz.</p>
                        <button class="ghost" id="signIn">Giriş Yap</button>
                    </div>
                </div>
            </div>
        </div>
    </>
}