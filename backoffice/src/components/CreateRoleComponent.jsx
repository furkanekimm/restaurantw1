import React, {Component} from 'react';
import HeaderComponent from "./HeaderComponent";
import UserService from "../services/UserService";

class CreateRoleComponent extends Component {
    constructor(props) {
        super(props);
            this.state={
                name:'',
            }
        this.changeRoleNameHandler = this.changeRoleNameHandler.bind(this);
        this.saveRole = this.saveRole.bind(this);
    }

    saveRole=(e)=>{
        let role={
            name:'ROLE_'+this.state.name.toUpperCase()
        }
        UserService.addRole(role).then((res)=>{
                this.props.history.push('/listroles');
       }
       );
    e.preventDefault();
    }

    changeRoleNameHandler=(e)=>{
        this.setState({name:e.target.value})
    }

    cancel() {
        this.props.history.push('/listroles');
    }

    render() {
        return (
            <div>
                <div>
                    <HeaderComponent/>
                    <div className="container">
                        <div className="row">
                            <div className="card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-center">Add Role</h3>
                                <div className="card-body">
                                    <form>
                                        <div className="form-group">
                                            <label> Role Name </label>
                                            <input placeholder="Table Name" name="name" className="form-control"
                                                   value={this.state.name} onChange={this.changeRoleNameHandler}/>
                                        </div>
                                        <button className="btn btn-success" onClick={this.saveRole}>Save</button>
                                        <button className="btn btn-danger" onClick={this.cancel.bind(this)}
                                                style={{marginLeft: "10px"}}>Cancel
                                        </button>
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

export default CreateRoleComponent;