import React from 'react';
import { ProfileImage } from '../../../../Shared/Components/ProfileImage';
import { Link } from 'react-router-dom';
import { useAuthState } from '../../../../Shared/State/context';
import { deleteShare } from '../api';


export function SharesListItem({ share, onDeleteShare }) {
    const { user, fileAttachmentDTO } = share
    const dateObjectFromTimestamp = new Date(share.date)
    const formattedDate = `${dateObjectFromTimestamp.getDate()} ${getMonthName(dateObjectFromTimestamp.getMonth())} ${dateObjectFromTimestamp.getFullYear()}, ${formatHour(dateObjectFromTimestamp.getHours())}:${formatMinute(dateObjectFromTimestamp.getMinutes())}`
    const authState = useAuthState()
    const isLogged = authState.id === share.user.id

    const onClickDelete = async () => {
        const result = confirm("Are you sure?")
        if (result) {
            await deleteShare(share.id)
            onDeleteShare(share.id)
        }
    }

    return (
        <ul className="list-group list-group-flush">
            <li className='list-group-item'>
                <div className="row fw-bold">
                    <div className='col-6'>
                        <Link to={`/user/${user.id}`} className="text-dark d-flex" style={{ textDecoration: "none" }}>
                            <ProfileImage width={30} image={user.image} />
                            <p className="ml-2">{user.username}</p>
                        </Link>
                    </div>
                    <div className='col-6 d-flex flex-row-reverse bd-highlight'>
                        {isLogged && <button onClick={onClickDelete} className="btn-delete-link">X</button>}
                        <div className='m-2'>
                            <span className="badge text-bg-secondary">{formattedDate}</span>
                        </div>
                    </div>
                </div>
            </li>
            <li className="list-group-item d-flex justify-content-between align-items-start">
                <div className="ms-2 me-auto">
                    <div className='row m-5 d-flex justify-content-center'>
                        <div className="col-12 mb-2">
                            <p class="card-text">{share.content}</p>
                        </div>
                        <div className="col-12">
                            {fileAttachmentDTO && <img width={500} className='img-thumbnail' src={`/assets/profile/${fileAttachmentDTO.name}`} />}
                        </div>

                    </div>
                </div>
            </li>
            <br></br>
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
