import React, { useState } from 'react';

const PlasmanComponent = () => {
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
      <h2>Plasman</h2>
      <div>
        <table>
            <thead>
                <tr>
                    <th>IgracID</th>
<th>rang</th>
<th>ime</th>
<th>prezime</th>
<th>poeni</th>
                </tr>
            </thead>
            <tbody>
                    <tr>
                        <td>1</td> <td>2</td><td>1</td><td>1</td>                    </tr>
            </tbody>
        </table>

      <button  type="submit" style={{ 
      border: '1px solid black', 
      borderRadius: '0px', 
      backgroundColor: 'lightgray', 
      margin: '5px', 
      padding: '10px'
    }}>Plasman</button>
      </div>
    </form>
  );
};

export default PlasmanComponent;
