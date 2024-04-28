import React from 'react'
import logo from '../images/lunch-time.png'; 
import { getUser } from '../utils/StorageUtil'

export default function NavBar() {
  return (
    <div>
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
        <div className="container-fluid">
        <a className="navbar-brand" href="/home">
            <img src={logo} alt="logo" width="60" height="45"/>
         </a>
            <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarSupportedContent">
            <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                <li className="nav-item">
                    <a className="nav-link active" aria-current="page" href="/home">Home</a>
                </li>
                <li className="nav-item">
                    <a className="nav-link active" aria-current="page" href="/">Users</a>
                </li>
                <li className="nav-item">
                    <a className="nav-link active" aria-current="page" href="/sessionDetails">Past Sessions</a>
                </li>
            </ul>
            <span className="navbar-text active">
                Signed in as:  {getUser().userName} &nbsp;
            <a href='/logout' className="btn btn-outline-secondary">Logout</a>
            </span>
        </div>
        </div>
        </nav>
    </div>
  )
}
