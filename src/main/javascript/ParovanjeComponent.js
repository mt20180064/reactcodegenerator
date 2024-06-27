import React, { useState } from 'react';

const ParovanjeComponent = () => {
  const tableStyle = {
    borderCollapse: 'collapse', 
    width: '70%'
  };
const cellStyle = {
    border: '1px solid black',
    padding: '8px'
  };
  return (
<>      <h2>Parovanje</h2>
      <div>
        <table style={tableStyle}>
            <thead>
                <tr>
                    <th style={cellStyle}>PartijaID</th>
<th style={cellStyle}>tabla</th>
<th style={cellStyle}>beli</th>
<th style={cellStyle}>crni</th>
<th style={cellStyle}>bodovibeli</th>
<th style={cellStyle}>bodovicrni</th>
                </tr>
            </thead>
            <tbody>
                    <tr>
<td style={cellStyle}>upisi vrednost</td>
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
</>);};

export default ParovanjeComponent;
