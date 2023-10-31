import React, { useContext } from 'react';

import './NavBarStyles.css';
import { AuthContext } from '../../context/AuthContext';
import NavBarButton from '../buttons/navBarButton/NavBarButton';

const NavBar = () => {

  const { isLoggedIn, logout } = useContext(AuthContext);

  return (
    <nav>
      <ul className="navbar">
        <li>
          <img className='logo' alt='logo' src='investomania_logo.png'/>
        </li>
        <li>
          <NavBarButton title='Home' direction='/'/>
        </li>
        { !isLoggedIn &&
          <li>
            <NavBarButton title='Login' direction='/login'/>
          </li> }
        { !isLoggedIn &&
        <li>
          <NavBarButton title='Register' direction='/register'/>
        </li> }
        { isLoggedIn &&
          <li>
            <NavBarButton title='My Profile' direction={`/my-profile/${sessionStorage.getItem('userId')}`}/>
          </li> }
        { isLoggedIn &&
          <li>
            <NavBarButton title='Logout' direction='/logout' onclick={logout}/>
          </li> }
      </ul>
    </nav>
  );
}

export default NavBar;