import React, { useState } from 'react';

const ParovanjeComponent = () => {
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
      <h2>Parovanje</h2>
      <div>
        <table>
            <thead>
                <tr>
                    <th>PartijaID</th>
<th>tabla</th>
<th>beli</th>
<th>crni</th>
<th>bodovibeli</th>
<th>bodovicrni</th>
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
    }}>Parovanje</button>
      <button  type="submit" style={{ 
      border: '1px solid black', 
      borderRadius: '0px', 
      backgroundColor: 'lightgray', 
      margin: '5px', 
      padding: '10px'
    }}>Parovanje</button>
      <button  type="submit" style={{ 
      border: '1px solid black', 
      borderRadius: '0px', 
      backgroundColor: 'lightgray', 
      margin: '5px', 
      padding: '10px'
    }}>Parovanje</button>
      </div>
    </form>
  );
};

export default ParovanjeComponent;
