import http from "../../../../lib/http";

export function deleteUser(id){
    return http.delete(`/api/users/${id}`)
}