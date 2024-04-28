import axios from 'axios';
import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { ToastContainer } from 'react-toastify';

export default function SubmitRestaurant() {

    let navigate=useNavigate()
    const [restaurant, setRestaurant] = useState({
        userName:"",
        resName : "",
        location : "",
        distance : "",
        priceCat : ""
    })

    const{userName,resName,location,distance,priceCat} = restaurant;

    const onInputChange = (e)=>{
        setRestaurant({...restaurant,[e.target.name]:e.target.value})
    }

    function handleSubmit (e) { 
        e.preventDefault();


        if(restaurant.resName=== "" || restaurant.resName === undefined){
            toast.error('Error: Restaurant Name cannot be empty.');
            return;
        }else if(restaurant.location=== "" || restaurant.location === undefined){
            toast.error('Error: Restaurant Location cannot be empty.');
            return;
        }else if(restaurant.distance=== "" || restaurant.distance === undefined){
            toast.error('Error: Distance cannot be empty.');
            return;
        }else if(restaurant.priceCat=== "" || restaurant.priceCat === undefined){
            toast.error('Error: Price Category cannot be empty.');
            return;
        }else{

            const data = {
                user: {
                  userName: restaurant.userName,
                  teamName: "",
                  email: ""
                },
                restaurant: {
                  name: restaurant.resName,
                  location: restaurant.location,
                  distance: restaurant.distance,
                  priceCat: restaurant.priceCat
                }
              };
    
    
            axios.post('http://localhost:8087/api/lunchPlanner/restaurant/submit',data)
            .then(response => navigate("/home"))
            .catch(error =>  toast.error(`Error: ${error.response.data.Message}`));
        }
        
    } 

  return (
    <div className='container'>
        <ToastContainer position="top-center"/>
        <div className='row'>
            <div className='col-md-6 offset-md-3 border rounded p-4 mt-2 shadow'>
                <h2 className='text-center m-4'>Submit Restaurent</h2>
                <form onSubmit={handleSubmit}>
                    <div className='mb-3'>
                        <label htmlFor='userName' className='form-label'>
                            User Name:
                        </label>
                        <input type={'text'} className='form-control' placeholder='Enter the User Name' name='userName' value = {userName} onChange={(e)=>onInputChange(e)}> 
                        </input>
                    </div>
                    <div className='mb-3'>
                        <label htmlFor='resName' className='form-label'>
                            Restaurant Name:
                        </label>
                        <input type={'text'} className='form-control' placeholder='Enter the Restaurant Name' name='resName' value = {resName} onChange={(e)=>onInputChange(e)}> 
                        </input>
                    </div>
                
                    <div className='mb-3'>
                        <label htmlFor='Name' className='form-label'>
                            Location:
                        </label>
                        <input type='text' className='form-control' placeholder='Enter location' name='location' value = {location} onChange={(e)=>onInputChange(e)}> 
                        </input>
                    </div>
                    <div className='mb-3'>
                        <label htmlFor='Name' className='form-label'>
                            Distance:
                        </label>
                        <input type='text' className='form-control' placeholder='Enter the Distance' name='distance' value = {distance} onChange={(e)=>onInputChange(e)}> 
                        </input>
                    </div>
                    <div className='mb-3'>
                        <label htmlFor='Name' className='form-label'>
                            Price Category:
                        </label>
                        <input type='text' className='form-control' placeholder='Enter the Price Category' name='priceCat' value = {priceCat} onChange={(e)=>onInputChange(e)}> 
                        </input>
                    </div>
                    <button type='submit' className='btn btn-outline-primary'>Submit</button>
                    <a type='submit' className='btn btn-outline-danger mx-2' href='/home'>Cancel</a>
                </form>
            </div>

        </div>
    </div>
  )
}
