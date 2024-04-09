import { activateUser } from "./api"
import { Spinner } from "../Shared/Components/Spinner"
import { Alert } from "../Shared/Components/Alert"
import { useRouteParamApiRequest } from "../Shared/Hooks/useRouteParamApiRequest"

export function Activation() {
    const { apiProgress, data, error } = useRouteParamApiRequest("token", activateUser)

    // const { token } = useParams()
    // const [apiProgress, setApiProgress] = useState()
    // const [successMessage, setSuccessMessage] = useState()
    // const [errorMessage, setErrorMessage] = useState()

    // useEffect(() => {
    //     async function activate() {
    //         setApiProgress(true)
    //         try {
    //             const response = await activateUser(token)
    //             setSuccessMessage(response.data.message)
    //         } catch (axiosError) {
    //             setErrorMessage(axiosError.response.data.message)
    //         } finally {
    //             setApiProgress(false)
    //         }
    //     }

    //     activate()
    // }, [token])

    return <>
        {apiProgress &&
            <Spinner />}
        {data && (<Alert>{data.message}</Alert>)}
        {error && (<Alert type="danger">{error}</Alert>)}

    </>
}