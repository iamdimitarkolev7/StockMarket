import './App.css';
import { Route, Routes } from 'react-router-dom';
import Home from "./components/common/Home";
import NavBar from './components/common/NavBar';
import Register from './components/user/Register';
import isLoggedIn from './utils/isLoggedIn';

const App = () => {

  return (
      <div className="App">
        <NavBar/>
        <Routes>
          <Route path="/" exact element={<Home/>} />
          <Route path="/register" element={<Register/>} />
        </Routes>
    </div>
  );
}

export default App;
