import React, { useState } from 'react';

const PrijavaSudijeComponent = () => {
  const [formData, setFormData] = useState({});

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };
function validatePassword() 
{let passwordValid = true; if (formData.hasOwnProperty('password')) {
      const password = formData.password;
      passwordValid = /[a-z]/.test(password) && /[A-Z]/.test(password) && /\d/.test(password);
      if (!passwordValid) {
        console.log('Password mora sadrzati bar jedno malo slovo, jedno veliko slovo i jednu cifru.');
      }else {return true;};
    }
  }
function validateUsername ()
 {let usernameValid = true;if (formData.hasOwnProperty('username')) {
      const username = formData.username;
      usernameValid = /^[a-z]+$/.test(username);
      if (!usernameValid) {
        console.log('Username mora sadrzati samo mala slova.');
      } else {return true;}
    }
  }

  const handleSubmit = (e) => {
  e.preventDefault();
  if (true&&true&&validateUsername()&&validatePassword()) {
    console.log('Form data submitted:', formData);
  } else {
    console.log('Neispravan unos');
  }
}; 
return (
    <form onSubmit={handleSubmit}>
      <h2>PrijavaSudije</h2>
      <div>
        <div>
          <label>username</label>
          <input type="text" name="username" onChange={handleChange} />
        </div>
        <div>
          <label>password</label>
          <input type="text" name="password" onChange={handleChange} />
        </div>
      <button  type="submit" style={{ 
      border: '1px solid black', 
      borderRadius: '0px', 
      backgroundColor: 'lightgray', 
      margin: '5px', 
      padding: '10px'
    }}>PrijavaSudije</button>
      </div>
</form> ); };

export default PrijavaSudijeComponent;
