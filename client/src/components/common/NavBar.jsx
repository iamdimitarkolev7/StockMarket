import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";

import "../styles/NavBarStyles.css";

const NavBar = () => {

  const [loggedIn, setLoggedIn] = useState(false);

  useEffect(() => {
    setLoggedIn(sessionStorage.getItem('user') != null);
  }, [loggedIn]);

  return (
    <nav>
      <ul className="navbar">
        <li>
          <Link className="navbar-link" to="/" >
            Home
          </Link>
        </li>
        { !loggedIn &&
          <li>
            <Link className="navbar-link" to="/login" >
              Login
            </Link>
          </li> }
        { !loggedIn &&
        <li>
          <Link className="navbar-link" to="/register" >
            Register
          </Link>
        </li> }
        { loggedIn &&
          <li>
            <Link className="navbar-link" to={`/my-profile/${sessionStorage.getItem('userId')}`} >
              My Profile
            </Link>
          </li> }
        { loggedIn &&
          <li>
            <Link className="navbar-link" to="/logout" >
              Logout
            </Link>
          </li> }
      </ul>
    </nav>
  );
}

export default NavBar;