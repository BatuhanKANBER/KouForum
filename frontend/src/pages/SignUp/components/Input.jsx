export function Input(props) {

    const {id, error, onChange, placeholder, type} = props

    return (
        < div className="mb-3" >
            <input 
            id={id} 
            className={error ? "form-control is-invalid" : "form-control is-valid"} 
            onChange={onChange}  
            placeholder={placeholder}
            type={type} />
            <div className="invalid-feedback">{error}</div>
        </div >
    )
}