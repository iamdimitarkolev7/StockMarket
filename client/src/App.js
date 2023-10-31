import React from 'react';
import './App.css';
import { Route, Routes } from 'react-router-dom';
import NavBar from './components/common/NavBar';
import Home from './views/Home';
import Register from './views/Register';
import Login from './views/Login';
import MyProfile from './views/MyProfile';
import { AuthProvider } from './context/AuthContext';

const App = () => {

  return (
    <div className='App'>
      <AuthProvider>
        <NavBar/>
        <Routes>
          <Route path='/' exact element={<Home/>} />
          <Route path='/register' element={<Register/>} />
          <Route path='/login' element={<Login/>} />
          <Route path='/my-profile/:id' element={<MyProfile/>} />
        </Routes>
      </AuthProvider>
    </div>
  );
}

export default App;
