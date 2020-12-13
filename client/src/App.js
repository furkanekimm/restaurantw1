import logo from './logo.svg';
import './App.css';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom"
import ListProductComponent from './componenets/ListProductComponent'
import LoginComponent from "./componenets/LoginComponent";
import HomePageComponent from "./componenets/HomePageComponent";
import PlaceCartComponent from "./componenets/PlaceCartComponent";
import TableCartComponent from "./componenets/TableCartComponent";

function App() {
    return (
        <div>
            <Router>
                    <Switch>
                        <Route path="/" exact component={LoginComponent}></Route>
                        <Route path="/places" component={PlaceCartComponent}></Route>
                        <Route path="/tables" component={TableCartComponent}></Route>
                        <Route path="/products" component={ListProductComponent}></Route>
                        <Route path="/homepagee"  component={HomePageComponent}></Route>
                    </Switch>
            </Router>
        </div>
    );
}

export default App;
