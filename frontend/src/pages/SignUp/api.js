import http from "../../lib/http"

export function signUp(body){
    return http.post("/api/users/create", body);
}