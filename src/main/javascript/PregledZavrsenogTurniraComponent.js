import React, { useState } from 'react';

const PregledZavrsenogTurniraComponent = () => {
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
      <h2>PregledZavrsenogTurnira</h2>
      <div>
        <div>
          <label>TurnirID</label>
          <label>TurnirID</label>
        </div>
        <div>
          <label>sudija</label>
          <label>sudija</label>
        </div>
        <div>
          <label>status</label>
          <label>status</label>
        </div>
        <div>
          <label>prvi</label>
          <label>prvi</label>
        </div>
        <div>
          <label>drugi</label>
          <label>drugi</label>
        </div>
        <div>
          <label>treci</label>
          <label>treci</label>
        </div>
      </div>
    </form>
  );
};

export default PregledZavrsenogTurniraComponent;
