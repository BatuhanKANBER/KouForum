import { useCallback, useEffect, useState } from "react"
import { SharedListItem } from "./SharedsListItem"
import { loadShareds } from "../api"

export function SharedsList() {
    const [sharedPage, setSharedPage] = useState({
        content: [],
        last: false,
        first: false,
        number: 0
    })

    const getShareds = useCallback(async (page) => {
        const response = await loadShareds(page)
        setSharedPage(previousShareds => ({
            ...response.data,
            content: [...previousShareds.content, ...response.data.content]
        }))
    }, [])

    useEffect(() => {
        getShareds()
    }, [])

    return <>
        <div className="container">

            <div className="list-group">
                {
                    sharedPage.content.map((shared) => {
                        return <SharedListItem key={shared.id} shared={shared} />
                    })
                }
            </div>
            <div className="card-footer text-body-secondary mt-2">
                <nav aria-label="Page navigation example">
                    <ul className="pagination justify-content-center">
                        <li className={sharedPage.last ? "page-item disabled" : "page-item"}>
                            {<button className="page-link" onClick={() => getShareds(sharedPage.number + 1)}>Daha Fazla</button>}
                        </li>
                    </ul>
                </nav>
            </div>
        </div >
    </>
}