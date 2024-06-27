import React, { useState } from 'react';

const KreiranjeTurniraComponent = () => {
  const [formData, setFormData] = useState({});

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
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
  if (true&&validateTextContainingValues(formData.naziv,["turnir",  "prvenstvo",  "takmicenje",  "trofej",  "open",  "gradsko",  "drzavno",  "ekipno",  "pojedinacno",  "prvi",  "prvo",  "memorijalni"])&&validateTextContainingValues(formData.mesto,["hotel",  'gimnazija',  'restoran',  'klub',  'prostorije',  'klupske prostorije',  'prostorije kluba',  'prostorija',  'ucionica',  'basta',  'sala',  'hala',  'otvoreno',  'na otvorenom'])&&validateTextFinalValues(formData.tip,['kategorni', 'rejtingovani',  'nerejtingovani', 'atomac',  'humanitarni',  'jedi jedi',  'revijalni',  'prijateljski',  'simultanka',  'ekipno'])&&validateTextFinalValues(formData.tempo,['60+15',  '60',  '120',  '5',  '10',  '15+10',  '3+2',  '2:30 + 30' ,  '10+5'])&&true) {
    console.log('Form data submitted:', formData);
  } else {
    console.log('Neispravan unos');
  }
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
          <input type="text" name="tip" onChange={handleChange} />
        </div>
        <div>
          <label>tempo</label>
          <input type="text" name="tempo" onChange={handleChange} />
        </div>
      <button  type="submit" style={{ 
      border: '1px solid black', 
      borderRadius: '0px', 
      backgroundColor: 'lightgray', 
      margin: '5px', 
      padding: '10px'
    }}>KreiranjeTurnira</button>
      </div>
</form> ); };

export default KreiranjeTurniraComponent;
