import { useCallback, useEffect, useState } from "react"
import { SharesListItem } from "./SharesListItem"
import { loadNewShares, loadNewSharesCount, loadOldShares, loadShareds } from "../api"

export function SharesList() {
    const [sharePage, setSharePage] = useState({
        content: [],
        last: false
    })
    const [newShareCount, setNewShareCount] = useState(0)

    const getShares = useCallback(async (page) => {
        const response = await loadShareds(page)
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
        const response = await loadOldShares(lastShareId)
        setSharePage(previousShareds => ({
            ...response.data,
            content: [...previousShareds.content, ...response.data.content]
        }))
    }

    const getNewShares = async () => {
        const response = await loadNewShares(firstShareId);
        setSharePage(previousShareds => ({
            ...previousShareds,
            content: [...response.data, ...previousShareds.content]
        }))
    }

    const onDeleteShareSuccess = (id) => {
        setSharePage(previousShareds => ({
            ...previousShareds,
            content: previousShareds.content.filter(share => share.id !== id)
        }))
    }

    useEffect(() => {
        const getCount = async () => {
            const response = await loadNewSharesCount(firstShareId)
            setNewShareCount(response.data.count)
        }
        let looper = setInterval(() => {
            getCount()
        }, 1000)
        return function cleanup() {
            clearInterval(looper)
        }
    }, [firstShareId])

    useEffect(() => {
        getShares()
    }, [])

    return <>
        <div className="container">
            <div className="list-group">
                {newShareCount > 0 && (<button className="btn btn-secondary" onClick={() => getNewShares()}>Yeni Paylaşımlar Var...</button>)}
            </div>
            <div className="list-group">
                {
                    sharePage.content.map((share) => {
                        return <SharesListItem key={share.id} share={share} onDeleteShare={onDeleteShareSuccess} />
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