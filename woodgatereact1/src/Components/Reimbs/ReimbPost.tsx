import axios from "axios"
import { useState } from "react"
import { Button, Container, Form } from "react-bootstrap"
import { useNavigate } from "react-router-dom"
import { store } from "../../globalData/store"


export const ReimbPost:React.FC = () => {

    //Define a state object to store the username and password
    const[reimb, setReimb] = useState({
        amount:"",
        description:"",
        userId:store.loggedInUser.userId,
    })

    //Define a useNavigate so we can switch URLs around
    const navigate = useNavigate()

    //Function to store values when an input box changes
    const storeValues = (input:any) => {

        console.log(input)

        const name = input.target.name //the name of the input box that changed
        const value = input.target.value //the value in the input box 

        //input = the entire event (which got passed in as an argument)
        //target = the specific input box that triggered the onChange event
        //name/value = the name/value of the input box

        //annoying syntax - We need to send the entire user object to make a change to one field
        //"Take whatever input was changed, and set the matching field in user to the value in the input"
        //[name] can be EITHER username or password here. Flexibility!
        setReimb((reimb) => ({...reimb, [name]:value}))

        //Remember the spread operator (...) lets us access the values of the object individually

        console.log(reimb)
    }

    //Register function that sends the username/password to the backend
    const postreimb = async () => {

        //TODO: check username/password are present

        //POST REQUEST - send the new user info to the backend
        const response = await axios.post("http://localhost:2222/reimbs", reimb)
        .then(()=>{alert("success!")})
        .catch((error)=>{alert("Failed! " + error.message)})

    }


    return(
        /* what's my and mx? this is margin for y and x axis
        we set my-5 so that we have a decent amount of space away from the top of the page
        mx-auto centers the content horizontally */
        <Container className="my-5 mx-auto">
            <div>
                <h2>fill out the form to submit a reimbursement for review</h2>

                <div>
                    <Form.Control
                        type="number"
                        placeholder="0"
                        name="amount"
                        onChange={storeValues}
                    />
                </div>
                <div>
                    <Form.Control
                        type="text"
                        placeholder="description"
                        name="description"
                        onChange={storeValues}
                    />
                </div>

                <div>
                    <Button className="btn-success m-1" onClick={postreimb}>submit</Button>
                    <Button className="btn-dark" onClick={()=>navigate("/reimbs")}>Back</Button>
                </div>
            </div>
        </Container>
    )

}