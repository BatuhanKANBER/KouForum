import axios from "axios";

export function login(credentials){
    return axios.post("/api/auth/check", credentials);
}