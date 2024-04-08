import axios from "axios";

export function loadUsers(page = 0) {
    return axios.get("/api/users/list", {params: {page , size: 5}});
}