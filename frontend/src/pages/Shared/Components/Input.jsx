export function Input(props) {

    const {id, error, onChange, placeholder, type, defaultValue} = props

    return (
        < div className="mb-3 w-100" >
            <input 
            id={id} 
            className={error ? "form-control is-invalid" : "form-control"} 
            onChange={onChange}  
            placeholder={placeholder}
            type={type} 
            defaultValue={defaultValue}
            />
            <div className="invalid-feedback">{error}</div>
        </div >
    )
}