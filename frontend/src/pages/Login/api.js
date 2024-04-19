import http from "../../lib/http"


export function login(credentials){
    return http.post("/api/auth/login", credentials);
}