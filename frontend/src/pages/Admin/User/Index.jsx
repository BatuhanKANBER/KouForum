import { getUserById } from "./api";
import { Spinner } from "../../Shared/Components/Spinner";
import { Alert } from "../../Shared/Components/Alert";
import { useRouteParamApiRequest } from "../../Shared/Hooks/useRouteParamApiRequest";

export function User() {

    const { apiProgress, data: user, error } = useRouteParamApiRequest("id", getUserById)

    // const { id } = useParams()
    // const [apiProgress, setApiProgress] = useState()
    // const [user, setUser] = useState()
    // const [errorMessage, setErrorMessage] = useState()

    // useEffect(() => {
    //     async function loadUser() {
    //         setApiProgress(true)
    //         try {
    //             const response = await getUserById(id)
    //             setUser(response.data)
    //         } catch (axiosError) {
    //             setErrorMessage(axiosError.response.data.message)
    //         } finally {
    //             setApiProgress(false)
    //         }
    //     }

    //     loadUser()
    // }, [id])

    return <>
        {apiProgress &&
            <Spinner />}
        {user && (<h1>{user.username}</h1>)}
        {error && (<Alert type="danger">{error}</Alert>)}

    </>
}