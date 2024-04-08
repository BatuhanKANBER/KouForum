import axios from "axios";

export function loadUsers(body) {
    return axios.get("/api/users/list", body);
}