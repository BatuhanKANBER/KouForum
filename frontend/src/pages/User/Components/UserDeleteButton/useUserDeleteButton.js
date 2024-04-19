import { useCallback, useState } from "react"
import { useAuthDispatch, useAuthState } from "../../../Shared/State/context"
import { deleteUser } from "./api"
import { useNavigate } from "react-router-dom"

export function useUserDeleteButton() {
    const [apiProgress, setApiProgress] = useState(false)
    const { id } = useAuthState()
    const dispatch = useAuthDispatch()
    const navigate = useNavigate()

    const onClick = useCallback(async () => {
        const result = confirm("Are you sure?")
        if (result) {
            setApiProgress(true)
            try {
                await deleteUser(id)
                dispatch({ type: 'logout-success' })
                navigate("/login")
            } catch {

            } finally {
                setApiProgress(false)
            }
        }
    }, [id])

    return {
        apiProgress, onClick
    }
}