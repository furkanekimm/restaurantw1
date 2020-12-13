import logo from './logo.svg';
import './App.css';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom"
import ListProductComponent from './components/ListProductComponent';
import CreateProductComponent from './components/CreateProductComponent';
import UpdateProductComponent from './components/UpdateProductComponent';
import CreatePersonComponent from "./components/CreatePersonComponent";
import ListUserComponent from "./components/ListUserComponent";
import UpdateUserComponent from "./components/UpdateUserComponent";
import LoginComponent from "./components/LoginComponent";
import CreateCategoryComponent from "./components/CreateCategoryComponent";
import ListAllCategoryComponent from "./components/ListAllCategoryComponent";
import ListInfoComponent from "./components/ListInfoComponent";
import ListCartsComponent from "./components/ListCartsComponent";
import CreatePlaceRestComponent from "./components/CreatePlaceRestComponent";
import CreateTableRestComponent from "./components/CreateTableRestComponent";
import ListPlaceComponent from "./components/ListPlaceComponent";
import ListTableComponent from "./components/ListTableComponent";
import UpdateTableComponent from "./components/UpdateTableComponent";
import UpdateCategoryComponent from "./components/UpdateCategoryComponent.";
import UpdatePlaceRestComponent from "./components/UpdatePlaceRestComponent";
import ViewProductComponent from "./components/ViewProductComponent";
import CreateWaiterComponent from "./components/CreateWaiterComponent";
import ListWaitersComponent from "./components/ListWaitersComponent";
import UpdateWaiterComponent from "./components/UpdateWaiterComponent";
import CreateMediaComponent from "./components/CreateMediaComponent";
import {user,loading,UserContext} from "./context";
import Loader from 'react-loader-spinner';
import ListAllRolesComponent from "./components/ListAllRolesComponent";
import CreateRoleComponent from "./components/CreateRoleComponent";
import UpdateRoleComponent from "./components/UpdateRoleComponent";

function App() {

        return (
        <UserContext.Provider value={loading}>

        <div>
            <Router>
                <div class="container">
                    <Switch>
                        <Route path="/carts" component={ListCartsComponent}></Route>
                        <Route path="/infoserver" component={ListInfoComponent}></Route>
                        <Route path="/updatecategory" component={UpdateCategoryComponent}></Route>
                        <Route path="/updateplace" component={UpdatePlaceRestComponent}></Route>
                        <Route path="/addwaiter" component={CreateWaiterComponent}></Route>
                        <Route path="/listwaiters" component={ListWaitersComponent}></Route>
                        <Route path="/listroles" component={ListAllRolesComponent}></Route>
                        <Route path="/addrole" component={CreateRoleComponent}></Route>
                        <Route path="/updaterole" component={UpdateRoleComponent}></Route>
                        <Route path="/updatewaiter" component={UpdateWaiterComponent}></Route>
                        <Route path="/createmedia" component={CreateMediaComponent}></Route>
                        {/*<Route path="/updatetable" component={UpdateTableComponent}></Route>*/}
                        <Route path="/addplace" component={CreatePlaceRestComponent}></Route>
                        <Route path="/viewproduct" component={ViewProductComponent}></Route>
                        <Route path="/listplace" component={ListPlaceComponent}></Route>
                        {/*<Route path="/listtable" component={ListTableComponent}></Route>*/}
                        {/*<Route path="/addtable" component={CreateTableRestComponent}></Route>*/}
                        <Route path="/addcategory" component={CreateCategoryComponent}></Route>
                        <Route path="/listcategories" component={ListAllCategoryComponent}></Route>
                        <Route path="/product" exact component={ListProductComponent}></Route>
                        <Route path="/products" component={ListProductComponent}></Route>
                        <Route path="/add" component={CreateProductComponent}></Route>
                        <Route path="/newperson" component={CreatePersonComponent}></Route>
                        <Route path="/update/" component={UpdateProductComponent}></Route>
                        <Route path="/listuser" component={ListUserComponent}></Route>
                        <Route path="/updateUser" component={UpdateUserComponent}></Route>
                        <Route path="/" component={LoginComponent}></Route>



                    </Switch>
                </div>
            </Router>
        </div>
        </UserContext.Provider>
    );
}

export default App;
