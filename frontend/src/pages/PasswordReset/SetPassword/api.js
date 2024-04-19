import http from "../../../lib/http";

export function resetPassword(token, body) {
    return http.patch(`/api/users/${token}/password`, body);
}