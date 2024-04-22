import React from 'react';

export function SharedListItem({ shared }) {
    const dateObjectFromTimestamp = new Date(shared.date)
    const formattedDate = `${dateObjectFromTimestamp.getDate()} ${getMonthName(dateObjectFromTimestamp.getMonth())} ${dateObjectFromTimestamp.getFullYear()}, ${formatHour(dateObjectFromTimestamp.getHours())}:${formatMinute(dateObjectFromTimestamp.getMinutes())}`

    return (
        <ul className="list-group list-group-flush">
            <li className="list-group-item d-flex justify-content-between align-items-start">
                <div className="ms-2 me-auto">
                    <div className="fw-bold">Paylaşım</div>
                    <p>{shared.content}</p>
                </div>
                <span className="badge text-bg-primary rounded-pill">{formattedDate}</span>
            </li>
        </ul>
    )
}

function getMonthName(monthIndex) {
    const months = ["Ocak", "Şubat", "Mart", "Nisan", "Mayıs", "Haziran", "Temmuz", "Ağustos", "Eylül", "Ekim", "Kasım", "Aralık"]
    return months[monthIndex]
}

function formatHour(hour) {
    return hour < 10 ? `0${hour}` : hour
}

function formatMinute(minute) {
    return minute < 10 ? `0${minute}` : minute
}
