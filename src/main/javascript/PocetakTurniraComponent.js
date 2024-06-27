import React, { useState } from 'react';

const PocetakTurniraComponent = () => {
  const tableStyle = {
    borderCollapse: 'collapse', 
    width: '70%'
  };
const cellStyle = {
    border: '1px solid black',
    padding: '8px'
  };
  return (
<>      <h2>PocetakTurnira</h2>
      <div>
<select>
          <option value="option1">Kriterijum 1</option>
          <option value="option2">Kriterijum 2</option>
          <option value="option3">Kriterijum 3</option>
        </select>
        <input type="text" placeholder="Unesite vrednost kriterijuma" />
        <button 
          type="submit" 
          style={{ 
            border: '1px solid black', 
            borderRadius: '0px', 
            backgroundColor: 'lightgray' 
          }}
        >
          Pretraga
        </button>
         <table style={tableStyle}>
            <thead>
                <tr>
                    <th style={cellStyle}>TurnirID</th>
<th style={cellStyle}>naziv</th>
<th style={cellStyle}>mesto</th>
<th style={cellStyle}>tip</th>
<th style={cellStyle}>tempo</th>
                </tr>
            </thead>
            <tbody>
                    <tr>
<td style={cellStyle}>upisi vrednost</td>
<td style={cellStyle}>upisi vrednost</td>
<td style={cellStyle}>upisi vrednost</td>
<td style={cellStyle}>upisi vrednost</td>
<td style={cellStyle}>upisi vrednost</td>
                        </tr>
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
</>);};

export default PocetakTurniraComponent;
