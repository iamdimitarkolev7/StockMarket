import React, { useContext } from 'react';
import { AuthContext } from '../context/AuthContext';
import AuthHome from './homeViews/AuthHome';
import BasicHome from './homeViews/BasicHome';

const Home = () => {
  const { isLoggedIn } = useContext(AuthContext);

  return (
    <div>
      { isLoggedIn && <AuthHome/> }
      { !isLoggedIn && <BasicHome/> }
    </div>
  );
};

export default Home;