import React, {Component} from 'react';
import HeaderComponent from "./HeaderComponent";
import UserService from "../services/UserService";

class UpdateRoleComponent extends Component {
    constructor(props) {
        super(props);
        this.state={
            id:this.props.history.location.state?.role.id,
            name:this.props.history.location.state?.role.name
        }
        this.changeRoleNameHandler = this.changeRoleNameHandler.bind(this);
        this.editRole = this.editRole.bind(this);
    }

    editRole=(e)=>{
        let role={
            id:this.state.id,
            name:this.state.name.toUpperCase()
        }
        UserService.updateRole(role).then((res)=>{
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
                                <h3 className="text-center">Update Role</h3>
                                <div className="card-body">
                                    <form>
                                        <div className="form-group">
                                            <label> Role Name </label>
                                            <input placeholder="Table Name" name="name" className="form-control"
                                                   value={this.state.name} onChange={this.changeRoleNameHandler}/>
                                        </div>
                                        <button className="btn btn-success" onClick={this.editRole}>Update</button>
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

export default UpdateRoleComponent;