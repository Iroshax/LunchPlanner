import React, { useEffect, useState } from 'react'
import axios from "axios"
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { ToastContainer } from 'react-toastify';
import NavBar from '../common/NavBar';

export default function HomePage() {

const[sessionCache,setsessionCache] = useState([]);
const[sessionStatus,setsessionStatus] = useState([]);
const [user] = useState({
    userName:"Irosha1",
    teamName : "team1",
    email : "irosha.senevirathne@outlook.com"
})
const[session,setsession] = useState([]);

useEffect(() => {
    loadHomePage();
}, []);


function loadHomePage () {

    axios.get('http://localhost:8087/api/lunchPlanner/session/active')
    .then(response => {setsessionCache(response.data)})
    .catch(error => {console.error('Error fetching sessionCache data:', error);toast.error(`${error.response.data.Message}`);});

        axios.get('http://localhost:8087/api/lunchPlanner/session/latest')
        .then(response => {if(response.data!==undefined || response.data!==null){
            setsession(response.data);
            setsessionStatus(response.data.sessionStatus);
        }else{

            const data = {
                sessionDto: {
                    id: "",
                    sessionDetailList: "",
                    sessionName: "",
                    sessionAdmin: "",
                    pickedRestaurant: "",
                    sessionStatus: "Closed",
                    startTime: "",
                    endTime:""
                }
              };
              setsession(data);
              setsessionStatus(data.sessionStatus);

        }
    
    })
    .catch(error => {console.error('Error fetching sessionCache data:', error);toast.error(`${error.response.data.Message}`);});

    
} 


function handleButtonClick (e) { 
    if(e === "start"){
        axios.post('http://localhost:8087/api/lunchPlanner/session/initiate',user)
        .then(response => {setsessionStatus("Open");console.log(response.data);toast.info('Session started successfully');})
        .catch(error =>  toast.error(` ${error.response.data.Message}`));
    }else{
         axios.post('http://localhost:8087/api/lunchPlanner/session/end',user)
        .then(response => {setsessionStatus("Closed");loadHomePage();console.log(response.data);toast.info('Session ended successfully');})
        .catch(error =>  toast.error(`${error.response.data.Message}`));
    }
} 

  return (
    <div>
        <NavBar></NavBar>
    <div className='container'>&nbsp;
        <ToastContainer position="top-center"/>
        <h3>Welcome to Lunch Planner</h3>
    <div className='py-4'>
            {(sessionStatus==='Open' ? <h5>Today Lunch session is open now. Plase submit your Restaurant.</h5> 
                        : <h5>Please Start a session to submit restaurents.</h5>)}

    &nbsp; 
    <div className="panel panel-content">
        <div className="panel-heading">
            <div className="btn-group" role="toolbar">

                {(sessionStatus==='Open' ?
                 <div className="btn-group" role="toolbar">
                    <a className="btn btn-primary" href="/submitRestaurant">Submit Restaurant</a> 
                    <button className="btn btn-danger mx-2" onClick={e => handleButtonClick("end")}>End Session</button> 
                </div> 
                 :  <button className="btn btn-success mx-2" onClick={e => handleButtonClick("start")}>Start New Session</button>
                 )}
            </div>
            &nbsp;&nbsp;
        </div>
    </div>

    <div className='py-5'>{(sessionStatus==='Open' ? <h5>Submitted Restaurant List</h5>  :  <h5>Selected Restaurant for {session.sessionName} </h5> )}</div>

    
    {(sessionStatus==='Open' ?

            <table className="table border shadow">
            <thead>
                <tr>
                <th scope="col">User</th>
                <th scope="col">Name</th>
                <th scope="col">Location</th>
                <th scope="col">Distance</th>
                <th scope="col">Price Category</th>
                </tr>
            </thead>
            <tbody>
                {Object.entries(sessionCache).map(([key, value], index)=>(
                    <tr key={index}>
                    <th scope="row">{key}</th>
                    <td>{value.name}</td>
                    <td>{value.location}</td>
                    <td>{value.distance}</td>
                    <td>{value.priceCat}</td>
                
                    </tr>
                ))}
            </tbody>
            </table> 
            : 
            
            <table className="table border shadow">
                <thead>
                    <tr>
                    <th scope="col">Session Admin</th>
                    <th scope="col">Session Name</th>
                    <th scope="col">Selected Restaurant</th>
                    <th scope="col">Status</th>
                    </tr>
                </thead>
                <tbody>
                    <tr key="1">
                        <td>{session.sessionAdmin}</td>
                        <td>{session.sessionName}</td>
                        <td>{session.pickedRestaurant}</td>
                        <td>{session.sessionStatus}</td>
                    </tr>
                </tbody>
            </table>
           )} 
           
        </div>
    </div>
    </div>
  )
}
