import logo from './logo.svg';
import './App.css';
import {Switch, Route} from "react-router-dom";

const App = () => {
  return (
    <div className="App">
    <Switch>
    <Route path="/" exact component={Home}/>
    <Route path="/register" component={Register}/>
    <Route path="*" component={NotFound}/>
    </Switch>
    </div>
  );
}

export default App;
