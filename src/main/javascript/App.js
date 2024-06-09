import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import PrijavaSudijeComponent from './PrijavaSudijeComponent';
import KreiranjeIgracaComponent from './KreiranjeIgracaComponent';
import KreiranjeTurniraComponent from './KreiranjeTurniraComponent';
import PocetakTurniraComponent from './PocetakTurniraComponent';
import ParovanjeComponent from './ParovanjeComponent';
import PlasmanComponent from './PlasmanComponent';
import PregledZavrsenogTurniraComponent from './PregledZavrsenogTurniraComponent';

const App = () => (
  <Router>
    <div>
      <nav>
        <ul>
          <li>
            <Link to="/prijavasudijecomponent">PrijavaSudijeComponent</Link>
          </li>
          <li>
            <Link to="/kreiranjeigracacomponent">KreiranjeIgracaComponent</Link>
          </li>
          <li>
            <Link to="/kreiranjeturniracomponent">KreiranjeTurniraComponent</Link>
          </li>
          <li>
            <Link to="/pocetakturniracomponent">PocetakTurniraComponent</Link>
          </li>
          <li>
            <Link to="/parovanjecomponent">ParovanjeComponent</Link>
          </li>
          <li>
            <Link to="/plasmancomponent">PlasmanComponent</Link>
          </li>
          <li>
            <Link to="/pregledzavrsenogturniracomponent">PregledZavrsenogTurniraComponent</Link>
          </li>
        </ul>
      </nav>
      <Routes>
        <Route path="/prijavasudijecomponent" element={<PrijavaSudijeComponent />} />
        <Route path="/kreiranjeigracacomponent" element={<KreiranjeIgracaComponent />} />
        <Route path="/kreiranjeturniracomponent" element={<KreiranjeTurniraComponent />} />
        <Route path="/pocetakturniracomponent" element={<PocetakTurniraComponent />} />
        <Route path="/parovanjecomponent" element={<ParovanjeComponent />} />
        <Route path="/plasmancomponent" element={<PlasmanComponent />} />
        <Route path="/pregledzavrsenogturniracomponent" element={<PregledZavrsenogTurniraComponent />} />
      </Routes>
    </div>
  </Router>
);

export default App;
