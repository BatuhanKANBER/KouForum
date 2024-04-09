import { Component } from "react";
import { getUserById } from "./api";
import { useParams } from 'react-router-dom'
import { Spinner } from "../../Shared/Components/Spinner";
import "./index.css"
import { Alert } from "../../Shared/Components/Alert";

export class UserClass extends Component {

    state = {
        user: null,
        apiProgress: false,
        error: null
    }

    loadUser = async () => {
        this.setState({ apiProgress: true })
        try {
            const response = await getUserById(this.props.id)
            this.setState({
                user: response.data
            })
        } catch (axiosError) {
            this.setState({
                error: axiosError.response.data.message
            })
        } finally {
            this.setState({ apiProgress: false })
        }
    }

    async componentDidMount() {
        this.loadUser();
    }

    componentDidUpdate(previousProps, previousState) {
        if (this.props.id !== previousProps.id) {
            this.loadUser()
        }
    }

    render() {
        return <>
            <div className="mt-5">
                {this.state.user && <h1>{this.state.user.username}</h1>}
                <div className="spinner">
                    {this.state.apiProgress &&
                        <Spinner />}
                </div>
                {this.state.error && <Alert type="danger">{this.state.error}</Alert>}
            </div>
        </>
    }
}


export function UserPage() {
    const { id } = useParams()
    return <>
        <UserClass id={id} />
    </>
}