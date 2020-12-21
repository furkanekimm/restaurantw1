import './App.css';
import { BrowserRouter as Router, Route, Switch } from "react-router-dom"
import ContextProvider from './contexts/Context';
import Login from './components/Login'
import CreateMedia from "./components/Media/CreateMedia";
import ListCategory from './components/Category/ListCategory';
import AddCategory from './components/Category/AddCategory';
import EditCategory from './components/Category/EditCategory';
import ListProduct from "./components/Product/ListProduct";
import AddProduct from './components/Product/AddProduct';
import EditProduct from "./components/Product/EditProduct";
import ViewProduct from "./components/Product/ViewProduct";
import ListWaiter from "./components/Waiter/ListWaiter";
import AddWaiter from "./components/Waiter/AddWaiter";
import EditWaiter from "./components/Waiter/EditWaiter";
import ListUsers from "./components/User/ListUsers";
import AddUser from "./components/User/AddUser";
import EditUser from "./components/User/EditUser";
import ListRoles from "./components/Role/ListRoles";
import AddRole from "./components/Role/AddRole";
import EditRole from "./components/Role/EditRole";
import ListReports from "./components/Report/ListReports";
import ListInfos from "./components/Info/ListInfos";
import ListTables from "./components/Table/ListTables";
import AddTable from "./components/Table/AddTable";
import EditTable from "./components/Table/EditTable";
import InfoServices from './services/InfoServices';
function App() {
  return (
    <ContextProvider>
     
      <div>
        <Router>
          <div class="container">
            <Switch>
              <Route path="/updatetable" component={EditTable}></Route>
              <Route path="/addtable" component={AddTable}></Route>
              <Route path="/tables" component={ListTables}></Route>
              <Route path="/infos" component={ListInfos}></Route>
              <Route path="/reports" component={ListReports}></Route>
              <Route path="/updaterole" component={EditRole}></Route>
              <Route path="/addrole" component={AddRole}></Route>
              <Route path="/roles" component={ListRoles}></Route>
              <Route path="/updateuser" component={EditUser}></Route>
              <Route path="/adduser" component={AddUser}></Route>
              <Route path="/users" component={ListUsers}></Route>
              <Route path="/updatewaiter" component={EditWaiter}></Route>
              <Route path="/addwaiter" component={AddWaiter}></Route>
              <Route path="/waiters" component={ListWaiter}></Route>
              <Route path="/viewproduct" component={ViewProduct}></Route>
              <Route path="/updateproduct" component={EditProduct}></Route>
              <Route path="/addproduct" component={AddProduct}></Route>
              <Route path="/products" component={ListProduct}></Route>
              <Route path="/updatecategory" component={EditCategory}></Route>
              <Route path="/createmedia" component={CreateMedia}></Route>
              <Route path="/categories" component={ListCategory}></Route>
              <Route path="/addcategory" component={AddCategory}></Route>
              <Route path="/" component={Login} />
            </Switch>
          </div>
        </Router>
      </div>
    </ContextProvider>
  );
}

export default App;
