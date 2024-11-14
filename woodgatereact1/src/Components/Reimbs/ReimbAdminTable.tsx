import axios from "axios"
import { useState } from "react"
import { useNavigate } from "react-router-dom"
import { Container, Table, Button } from "react-bootstrap"

export const ReimbAdminTable:React.FC<{reimbs:any[]}> = ({reimbs}) => {
    
    const navigate = useNavigate()

    const updateStatus = async(currentId:number, newStatus:string) => {
        let prejson = '{"reimbId":' + currentId + ', "status":"' + newStatus + '"}';
        let passObject = JSON.parse(prejson);
        const response = await axios.patch("http://localhost:2222/reimbs/status", passObject)
            .then()
            .catch((error)=>{alert("Failed! " + error.message)})
    }

    return(
        <Container>

            <Table>
                <thead>
                    <tr>
                        <th>first name</th>
                        <th>last name</th>
                        <th>username</th>
                        <th>description</th>
                        <th>amount</th>
                        <th>status</th>
                        <th>actions</th>
                    </tr>
                </thead>
                <tbody>
                    {reimbs.map((reimb:any)=>(
                        <tr>
                            <td>{reimb.user.firstname}</td>
                            <td>{reimb.user.lastname}</td>
                            <td>{reimb.user.username}</td>
                            <td>{reimb.description}</td>
                            <td>{reimb.amount}</td>
                            <td>{reimb.status}</td>
                            <td>
                            {reimb.status === 'PENDING' &&
                            <div><Button className="btn-success" onClick={() =>updateStatus(reimb.reimbId, "APPROVED")}>approve</Button> <Button className="btn-danger" onClick={() =>updateStatus(reimb.reimbId, "DENIED")}>deny</Button></div>
                            }
                            </td>
                        </tr>
                    ))}
                </tbody>
            </Table>


        </Container>
    )

}