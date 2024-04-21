import { getUserById } from "./api";
import { Spinner } from "../Shared/Components/Spinner";
import { Alert } from "../Shared/Components/Alert";
import { useRouteParamApiRequest } from "../Shared/Hooks/useRouteParamApiRequest";
import { ProfileCard } from "./Components/ProfileCard";

export function User() {

    const { apiProgress, data: user, error } = useRouteParamApiRequest("id", getUserById)

    return <>
        {apiProgress &&
            <Spinner />}
        {user && <ProfileCard user={user} />}
        {error && (<Alert type="danger">{error}</Alert>)}

    </>
}