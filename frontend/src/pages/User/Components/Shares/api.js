import http from "../../../../lib/http";

export function loadSharesForUser(id, page = 0) {
    return http.get(`/api/shares/user/${id}/list`, { params: { page, size: 5 } })
}

export function loadOldSharesForUser(id, shareId) {
    return http.get(`/api/shares/user/${id}/list/${shareId}`)
}

export function loadNewSharesCountForUser(id, shareId) {
    return http.get(`/api/shares/user/${id}/list/${shareId}?count=true`)
}

export function loadNewSharesForUser(id, shareId) {
    return http.get(`/api/shares/user/${id}/list/${shareId}?direction=after`)
}