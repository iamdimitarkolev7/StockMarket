const handleAuthResponse = (response) => {

  const message = response.message;

  if (!response.success) {
    throw new Error(message);
  }
  
  const user = response.data.user;
  sessionStorage.setItem('user', user.username);
  sessionStorage.setItem('userId', user.userId);
}

export default handleAuthResponse;