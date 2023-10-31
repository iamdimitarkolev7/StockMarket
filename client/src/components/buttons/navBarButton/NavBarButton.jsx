import React from 'react';
import { Link } from 'react-router-dom';

import './NavBarButtonStyles.css';

const NavBarButton = ({title, direction, onclick}) => {

  return (
    <Link className='navBarButton' to={direction} onClick={onclick}>{title}</Link>
  )
}

export default NavBarButton;