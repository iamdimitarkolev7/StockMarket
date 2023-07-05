const isLoggedIn = () => {

  const user = sessionStorage.getItem('user');
  return user != null;
}

export default isLoggedIn;
