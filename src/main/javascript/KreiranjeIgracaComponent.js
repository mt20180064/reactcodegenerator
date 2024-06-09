import React, { useState } from 'react';

const KreiranjeIgracaComponent = () => {
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
      <h2>KreiranjeIgraca</h2>
      <div>
        <div>
          <label>name</label>
          <input type="text" name="name" onChange={handleChange} />
        </div>
        <div>
          <label>surname</label>
          <input type="text" name="surname" onChange={handleChange} />
        </div>
        <div>
          <label>rejting</label>
          <input type="text" name="rejting" onChange={handleChange} />
        </div>
        <div>
          <label>kategorija</label>
          <input type="text" name="kategorija" onChange={handleChange} />
        </div>
        <div>
          <label>klub</label>
          <select name="" class="">
        <option value="" disabled selected>klub</option></select>"
        </div>
      <button  type="submit" style={{ 
      border: '1px solid black', 
      borderRadius: '0px', 
      backgroundColor: 'lightgray', 
      margin: '5px', 
      padding: '10px'
    }}>KreiranjeIgraca</button>
      </div>
    </form>
  );
};

export default KreiranjeIgracaComponent;
