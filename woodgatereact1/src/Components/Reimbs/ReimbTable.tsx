import { useNavigate } from "react-router-dom"
import { Button, Container, Table } from "react-bootstrap"

export const ReimbTable:React.FC<{reimbs:any[]}> = ({reimbs}) => {

    const navigate = useNavigate()

    return(
        <Container>

            <Table>
                <thead>
                    <tr>
                        <th>id</th>
                        <th>description</th>
                        <th>amount</th>
                        <th>status</th>
                        <th>update description</th>
                    </tr>
                </thead>
                <tbody>
                    {reimbs.map((reimb:any)=>(
                        <tr>
                            <td>{reimb.reimbId}</td>
                            <td>{reimb.description}</td>
                            <td>{reimb.amount}</td>
                            <td>{reimb.status}</td>
                            <td>
                            {reimb.status === 'PENDING' &&
                            <Button className="btn-dark" onClick={()=>navigate(`/updatereimb/${reimb.reimbId}`)}>update</Button>
                            }
                            </td>
                        </tr>
                    ))}
                </tbody>
            </Table>


        </Container>
    )

}