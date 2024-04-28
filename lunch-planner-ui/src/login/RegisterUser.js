import axios from 'axios';
import React, { useState } from 'react'
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { ToastContainer } from 'react-toastify';

export default function RegisterUser() {

    const [newUser, setNewUser] = useState({
        userName:"",
        password : "",
        firstName : "",
        lastName : "",
        email : "",
        teamName : ""
    })

    const{userName,password,firstName,lastName,email,teamName} = newUser;

    const onInputChange = (e)=>{
        setNewUser({...newUser,[e.target.name]:e.target.value})
    }

    function handleSubmit (e) { 
        e.preventDefault();


        if(newUser.firstName=== "" || newUser.firstName === undefined){
            toast.error('Error: First Name cannot be empty.');
            return;
        }else if(newUser.lastName=== "" || newUser.lastName === undefined){
            toast.error('Error: Last Name cannot be empty.');
            return;
        }else if(newUser.userName=== "" || newUser.userName === undefined){
            toast.error('Error: userName cannot be empty.');
            return;
        }else if(newUser.password=== "" || newUser.password === undefined){
            toast.error('Error: Password Category cannot be empty.');
            return;
            
        }else if(newUser.email=== "" || newUser.email === undefined){
            toast.error('Error: Email cannot be empty.');
            return;
        }else if(newUser.teamName=== "" || newUser.teamName === undefined){
            toast.error('Error: Team Name cannot be empty.');
            return;
        }else{

            const data = {
                user: {
                    userName:newUser.userName,
                    password : newUser.password,
                    firstName : newUser.firstName,
                    lastName : newUser.lastName,
                    email : newUser.email,
                    teamName : newUser.teamName
                }
              };
    
            axios.post('http://localhost:8087/api/lunchPlanner/user',data)
            .then(response =>  toast.success(`Registration Successful.`))
            .catch(error =>  toast.error(`Error: ${error.response.data.Message}`));
        }
        
    } 

  return (
    <div className='container'>
        <ToastContainer position="top-center"/>
        <div className='row'>
            <div className='col-md-6 offset-md-3 border rounded p-4 mt-2 shadow'>
                <h2 className='text-center m-4'>User Registration</h2>
                <form onSubmit={handleSubmit}>
                    <div className='mb-3'>
                        <label htmlFor='firstName' className='form-label'>
                            First Name:
                        </label>
                        <input type={'text'} className='form-control' placeholder='Enter your First Name' name='firstName' value = {firstName} onChange={(e)=>onInputChange(e)}> 
                        </input>
                    </div>
                    <div className='mb-3'>
                        <label htmlFor='lastName' className='form-label'>
                            Last Name:
                        </label>
                        <input type={'text'} className='form-control' placeholder='Enter your Last Name' name='lastName' value = {lastName} onChange={(e)=>onInputChange(e)}> 
                        </input>
                    </div>
                
                    <div className='mb-3'>
                        <label htmlFor='Name' className='form-label'>
                            Username:
                        </label>
                        <input type='text' className='form-control' placeholder='Enter Username' name='userName' value = {userName} onChange={(e)=>onInputChange(e)}> 
                        </input>
                    </div>
                    <div className='mb-3'>
                        <label htmlFor='password' className='form-label'>
                            Password:
                        </label>
                        <input type='password' className='form-control' placeholder='Enter the Password' name='password' value = {password} onChange={(e)=>onInputChange(e)}> 
                        </input>
                    </div>
                    <div className='mb-3'>
                        <label htmlFor='email' className='form-label'>
                           Email:
                        </label>
                        <input type='email' className='form-control' placeholder='Enter Email Address' name='email' value = {email} onChange={(e)=>onInputChange(e)}> 
                        </input>
                    </div>
                    <div className='mb-3'>
                        <label htmlFor='teamName' className='form-label'>
                           Team Name:
                        </label>
                        <input type='text' className='form-control' placeholder='Enter Team Name' name='teamName' value = {teamName} onChange={(e)=>onInputChange(e)}> 
                        </input>
                    </div>
                    <button type='submit' className='btn btn-outline-primary'>Submit</button>
                    <a type='submit' className='btn btn-outline-danger mx-2' href='/login'>Cancel</a>
                </form>
            </div>

        </div>
    </div>
  )
}
