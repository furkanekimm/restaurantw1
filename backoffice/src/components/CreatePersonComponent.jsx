import React, {Component} from 'react';
import ProductService from "../services/ProductService";
import UserService from "../services/UserService";
import HeaderComponent from "./HeaderComponent";

class CreatePersonComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            username: '',
            password: '',
            role: '',
            authority:'',
            authorities:[],
            rolesId:[]
        }
        this.changeusernameHandler =this.changeusernameHandler.bind(this);
        this.changepasswordHandler = this.changepasswordHandler.bind(this);
        this.changeRoleHandler=this.changeRoleHandler.bind(this);
        this.saveUser = this.saveUser.bind(this);
        this.changeMultiCate = this.changeMultiCate.bind(this);
    }
    changeMultiCate(id) {
        if (this.state.rolesId.includes(id) !== true) {
            this.state.rolesId.push(id);
            console.log(this.state.rolesId)
        } else {
            for (let i = 0; i < this.state.rolesId.length; i++) {
                if (id === this.state.rolesId[i]) {
                    this.state.rolesId.splice(i, 1);
                    console.log(this.state.rolesId)
                }
            }
        }


    }

    componentDidMount() {
        UserService.listAllRoles().then((res)=>{
            this.setState({authorities:res.data})
        })
    }

    saveUser = (e) => {

        e.preventDefault();
        let user = {
            username: this.state.username,
            password: this.state.password,
            rolesId:this.state.rolesId
        };


        console.log('user => ' + JSON.stringify(user));
        UserService.addUser(user,this.state.authority).then(res =>{
            this.props.history.push('/listuser');
        });

    }
    changeusernameHandler=(event) =>{
        this.setState({username: event.target.value})
    }
    changepasswordHandler=(event) =>{
        this.setState({password: event.target.value})
    }
    changeRoleHandler=(event) =>{
        this.setState({role: event.target.value})
        console.log(this.state.role)
    }
    cancel(){
        this.props.history.push('/listuser');
    }

    render() {
        return (
            <div>
                <HeaderComponent/>
                <div className="container">

                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Add User</h3>
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label> username </label>
                                        <input placeholder="username" name="category" className="form-control"
                                               value={this.state.username} onChange={this.changeusernameHandler}/>
                                    </div>
                                    <div className="form-group">
                                        <label> password </label>
                                        <input placeholder="password" name="productName" className="form-control" type="password"
                                               value={this.state.password} onChange={this.changepasswordHandler}/>
                                    </div>

                                    <div className="form-group">
                                        <label> Category </label>
                                        <div className="checkbox" style={{height: "4rem", overflow: "auto"}}>
                                            {
                                                this.state.authorities.map(
                                                    role =>
                                                        <div className="row col-md -12">
                                                            <label><input type="checkbox" value=""
                                                                          onClick={() => this.changeMultiCate(role.id)}/>{role.name}
                                                            </label>
                                                        </div>
                                                )
                                            }
                                        </div>
                                    </div>
                                    <button className="btn btn-success" onClick={this.saveUser}>Save</button>
                                    <button className="btn btn-danger" onClick={this.cancel.bind(this)}
                                            style={{marginLeft: "10px"}}>Cancel
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default CreatePersonComponent;