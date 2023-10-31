import { handleAuthResponse } from './utils/handleAuthResponse';
import { handleError } from './utils/handleError';

const register = (data, setIsLoggedIn, navigate) => {
  fetch('http://localhost:8088/api/users/register', {
    method: 'POST',
    body: JSON.stringify(data),
    headers: {
      'Content-type': 'application/json'
    },
    credentials: 'include'
  })
  .then(res => res.json())
  .then(res => handleAuthResponse(res, setIsLoggedIn, navigate))
  .catch(err => handleError(err));
}

const login = (data, setIsLoggedIn, navigate) => {
  fetch('http://localhost:8088/api/users/login', {
    method: 'POST',
    body: JSON.stringify(data),
    headers: {
      'Content-type': 'application/json'
    },
    credentials: 'include'
  })
  .then(res => res.json())
  .then(res => handleAuthResponse(res, setIsLoggedIn, navigate))
  .catch(err => handleError(err));
}

const logout = (setIsLoggedIn, navigate) => {
  fetch('http://localhost:8088/api/users/logout', {
    method: 'GET',
    headers: {
      'Content-type': 'application/json',
      'Authorization': `Bearer ${sessionStorage.getItem('jwtToken')}`
    },
    credentials: 'include'
  })
  .then(res => {
    sessionStorage.removeItem('jwtToken');
    sessionStorage.removeItem('userId');
    setIsLoggedIn(false);
    navigate('/');
  })
  .catch(err => handleError(err));
  
}

export { register, login, logout };