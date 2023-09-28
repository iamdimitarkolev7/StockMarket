import React, { createContext, useState } from 'react';
import { useNavigate } from 'react-router-dom';

const AuthContext = createContext();

const AuthProvider = ({ children }) => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const navigate = useNavigate();

  const register = (data) => {
    fetch('http://localhost:8088/api/users/register', {
      method: 'POST',
      body: JSON.stringify(data),
      headers: {
        'Content-type': 'application/json'
      },
      credentials: 'include'
    })
    .then(res => res.json())
    .then(res => handleAuthResponse(res))
    .catch(err => handleError(err));
  }

  const login = (data) => {
    fetch('http://localhost:8088/api/users/login', {
      method: 'POST',
      body: JSON.stringify(data),
      headers: {
        'Content-type': 'application/json'
      },
      credentials: 'include'
    })
    .then(res => res.json())
    .then(res => handleAuthResponse(res))
    .catch(err => handleError(err));
  }

  const logout = () => {
    fetch('http://localhost:8088/api/users/logout', {
      method: 'GET',
      headers: {
        'Content-type': 'application/json',
        'Authorization': `Bearer ${sessionStorage.getItem('jwtToken')}`
      },
      credentials: 'include'
    })
    .then(res => {
      sessionStorage.removeItem("jwtToken");
      sessionStorage.removeItem("userId");
      setIsLoggedIn(false);
      navigate('/');
    })
    .catch(err => handleError(err));
    
  }

  const handleAuthResponse = (response) => {
    const message = response.message;
  
    if (!response.success) {
      throw new Error(message);
    }
    
    const jwtToken = response.data.jwtToken
    const user = response.data.user;
    sessionStorage.setItem('jwtToken', jwtToken);
    sessionStorage.setItem('userId', user.userId);
    setIsLoggedIn(true);
    navigate('/');
  }

  const handleError = (error) => {
    console.log(error);
  }

  return (
    <AuthContext.Provider value={{ isLoggedIn, register, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export { AuthContext, AuthProvider };