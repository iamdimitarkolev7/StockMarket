const handleAuthResponse = (response, setIsLoggedIn, navigate) => {
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

export { handleAuthResponse };