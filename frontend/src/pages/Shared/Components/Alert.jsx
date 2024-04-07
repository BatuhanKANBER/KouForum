export function Alert(props) {
    const { children, type } = props;

    return (
        <div className={`alert alert-${type || 'success'}`} role="alert">{children}</div>
    )
}