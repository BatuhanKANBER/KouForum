import { useCallback, useEffect, useState } from "react"
import { loadUsers } from "../api"
import { UserTableItem } from "./UserListItem"

export function UserList() {

    const [userPage, setUserPage] = useState({
        content: [],
        last: false,
        first: false,
        number: 0
    })

    const getUsers = useCallback(async (page) => {
        const response = await loadUsers(page)
        setUserPage(response.data)
    }, [])

    useEffect(() => {
        getUsers()
    }, [])


    return <>
        <div className="container">
            <div className="card">
                <div className="card-header d-flex justify-content-between">
                    <b>#</b><b>Username</b><b>Email</b>
                </div>
                <div className="list-group">
                    {
                        userPage.content.map((user) => {
                            return <UserTableItem key={user.id} user={user} />
                        })
                    }
                </div>
                <div className="card-footer text-body-secondary">
                    <nav aria-label="Page navigation example">
                        <ul className="pagination">
                            <li className="page-item">{<button disabled={userPage.first} class="page-link" onClick={() => getUsers(userPage.number - 1)}>Previous</button>}</li>
                            <li className="page-item">{<button disabled={userPage.last} class="page-link" onClick={() => getUsers(userPage.number + 1)}>Next</button>}</li>
                        </ul>
                    </nav>
                </div>
            </div >
        </div>
    </>
}