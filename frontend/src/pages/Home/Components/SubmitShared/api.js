import http from "../../../../lib/http";

export function submitShared(body) {
    return http.post("/api/shares/submit", body)
}

export function submitAttachment(attachment) {
    return http.post("/api/attachments/upload", attachment)
}