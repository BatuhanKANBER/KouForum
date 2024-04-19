import defaultProfileImage from "../../../assets/default_user.png"

export function ProfileImage({ width }) {
    return <>
        <img src={defaultProfileImage} width={width}></img>
    </>
}