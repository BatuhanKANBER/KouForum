import { UserList } from "./Components/UserList";

export function Users() {
    return <>
        <div className="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 className="h3 mb-0 text-gray-800">Kullanıcılar</h1>
        </div>
        <div className="mt-5">
            <UserList />
        </div>
    </>
}