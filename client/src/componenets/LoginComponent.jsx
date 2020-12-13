import React, {Component} from 'react';
import UserService from "../services/UserService";

const userContext = React.createContext({user: {}});

class LoginComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            username: '',
            password: '',
            checked:false,
        }
        this.changeusernameHandler = this.changeusernameHandler.bind(this);
        this.changepasswordHandler = this.changepasswordHandler.bind(this);
        this.addLocal = this.addLocal.bind(this);
    }
    loadRemember(e){
        if(localStorage.getItem("rememberUser")!==null && localStorage.getItem("rememberPass")!==null){
            this.setState({username:localStorage.getItem("rememberUser"),password:localStorage.getItem("rememberPass")})
            this.setState({checked:true})
            document.getElementById("check").checked=true;

        }
        else{
            document.getElementById("check").checked=false;
            this.setState({checked:false})
        }
    }
    rememberFunc(e){
        if(this.state.checked===true){
            localStorage.setItem("rememberUser",this.state.username);
            localStorage.setItem("rememberPass",this.state.password);
            /* document.getElementById("check").checked=true;*/
        }else{
            localStorage.removeItem("rememberUser");
            localStorage.removeItem("rememberPass");
            this.setState({checked:false})
        }
    }

    loginUser = (e) => {
        e.preventDefault();
        UserService.login(this.state.username, this.state.password).then((res) => {
                if (res.data != '') {
                    localStorage.setItem("username", this.state.username);
                    localStorage.setItem("password", this.state.password);
                    this.rememberFunc();
                    this.props.history.push("/homepagee");
                } else {
                    alert("Hatalı giriş yaptınız.");
                }
            }
        )
    }
    addLocal=(event) =>{
        this.setState({checked: event.target.checked})
    }
    changeusernameHandler = (event) => {
        this.setState({username: event.target.value})
    }
    changepasswordHandler = (event) => {
        this.setState({password: event.target.value})
    }
    componentDidMount() {
        this.loadRemember();
    }

    render() {
        return (
            <div>
                <div className="center-login">
                    <div className="container ">
                        <div className="row ">
                            <div className="card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-center">Login</h3>
                                <div className="card-body ">
                                    <form>
                                        <div className="form-group">
                                            <label> username </label>
                                            <input placeholder="username" name="category" className="form-control"
                                                   value={this.state.username} onChange={this.changeusernameHandler}/>
                                        </div>
                                        <div className="form-group">
                                            <label> password </label>
                                            <input placeholder="password" name="productName" className="form-control"
                                                   type="password"
                                                   value={this.state.password} onChange={this.changepasswordHandler}/>
                                        </div>
                                        <div className="form-group">
                                            <label> Remember Me </label>
                                            <label><input id="check" type="checkbox"
                                                          onChange={this.addLocal}/>
                                            </label>
                                        </div>
                                        <button className="btn btn-success" onClick={this.loginUser}>Login</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default LoginComponent;