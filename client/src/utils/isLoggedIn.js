const isLoggedIn = () => {

  const jwt = sessionStorage.getItem('jwt');
  console.log(jwt);
  return jwt != null;
}

export default isLoggedIn;
