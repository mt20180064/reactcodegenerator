import React, { useState } from 'react';

const PocetakTurniraComponent = () => {
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
      <h2>PocetakTurnira</h2>
      <div>
        <table>
            <thead>
                <tr>
                    <th>TurnirID</th>
<th>naziv</th>
<th>mesto</th>
<th>tip</th>
<th>tempo</th>
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
    }}>PocetakTurnira</button>
      </div>
    </form>
  );
};

export default PocetakTurniraComponent;
