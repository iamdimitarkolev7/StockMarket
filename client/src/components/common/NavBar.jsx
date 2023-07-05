import React from "react";
import { Link } from "react-router-dom";

import isLoggedIn from "../../utils/isLoggedIn";
import "../styles/NavBarStyles.css"

const NavBar = () => {

  return (
    <nav>
      <ul className="navbar">
        <li>
          <Link className="navbar-link" to="/" >
            Home
          </Link>
        </li>
        { !isLoggedIn() &&
          <li>
            <Link className="navbar-link" to="/login" >
              Login
            </Link>
          </li> }
        { !isLoggedIn() &&
        <li>
          <Link className="navbar-link" to="/register" >
            Register
          </Link>
        </li> }
        { isLoggedIn() &&
          <li>
            <Link className="navbar-link" to={`/my-profile/${sessionStorage.getItem('userId')}`} >
              My Profile
            </Link>
          </li> }
        { isLoggedIn() &&
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