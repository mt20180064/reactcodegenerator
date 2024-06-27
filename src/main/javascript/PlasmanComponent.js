import React, { useState } from 'react';

const PlasmanComponent = () => {
  const tableStyle = {
    borderCollapse: 'collapse', 
    width: '70%'
  };
const cellStyle = {
    border: '1px solid black',
    padding: '8px'
  };
  return (
<>      <h2>Plasman</h2>
      <div>
        <table style={tableStyle}>
            <thead>
                <tr>
                    <th style={cellStyle}>IgracID</th>
<th style={cellStyle}>rang</th>
<th style={cellStyle}>ime</th>
<th style={cellStyle}>prezime</th>
<th style={cellStyle}>poeni</th>
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
    }}>Plasman</button>
      </div>
</>);};

export default PlasmanComponent;
