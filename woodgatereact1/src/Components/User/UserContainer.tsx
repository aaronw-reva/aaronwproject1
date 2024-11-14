import axios from "axios"
import { useEffect, useState } from "react"
import { Container, Button } from "react-bootstrap"
import { store } from "../../globalData/store"
import { UserTable } from "./UserTable"
import { ReimbAdminTable } from "../Reimbs/ReimbAdminTable"
import { ReimbCount } from "../Reimbs/ReimbCount"
import { useNavigate } from "react-router-dom"

export const UserContainer:React.FC = () => {

    //we'll store a state object that contains an Array of PetInterfaces
    //TODO: make the pet interface, but for now, we can just use an any[]
    const[users, setUsers] = useState<any[]>([])
    const[reimbs, setReimbs] = useState([])

    const navigate = useNavigate()

    //Defining a useEffect that calls the function that gets pets by userId
    useEffect(()=>{
        getUsers()
        getReimbs();
    }, []) //this useEffect triggers on component load


    //The function that gets all pets with an axios GET request
    const getUsers = async () => {

        //axios GET request 
        //NOTE: using the id of the loggedInUser to get only their pets
        const response = await axios.get("http://localhost:2222/users")
        //TODO: then(), catch() etc

        //populate the pets state object
        setUsers(response.data) //data holds the data send in the response body

        console.log(response.data)
    }

    const getReimbs = async () => {

        //axios GET request 
        //NOTE: using the id of the loggedInUser to get only their pets
        const response = await axios.get("http://localhost:2222/reimbs")
        //TODO: then(), catch() etc

        //populate the pets state object
        setReimbs(response.data) //data holds the data send in the response body

        console.log(response.data)
    }

    const getPendingReimbs = async () => {

        //axios GET request 
        //NOTE: using the id of the loggedInUser to get only their pets
        const response = await axios.get("http://localhost:2222/reimbs/pending")
        //TODO: then(), catch() etc

        //populate the pets state object
        setReimbs(response.data) //data holds the data send in the response body

        console.log(response.data)
    }


    return(
        /* TODO: navbar thing? for navigation options etc */
        <Container>
            <Button className="btn-dark" onClick={getUsers}>refresh</Button>
            <h2>welcome {store.loggedInUser.username}</h2>
            <ReimbCount />
            
            <UserTable users={users}></UserTable> 
            <br/><br/>
            <Button className="btn-warning" onClick={getPendingReimbs}>pending reimbursements</Button>
            <Button className="btn-primary" onClick={getReimbs}>all reimbursements</Button>
            <ReimbAdminTable reimbs={reimbs}></ReimbAdminTable>

        </Container>
    )

}