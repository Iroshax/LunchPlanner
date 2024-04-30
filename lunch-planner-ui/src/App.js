
import './App.css';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css"
import HomePage from './sessions/HomePage';
import Login from './login/Login';
import Logout from './login/Logout';
import SessioonDetails from './sessionDetails/SessionDetails';
import SubmitRestaurant from './sessions/SubmitRestaurant';
import PrivateRoute from './common/PrivateRoute';
import {BrowserRouter as Router, Routes,Route} from 'react-router-dom'
import RegisterUser from './login/RegisterUser';
import UserDetails from './users/UserDetails';



function App() {
  return (
    <div className="App">
<Router>
  <Routes>
        <Route path='/' element={<Login></Login>}/>
        <Route path='/login' element={<Login></Login>}/>
        <Route path='/register' element={<RegisterUser></RegisterUser>}/>
        <Route path='/logout' element={<Logout></Logout>}/>
        <Route path="/home" element={<PrivateRoute component={HomePage}/>} />
        <Route path="/userDetails" element={<PrivateRoute component={UserDetails}/>} />
        <Route path="/sessionDetails" element={<PrivateRoute component={SessioonDetails}/>} />
        <Route path="/submitRestaurant" element={<PrivateRoute component={SubmitRestaurant}/>} />
       
  </Routes>
  </Router>
    </div>
  );
}

export default App;
