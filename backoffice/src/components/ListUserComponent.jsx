import React, {Component} from 'react';
import UserService from "../services/UserService";
import HeaderComponent from "./HeaderComponent";

class ListUserComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            users :[]
        }
        this.editUser = this.editUser.bind(this);
        this.deleteUser = this.deleteUser.bind(this);
        //
    }

    deleteUser(id){
        UserService.deleteUser(id).then(res =>{
           this.setState({users: this.state.users.filter(user => user.id !== id)});
        });
    }

    componentDidMount(){
        if(localStorage.getItem("username") ==null && localStorage.getItem("password") == null ){
            this.props.history.push('/');
        }
        UserService.listAllUser().then(res =>{
            this.setState({users:res.data})
        });
    }
    editUser(user){
        this.props.history.push({
            pathname:'/updateUser',
            state:{
                user:user
            }
    });
    }

    addUser=(e)=>{
        this.props.history.push('/newperson');
    }


    render() {
        return (
            <div >
                <HeaderComponent/>
                <div className="container" >
                <div className="row">
                    <button style={{marginTop: "15px",marginBottom: "10px"}} className="btn btn-primary"onClick={this.addUser} >Add User</button>
                </div>
                <div className="row">
                    <table className="table table-striped table-bordered">
                        <thead >
                        <tr>
                            <th>username</th>
                            <th>password</th>
                            <th>Role</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.users.map(
                                user =>
                                    <tr key={user.id}>
                                        <td>{user.username}</td>
                                        <td>{user.password}</td>
                                        <td>{user.role}</td>
                                        <td>
                                            <button onClick={() => this.editUser(user)}
                                                    className="btn btn-info"> Update
                                            </button>
                                            <button style={{marginLeft: "6px"}} onClick={() => this.deleteUser(user.id)}
                                                    className="btn btn-danger"> Delete
                                            </button>
                                        </td>
                                    </tr>
                            )
                        }
                        </tbody>
                    </table>
                </div>

                </div>
            </div>
        );
    }
}

export default ListUserComponent;