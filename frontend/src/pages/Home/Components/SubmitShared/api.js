import http from "../../../../lib/http";

export function submitShared(body) {
    return http.post("/api/shareds/submit", body)
}