import './login.css'
export function Login() {
    return <>
        <div class="container" id="container">
            <div class="form-container sign-in-container">
                <form action="#">
                    <input type="email" placeholder="Email" />
                    <input type="password" placeholder="Parola" />
                    <a href="#">Şifremi Unuttum!</a>
                    <button>Giriş Yap</button>
                </form>
            </div>
            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-panel overlay-right">
                        <h1>Seni Görmek Ne Güzel!</h1>
                        <p>Kullanıcı bilgilerini doldur ve KouForum macerana başla.</p>
                        <button class="ghost" id="signUp">Kayıt Ol</button>
                    </div>
                </div>
            </div>
        </div>
    </>
}