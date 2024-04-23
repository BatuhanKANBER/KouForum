import { useCallback, useEffect, useState } from "react"
import { loadNewSharesCountForUser, loadNewSharesForUser, loadOldSharesForUser, loadSharesForUser } from "./api"
import { SharesListItem } from "../../../Home/Components/ListShared/Components/SharesListItem"
import { useParams } from "react-router-dom"


export function SharesForUserList() {
    const user = useParams()
    const [newShareCount, setNewShareCount] = useState(0)

    const [sharePage, setSharePage] = useState({
        content: [],
        last: false
    })

    const getShares = useCallback(async (page) => {

        const response = await loadSharesForUser(user.id, page)
        setSharePage(previousShareds => ({
            ...response.data,
            content: [...previousShareds.content, ...response.data.content]
        }))
    }, [])

    let lastShareId = 0
    let firstShareId = 0
    if (sharePage.content.length > 0) {
        firstShareId = sharePage.content[0].id
        const lastShareIndex = sharePage.content.length - 1
        lastShareId = sharePage.content[lastShareIndex].id
    }

    const getOldShares = async () => {
        const response = await loadOldSharesForUser(user.id, lastShareId)
        setSharePage(previousShareds => ({
            ...response.data,
            content: [...previousShareds.content, ...response.data.content]
        }))
    }

    const getNewShares = async () => {
        const response = await loadNewSharesForUser(user.id, firstShareId);
        setSharePage(previousShareds => ({
            ...previousShareds,
            content: [...response.data, ...previousShareds.content]
        }))
    }

    useEffect(() => {
        const getCount = async () => {
            const response = await loadNewSharesCountForUser(user.id, firstShareId)
            setNewShareCount(response.data.count)
        }
        let looper = setInterval(() => {
            getCount()
        }, 1000)
        return function cleanup() {
            clearInterval(looper)
        }
    }, [firstShareId, user.id])

    useEffect(() => {
        getShares()
    }, [])

    return <>
        <div className="container">
            <div className="list-group">
                {newShareCount > 0 && (<button className="btn btn-secondary" onClick={() => getNewShares()}>Kullanıcının Yeni Paylaşımları Var</button>)}
            </div>
            <div className="list-group">
                {
                    sharePage.content.map((share) => {
                        return <SharesListItem key={share.id} share={share} />
                    })
                }
            </div>
            <div className="card-footer text-body-secondary mt-2">
                <nav aria-label="Page navigation example">
                    <ul className="pagination justify-content-center">
                        <li className={sharePage.last ? "page-item disabled" : "page-item"}>
                            {<button className="page-link" onClick={() => getOldShares()}>Daha Fazla</button>}
                        </li>
                    </ul>
                </nav>
            </div>
        </div >
    </>
}