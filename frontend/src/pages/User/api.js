import http from "../../lib/http"

export function getUserById(id) {
    return http.get(`/api/users/${id}`)
}