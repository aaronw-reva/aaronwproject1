import axios from "axios"
import { useEffect, useState } from "react"
import { Button, Container } from "react-bootstrap"
import { ReimbTable } from "./ReimbTable"
import { useNavigate } from "react-router-dom"
import { store } from "../../globalData/store"
import { ReimbAcceptedSum } from "./ReimbAcceptedSum"

export const ReimbContainer:React.FC = () => {
    //we'll store a state object that contains an Array of PetInterfaces
    //TODO: make the pet interface, but for now, we can just use an any[]
    const[reimbs, setReimbs] = useState([])

    //Defining a useEffect that calls the function that gets pets by userId
    useEffect(()=>{
        getReimbsByUserId()
    }, []) //this useEffect triggers on component load

    const navigate = useNavigate()


    //The function that gets all pets with an axios GET request
    const getReimbsByUserId = async () => {

        //axios GET request 
        //NOTE: using the id of the loggedInUser to get only their pets
        const response = await axios.get("http://localhost:2222/reimbs/user/" + store.loggedInUser.userId)
        //TODO: then(), catch() etc

        //populate the pets state object
        setReimbs(response.data) //data holds the data send in the response body

        console.log(response.data)
    }

    const getPendingReimbsByUserId = async () => {

        //axios GET request 
        //NOTE: using the id of the loggedInUser to get only their pets
        const response = await axios.get("http://localhost:2222/reimbs/user/" + store.loggedInUser.userId + "/pending")
        //TODO: then(), catch() etc

        //populate the pets state object
        setReimbs(response.data) //data holds the data send in the response body

        console.log(response.data)
    }


    return(
        /* TODO: navbar thing? for navigation options etc */
        <Container>

            <h2>welcome {store.loggedInUser.username}</h2>
            <ReimbAcceptedSum />
            <Button className="btn-secondary" onClick={()=>navigate("/newreimb")}>submit new reimbursement</Button>

            <br/><br/>

            <Button className="btn-warning" onClick={getPendingReimbsByUserId}>pending reimbursements</Button>
            <Button className="btn-primary" onClick={getReimbsByUserId}>all reimbursements</Button>
            <ReimbTable reimbs={reimbs}></ReimbTable>

        </Container>
    )
}