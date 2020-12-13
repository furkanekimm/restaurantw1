import React, {Component} from 'react';
import UserService from "../services/UserService";
import HeaderComponent from "./HeaderComponent";

class ListAllRolesComponent extends Component {
    constructor(props) {
        super(props);
            this.state={
                roles:[]
            }
        this.deleteRole = this.deleteRole.bind(this);
        this.editRole = this.editRole.bind(this);
        this.goAdd = this.goAdd.bind(this);

    }
    goAdd(){
        this.props.history.push('/addrole')
    }

    editRole(role){
        this.props.history.push({
            pathname:'/updaterole',
            state:{
                role:role
            }
        })
    }

    deleteRole(id){
        UserService.deleteRole(id).then(res=>{
            window.location.reload();
        });
    }

    componentDidMount() {
        UserService.listAllRoles().then((res)=>{
            this.setState({roles:res.data})
        })
    }

    render() {
        return (
            <div>
                <HeaderComponent/>
                <div className="container">
                    <div className="row">
                        <button style={{marginTop: "15px",marginBottom: "10px"}} className="btn btn-primary" onClick={()=>this.goAdd()}>Add
                            Role
                        </button>
                    </div>
                    <div className="row">
                        <table className="table table-striped table table-bordered">
                            <thead>
                            <tr>
                                <th>Place</th>
                                <th>Actions</th>
                            </tr>
                            </thead>
                            {
                                this.state.roles.map(
                                    role=>
                                        <tbody >
                                                    <tr key={role.id}>
                                                        <td>{role.name}</td>
                                                        <td>
                                                            <button
                                                                onClick={() => this.editRole(role)}
                                                                className="btn btn-info"> Update
                                                            </button>
                                                            <button style={{marginLeft: "6px"}}
                                                                    onClick={() => this.deleteRole(role.id)}
                                                                    className="btn btn-danger"> Delete
                                                            </button>
                                                        </td>
                                                    </tr>

                                        </tbody>
                                )
                            }
                        </table>
                    </div>
                </div>
            </div>
        );
    }
}

export default ListAllRolesComponent;