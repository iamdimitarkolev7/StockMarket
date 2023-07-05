import React, { useState, useEffect } from "react";
import { useParams } from 'react-router-dom';

const MyProfile = () => {

  const [user, setUser] = useState(null);
  const { id } = useParams();

  const getUserByIdRequest = (id) => {

    fetch('http://localhost:8088/api/users/' + id, {
      method: 'GET'
    })
    .then(res => res.json())
    .then(res => console.log(res))
    .catch(err => console.log(err));
  }
  
  useEffect(() => {
    getUserByIdRequest(id);
  }, [id]);

  return (
    <div>
      <p>My profile</p>
    </div>
  )
}

export default MyProfile;