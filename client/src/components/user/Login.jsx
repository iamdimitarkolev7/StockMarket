import React, { useState, useEffect } from "react";
import { useNavigate } from 'react-router-dom';

import userRequests from "../../services/userService";
import "../styles/AuthStyles.css";
import isLoggedIn from "../../utils/isLoggedIn";

const Login = () => {
  
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleUsernameChange = (event) => {
    setUsername(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleSubmit = (event) => {
    
    event.preventDefault();
    
    const data = { username, password };
    userRequests.loginRequest(data);
    navigate('/');
  };

  useEffect(() => {
    if (isLoggedIn()) {
      navigate('/');
    }
  }, [navigate]);

  return (
    <div>
      <h2>Login</h2>
      <form onSubmit={handleSubmit} className="login-form">
        <div className="form-group">
          <label htmlFor="username">Username:</label>
          <input
            type="text"
            id="username"
            value={username}
            onChange={handleUsernameChange}
          />
        </div>
        <div className="form-group">
          <label htmlFor="password">Password:</label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={handlePasswordChange}
          />
        </div>
        <button type="submit" className="submit-button">Login</button>
      </form>
    </div>
  );
}

export default Login;