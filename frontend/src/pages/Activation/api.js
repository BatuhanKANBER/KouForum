import axios from "axios";

export function activateUser(token){
    return axios.patch(`/api/users/${token}/activate`)
}