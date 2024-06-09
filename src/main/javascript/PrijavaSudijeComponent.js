import React, { useState } from 'react';

const PrijavaSudijeComponent = () => {
  const [formData, setFormData] = useState({});

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log('Form data submitted:', formData);
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
    </form>
  );
};

export default PrijavaSudijeComponent;
