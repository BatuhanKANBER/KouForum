import axios from "axios";

export function getUserById(id) {
    return axios.get(`/api/users/${id}`)
}