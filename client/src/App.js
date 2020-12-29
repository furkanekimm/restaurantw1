import logo from './logo.svg';
import './App.css';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom"
import ListProductComponent from './componenets/ListProductComponent'
import LoginComponent from "./componenets/LoginComponent";
import HomePageComponent from "./componenets/HomePageComponent";
import PlaceCartComponent from "./componenets/PlaceCartComponent";
import TableCartComponent from "./componenets/TableCartComponent";
import AddCustomer from "./componenets/AddCustomer";
import ListCustomers from "./componenets/ListCustomer";
import CustomersControl from "./componenets/CustomersControl";
import Customers from "./componenets/customers";
import EditCustomer from "./componenets/EditCustomer";

function App() {
    return (
        <div>
            <Router>
                    <Switch>
                        <Route path="/" exact component={LoginComponent}></Route>
                        <Route path="/customers" component={Customers}></Route>
                        <Route path="/update-customer" component={EditCustomer}></Route>
                        <Route path="/add-customer" component={AddCustomer}></Route>
                        <Route path="/list-customer" component={ListCustomers}></Route>
                        <Route path="/places" component={PlaceCartComponent}></Route>
                        <Route path="/tables" component={TableCartComponent}></Route>
                        <Route path="/products" component={ListProductComponent}></Route>
                        <Route path="/select" component={CustomersControl}></Route>
                        <Route path="/homepagee"  component={HomePageComponent}></Route>
                    </Switch>
            </Router>
        </div>
    );
}

export default App;
