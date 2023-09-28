import React, { useContext } from "react";
import { Link } from "react-router-dom";

import "../styles/NavBarStyles.css";
import { AuthContext } from "../context/AuthContext";

const NavBar = () => {

  const { isLoggedIn, logout } = useContext(AuthContext);

  return (
    <nav>
      <ul className="navbar">
        <li>
          <Link className="navbar-link" to="/" >
            Home
          </Link>
        </li>
        { !isLoggedIn &&
          <li>
            <Link className="navbar-link" to="/login">
              Login
            </Link>
          </li> }
        { !isLoggedIn &&
        <li>
          <Link className="navbar-link" to="/register">
            Register
          </Link>
        </li> }
        { isLoggedIn &&
          <li>
            <Link className="navbar-link" to={`/my-profile/${sessionStorage.getItem('userId')}`} >
              My Profile
            </Link>
          </li> }
        { isLoggedIn &&
          <li>
            <Link className="navbar-link" to="/logout" onClick={logout}>
              Logout
            </Link>
          </li> }
      </ul>
    </nav>
  );
}

export default NavBar;