import http from "../../../../lib/http";

export function loadShareds(page = 0) {
    return http.get("/api/shares/list", { params: { page, size: 5 } })
}

export function loadOldShares(id){
    return http.get(`/api/shares/list/${id}`)
}

export function loadNewSharesCount(id){
    return http.get(`/api/shares/list/${id}?count=true`)
}

export function loadNewShares(id){
    return http.get(`/api/shares/list/${id}?direction=after`)
}

export function deleteShare(id){
    return http.delete(`/api/shares/${id}`)
}