import defaultProfileImage from "../../../assets/default_user.png"

export function ProfileImage({ width, tempImage, image }) {

    const profileImage = image ? `/assets/profile/${image}` : defaultProfileImage

    return <>
        <img
            className="rounded-circle"
            src={tempImage || profileImage}
            width={width}
            height={width}
            onError={({ target }) => {
                target.src = defaultProfileImage
            }}
        ></img>
    </>
}