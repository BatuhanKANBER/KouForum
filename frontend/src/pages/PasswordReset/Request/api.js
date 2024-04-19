import http from ".././../../lib/http"


export function passwordResetRequest(body) {
    return http.post("/api/users/password-reset", body);
}