import React from "react";

const Register = () => {

  const sendRequest = () => {
      let data = {firstName: };
      
      return fetch(`http://localhost:8088/api/users/register`, {
              method: 'POST',
              body: JSON.stringify(data),
              headers: {
                  'Content-type': 'application/x-www-form-urlencoded'
              },
              credentials: 'include'
          }).then(res => res.json()).then(user => sessionStorage.setItem('user', JSON.stringify(user)));
      },
  }

  return (
  <div className="form">
          <div className="form-body">
              <div className="username">
                  <label className="form__label" for="firstName">First Name </label>
                  <input className="form__input" type="text" id="firstName" placeholder="First Name"/>
              </div>
              <div className="lastname">
                  <label className="form__label" for="lastName">Last Name </label>
                  <input  type="text" name="" id="lastName"  className="form__input"placeholder="LastName"/>
              </div>
              <div className="email">
                  <label className="form__label" for="email">Email </label>
                  <input  type="email" id="email" className="form__input" placeholder="Email"/>
              </div>
              <div className="password">
                  <label className="form__label" for="password">Password </label>
                  <input className="form__input" type="password"  id="password" placeholder="Password"/>
              </div>
              <div className="confirm-password">
                  <label className="form__label" for="confirmPassword">Confirm Password </label>
                  <input className="form__input" type="password" id="confirmPassword" placeholder="Confirm Password"/>
              </div>
          </div>
          <div class="footer">
              <button type="submit" class="btn" onSubmit={sendRequest}>Register</button>
          </div>
      </div>  
  )    
}

export default Register;