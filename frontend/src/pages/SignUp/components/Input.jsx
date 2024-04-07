export function Input(props) {

    const {id, error, onChange, placeholder, type} = props

    return (
        < div className="mb-3 w-100" >
            <input 
            id={id} 
            className={error ? "form-control is-invalid" : "form-control"} 
            onChange={onChange}  
            placeholder={placeholder}
            type={type} />
            <div className="invalid-feedback">{error}</div>
        </div >
    )
}