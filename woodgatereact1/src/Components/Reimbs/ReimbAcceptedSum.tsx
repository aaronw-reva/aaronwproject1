import axios from "axios"
import { useEffect, useState } from "react"
import { Container } from "react-bootstrap"
import { store } from "../../globalData/store"

export const ReimbAcceptedSum:React.FC = () => {
    const [total, setTotal] = useState([])

    useEffect(()=>{
        getApprovedTotal()
    }, [])

    const getApprovedTotal = async () => {
        const response = await axios.get("http://localhost:2222/reimbs/total/" + store.loggedInUser.userId)
        setTotal(response.data)
    }

    return(
        <Container>
            <h3>you have received {total} in reimbursements</h3>
        </Container>
    )
}