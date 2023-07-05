import './App.css';
import { Route, Routes } from 'react-router-dom';
import Home from "./components/common/Home";
import NavBar from './components/common/NavBar';
import Register from './components/user/Register';
import Login from './components/user/Login';
import MyProfile from './components/user/MyProfile';

const App = () => {

  return (
      <div className="App">
        <NavBar/>
        <Routes>
          <Route path="/" exact element={<Home/>} />
          <Route path="/register" element={<Register/>} />
          <Route path="/login" element={<Login/>} />
          <Route path="/my-profile/:id" element={<MyProfile/>} />
        </Routes>
    </div>
  );
}

export default App;
