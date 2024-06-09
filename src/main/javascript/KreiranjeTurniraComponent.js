import React, { useState } from 'react';

const KreiranjeTurniraComponent = () => {
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
      <h2>KreiranjeTurnira</h2>
      <div>
        <div>
          <label>naziv</label>
          <input type="text" name="naziv" onChange={handleChange} />
        </div>
        <div>
          <label>mesto</label>
          <input type="text" name="mesto" onChange={handleChange} />
        </div>
        <div>
          <label>tip</label>
          <select name="" class="">
        <option value="" disabled selected>tip</option></select>"
        </div>
        <div>
          <label>tempo</label>
          <select name="" class="">
        <option value="" disabled selected>tempo</option></select>"
        </div>
      <button  type="submit" style={{ 
      border: '1px solid black', 
      borderRadius: '0px', 
      backgroundColor: 'lightgray', 
      margin: '5px', 
      padding: '10px'
    }}>KreiranjeTurnira</button>
      </div>
    </form>
  );
};

export default KreiranjeTurniraComponent;
