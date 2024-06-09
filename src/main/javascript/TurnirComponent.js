import React, { useState } from 'react';

const TurnirComponent = () => {
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
      <h2>Turnir</h2>
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
      <button type="submit">Turnir</button>
      </div>
    </form>
  );
};

export default TurnirComponent;
