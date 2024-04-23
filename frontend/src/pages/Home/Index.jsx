import { useAuthState } from "../Shared/State/context"
import { Shareds } from "./Components/ListShared/Index"
import { SubmitShared } from "./Components/SubmitShared/Index"

export function Home() {
    const authState = useAuthState()

    return <>
        <div className="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 className="h3 mb-0 text-gray-800">Ana Sayfa</h1>
        </div>
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