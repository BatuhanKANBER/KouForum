import React from 'react';
import { ProfileImage } from '../../../../Shared/Components/ProfileImage';
import { Link } from 'react-router-dom';

export function SharesListItem({ share }) {
    const { user, fileAttachmentDTO } = share
    const dateObjectFromTimestamp = new Date(share.date)
    const formattedDate = `${dateObjectFromTimestamp.getDate()} ${getMonthName(dateObjectFromTimestamp.getMonth())} ${dateObjectFromTimestamp.getFullYear()}, ${formatHour(dateObjectFromTimestamp.getHours())}:${formatMinute(dateObjectFromTimestamp.getMinutes())}`

    return (
        <ul className="list-group list-group-flush">
            <li className="list-group-item d-flex justify-content-between align-items-start">
                <div className="ms-2 me-auto">
                    <div className="fw-bold">
                        <h5 class="card-title">
                            <Link to={`/user/${user.id}`} className="text-dark d-flex" style={{ textDecoration: "none" }}>
                                <ProfileImage width={30} image={user.image} />
                                <p className="ml-2">{user.username}</p>
                            </Link></h5>
                    </div>
                    <div className='m-5'>
                        <p class="card-text">{share.content}</p>
                        {fileAttachmentDTO && <img width={500} className='img-thumbnail' src={`/assets/profile/${fileAttachmentDTO.name}`} />}
                    </div>
                </div>
                <span className="badge text-bg-primary rounded-pill">{formattedDate}</span>
            </li>
            <hr></hr>
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
