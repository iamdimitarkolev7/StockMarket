import handleAuthResponse from "./utils/handleAuthResponse";
import handleError from "./utils/handleError";

const registerRequest = (data) => {
  
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
};

const loginRequest = (data) => {

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

const logoutRequest = () => {
  // TODO...
  console.log('logout');
}

const userRequests = { registerRequest, loginRequest, logoutRequest };

export default userRequests;