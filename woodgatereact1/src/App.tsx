import React from 'react';
import logo from './logo.svg';
import './App.css';
import {BrowserRouter, Route, Routes } from 'react-router-dom';
import { Login } from './Components/LoginRegister/Login';
import { Register } from './Components/LoginRegister/Register';
import 'bootstrap/dist/css/bootstrap.css';
import { ReimbContainer } from './Components/Reimbs/ReimbContainer';
import { UserContainer } from './Components/User/UserContainer';
import { ReimbPost } from './Components/Reimbs/ReimbPost';
import { ReimbUpdate } from './Components/Reimbs/ReimbUpdate';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="" element={<Login/>} />
          <Route path="/register" element={<Register/>}/>
          <Route path="/reimbs" element={<ReimbContainer/>}/>
          <Route path="/newreimb" element={<ReimbPost/>}/>
          <Route path="/updatereimb/:reimbId" element={<ReimbUpdate/>}/>
          <Route path="/users" element={<UserContainer/>}/>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
