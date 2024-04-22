import http from "../../../../lib/http";

export function loadShareds(page = 0) {
    return http.get("/api/shareds/list", { params: { page, size: 5 } })
}