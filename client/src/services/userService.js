const registerRequest = (data) => {
  
  try {
    
    fetch('http://localhost:8088/api/users/register', {
      method: 'POST',
      body: JSON.stringify(data),
      headers: {
        'Content-type': 'application/json'
      },
      credentials: 'include'
    })
    .then(res => res.json())
    .then(res => func(res.data));

  } catch (error) {
    console.error(error);
  }
};

const func = (data) => {
  sessionStorage.setItem('user', data.createdUser.username);
  sessionStorage.setItem('jwt', data.createdUser.jwtToken);
}

export default registerRequest;