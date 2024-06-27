import React, { useState } from 'react';

const KreiranjeIgracaComponent = () => {
  const [formData, setFormData] = useState({});

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };
function validateSurname () 
 {let surnameValid = true;if (formData.hasOwnProperty('surname')) { 
const surname = formData.surname; 
surnameValid = /^[A-Z]/.test(surname) && /^[A-Za-z]+$/.test(surname);
if (!surnameValid){
console.log('prezime nije validno');} 
else {return true;}
}
}
function validateName () 
 {let nameValid = true;if (formData.hasOwnProperty('name')) { 
const name = formData.name; 
nameValid = /^[A-Z]/.test(name) && /^[A-Za-z]+$/.test(name);
if (!nameValid){
console.log('ime nije validno');} 
else {return true;}
}
}

  function validateNumericAttribute(attributeName, attributeValue, donjaGranica, gornjaGranica ) {
  let attributeValid = true;
  if (formData.hasOwnProperty(attributeName)) {

    if (!isNaN(attributeValue)) {
      const numericValue = parseFloat(attributeValue);

      attributeValid = numericValue >= donjaGranica && numericValue <= gornjaGranica;

      if (!attributeValid) {
        console.log(`${attributeName} mora biti izmedju ${donjaGranica} i ${gornjaGranica}.`);
      } else {
        return true;
      }
    } else {
      console.log(`${attributeName} mora biti numericka vrednost.`);
      attributeValid = false;
    }
  }
  return attributeValid;
};
function validateTextFinalValues(textInput, allowedValues) {
    if (!allowedValues.includes(textInput)) {
        console.log("nedozvoljena vrednost unosa");
    } else return true;
};function validateTextContainingValues(textInput, allowedValues) {
    for (let value of allowedValues) {
        if (textInput.includes(value)) {
            return true;
        }
    }
    console.log("nedozvoljena vrednost unosa");
    return false;
}const handleSubmit = (e) => {
  e.preventDefault();
  if (validateNumericAttribute('rejting',formData.rejting,0.0,3000.0) &&validateTextFinalValues(formData.kategorija,['VK', 'IV', 'III',  'II',  'I',  'MK',  'FM',  'IM' ,  'VM'])&&validateName()&&validateSurname()) {
    console.log('Form data submitted:', formData);
  } else {
    console.log('Neispravan unos');
  }
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
        <option value="" disabled selected>klub</option></select>
        </div>
      <button  type="submit" style={{ 
      border: '1px solid black', 
      borderRadius: '0px', 
      backgroundColor: 'lightgray', 
      margin: '5px', 
      padding: '10px'
    }}>KreiranjeIgraca</button>
      </div>
</form> ); };

export default KreiranjeIgracaComponent;
