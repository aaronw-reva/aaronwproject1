import axios from "axios"
import { useEffect, useState } from "react"
import { Container } from "react-bootstrap"


export const ReimbCount:React.FC = () => {
    const [count, setCount] = useState([])

    useEffect(()=>{
        getPendingCount()
    })

    const getPendingCount = async () => {
        const response = await axios.get("http://localhost:2222/reimbs/pendingcount")
        setCount(response.data)
    }

    return(
        <Container>
            <h3>there are currently {count} pending reimbursements</h3>
        </Container>
    )
}