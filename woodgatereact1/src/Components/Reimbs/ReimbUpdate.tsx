import axios from "axios"
import { useState } from "react"
import { Button, Container, Form } from "react-bootstrap"
import { useNavigate, useParams } from "react-router-dom"
import { store } from "../../globalData/store"


export const ReimbUpdate:React.FC = () => {

    //Define a state object to store the username and password
    const[description, setDescription] = useState(null)

    //Define a useNavigate so we can switch URLs around
    const navigate = useNavigate()

    const { reimbId } = useParams()

    //Function to store values when an input box changes
    const storeValues = (input:any) => {

        console.log(input)

        const name = "description"
        const value = input.target.value //the value in the input box 

        setDescription(value)

        //Remember the spread operator (...) lets us access the values of the object individually

        //console.log(reimb)
    }

    //Register function that sends the username/password to the backend
    const updatereimb = async (currentId:number, newDesc:string) => {
        let prejson = '{"reimbId":' + currentId + ', "description":"' + newDesc + '"}';
        let passObject = JSON.parse(prejson);
        const response = await axios.patch("http://localhost:2222/reimbs/desc", passObject)
            .then(()=>{alert("success!")})
            .catch((error)=>{alert("Failed! " + error.message)})

    }


    return(
        /* what's my and mx? this is margin for y and x axis
        we set my-5 so that we have a decent amount of space away from the top of the page
        mx-auto centers the content horizontally */
        <Container className="my-5 mx-auto">
            <div>
                <h2>enter a new description for this reimbursement</h2>

                <div>
                    <Form.Control
                        type="text"
                        placeholder="description"
                        name="description"
                        onChange={storeValues}
                    />
                </div>

                <div>
                    <Button className="btn-success m-1" onClick={() => updatereimb((reimbId as unknown) as number,(description as unknown) as string)}>submit</Button>
                    <Button className="btn-dark" onClick={()=>navigate("/reimbs")}>Back</Button>
                </div>
            </div>
        </Container>
    )

}