//This is sort of a placeholder component - admins get navigated to this after login
//In your P1 this is probably where admins could see all users, update, delete etc.

import axios from "axios";
import { store } from "../../globalData/store"
import { useEffect, useState } from "react"
import { Button, Container, Table } from "react-bootstrap";

//And maybe there's a navbar or some button that navigates to the admin reimbursement manager
export const UserTable:React.FC<{users:any[]}> = ({users}) => {

    //set the child state to be the array of users from the parent
    const [childUsers, setUsers] = useState<any[]>(users);

    //then we get the users from the DB with the child's own get all users request
    useEffect(()=>{
        getUsers()
    })

    const deleteUser = async (id:number) => {
        const response = await axios.delete("http://localhost:2222/users/" + id)
        .then((response) =>{
            alert("deleted")}
        )
        .then(()=>{
            getUsers()
        })
    }

    const updateRole = async(id:number) => {
        const response = await axios.patch("http://localhost:2222/users/" + id)
            .then(()=>{alert("success!")})
            .catch((error)=>{alert("Failed! " + error.message)})
    }

    const getUsers = async () => {
        const userData = await axios.get("http://localhost:2222/users")
        .then((userData) => {
            setUsers(userData.data)
        })
    }

    return(
        <Container>
            <Table>
                <thead>
                    <tr>
                        <th>user id</th>
                        <th>first name</th>
                        <th>last name</th>
                        <th>username</th>
                        <th>role</th>
                        <th>actions</th>
                    </tr>
                </thead>
                <tbody>
                    {childUsers.map((user:any)=>(
                        <tr>
                            <td>{user.userId}</td>
                            <td>{user.firstname}</td>
                            <td>{user.lastname}</td>
                            <td>{user.username}</td>
                            <td>{user.role}</td>
                            <td>
                                {user.role === 'employee' &&
                                <Button className="btn-warning" onClick={() =>updateRole(user.userId)}>promote</Button>
                                }
                                {user.userId != store.loggedInUser.userId &&
                                <Button className="btn-danger" onClick={() =>deleteUser(user.userId)}>delete</Button>
                                }
                                
                            </td>
                        </tr>
                    ))}
                </tbody>
            </Table>

        </Container>
    )

}