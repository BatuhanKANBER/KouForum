import { useEffect, useState } from "react"
import { loadUsers } from "../api"

export function UserList() {
    const [userPage, setUser] = useState({
        content: [],
        last: false,
        first: false,
        number: 0
    })

    useEffect(() => {
        async function getUsers() {
            const response = await loadUsers()
            setUser(response.data)
        }

        getUsers()
    }, [])

    return <>
        <div>User List</div>
        {
          userPage.content.map((user) => {
            return <div>{user.username}</div>
          })
        }
    </>
}