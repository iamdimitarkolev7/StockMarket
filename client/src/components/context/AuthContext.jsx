import React, { createContext, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { register, login, logout } from '../../services/userService';

const AuthContext = createContext();

const AuthProvider = ({ children }) => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const navigate = useNavigate();

  return (
    <AuthContext.Provider value={
      { isLoggedIn, 
        register: (data) => register(data, setIsLoggedIn, navigate), 
        login: (data) => login(data, setIsLoggedIn, navigate), 
        logout: () => logout(setIsLoggedIn, navigate) }}>
      {children}
    </AuthContext.Provider>
  );
};

export { AuthContext, AuthProvider };