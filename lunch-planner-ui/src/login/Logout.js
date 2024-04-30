import React, { useEffect } from 'react';
import { removeUser } from '../utils/StorageUtil';
import { Navigate } from 'react-router-dom'

  export default function Logout() {

    useEffect(() => {
        removeUser();
    }, []);
  
    return (
        <Navigate to={{pathname: "/login", state: { from: window.location }}} />
    ) 
  }
  
 
