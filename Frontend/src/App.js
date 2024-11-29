import { useEffect } from "react";
import { getDepartment } from "./store/departmentReducer";
import Home from "./Components/Home/Home";
import { BrowserRouter,Routes,Route, Navigate } from "react-router-dom";
import EmpList from "./Components/Emp_list/EmpList";
import Create from "./Components/Create/Create";
import Auth from "./Components/Auth/Auth"
import { getUserProfile } from '../src/store/AuthReducer';

import { useDispatch, useSelector } from 'react-redux'



function App() {
  const dispatch=useDispatch();
  const {auth}=useSelector(store=>store);


  useEffect(()=>{
    dispatch(getUserProfile(localStorage.getItem("jwt")))

  },[auth.loggedIn])
  return (
    <>
    <BrowserRouter>
    <Routes>

      <Route excat path="/" element={auth.user===null?<Navigate to="login"/>:<Home/>}/>
      <Route excat path="/create" element={<Create/>}/>
      <Route excat path="dept/:id" element={<EmpList/>}/>
      <Route excat path="/login" element={<Auth/>}/>
    </Routes>
    
    </BrowserRouter>
     
      
    </>
  );
}

export default App;
