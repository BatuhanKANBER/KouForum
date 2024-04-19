import { Spinner } from "../../../Shared/Components/Spinner"
import { useUserDeleteButton } from "./useUserDeleteButton"

export function UserDeleteButton() {
    const { apiProgress, onClick } = useUserDeleteButton()

    return <>
        <button apiProgress={apiProgress} onClick={onClick} className="btn btn-danger text-white w-100" type='submit'>
            {apiProgress &&
                (<Spinner sm />)}
            Sil
        </button>
    </>
}