import React, { useState } from 'react'
import { saveUser } from '../utils/StorageUtil'
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { ToastContainer } from 'react-toastify';
import { useNavigate } from 'react-router-dom';
import logo from '../images/lunch-time.png'; 
import axios from 'axios';
/** 
* User login 
*/
export default function Login() {

    let navigate=useNavigate()
    const [loginInfo, setLoginInfo] = useState({
        userName:"",
        password : ""
    })
    const onInputChange = (e)=>{
        setLoginInfo({...loginInfo,[e.target.name]:e.target.value})
    }

    function handleSubmit (e) { 
        e.preventDefault();

        if(loginInfo.userName=== "" || loginInfo.userName === undefined){
            toast.error('Error: UserName cannot be empty.');
            return;
        }else if(loginInfo.password=== "" || loginInfo.password === undefined){
            toast.error('Error: Password cannot be empty.');
            return;
        }else{

            axios.post('http://localhost:8087/api/lunchPlanner/user/login',loginInfo)
            .then(response => {saveUser(response.data);navigate("/home");})
            .catch(error =>  toast.error(`${error.response.data.Message}`));
            
            navigate("/home");
        }  
    } 
    
    return (
       
        <div className='container'>
            <ToastContainer position="top-right"/>
            <div className='row'>

            <div><img src={logo} alt="logo" width="200" height="200"/></div>    
            <div className='col-md-6 offset-md-3 border rounded p-4 mt-2 shadow'>
                <h2 className='text-center m-4'>Sign into your account</h2>
                <form onSubmit={handleSubmit}>
                    <div className='mb-3'>
                        <label htmlFor='userName' className='form-label'>
                            User Name:
                        </label>
                        <input type={'text'} className='form-control' placeholder='Enter User Name' name='userName' value = {loginInfo.userName} onChange={(e)=>onInputChange(e)}> 
                        </input>
                    </div>
                    <div className='mb-3'>
                        <label htmlFor='password' className='form-label'>
                           Password:
                        </label>
                        <input type={'password'} className='form-control' placeholder='Enter password' name='password' value = {loginInfo.password} onChange={(e)=>onInputChange(e)}> 
                        </input>
                    </div>
                    <button type='submit' className='btn btn-outline-primary'>Login</button>
                    <a type='submit' className='btn btn-outline-success mx-4' href='/register'>Register</a>
                </form>
            </div>
        </div>
    </div>
    );
}
