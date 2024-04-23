import { useAuthState } from "../Shared/State/context"
import { Shareds } from "./Components/ListShared/Index"
import { SubmitShared } from "./Components/SubmitShared/Index"

export function Home() {
    const authState = useAuthState()

    return <>
        <div className="row">
            <div className="col-md-12">
                {authState.id > 0 && <SubmitShared />}
            </div>
            <div className="col-md-12">
                <Shareds />
            </div>
        </div>

    </>
}