import React, {Component} from 'react';
import UserService from "../services/UserService";
import HeaderComponent from "./HeaderComponent";

class UpdateUserComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            id: this.props.history.location.state?.user.id,
            username:this.props.history.location.state?.user.username,
            password:this.props.history.location.state?.user.password,
            role:this.props.history.location.state?.user.rolesId,
            authorities:[]
        }
        this.changeusernameHandler = this.changeusernameHandler.bind(this);
        this.changepasswordHandler = this.changepasswordHandler.bind(this);
        this.updateUser = this.updateUser.bind(this);
        this.changeMultiCate = this.changeMultiCate.bind(this);
    }

    componentDidMount() {
        UserService.listAllRoles().then((res)=>{
            this.setState({authorities:res.data})
        });
    }

    changeMultiCate(id) {
        if (this.state.role.includes(id) !== true) {
            this.state.role.push(id);
            console.log(this.state.role)
        } else {
            for (let i = 0; i < this.state.role.length; i++) {
                if (id === this.state.role[i]) {
                    this.state.role.splice(i, 1);
                    console.log(this.state.role)
                }
            }
        }


    }


    updateUser =(e) =>{
        e.preventDefault();
        let User ={
            id:this.state.id,
            username:this.state.username,
            password:this.state.password,
            rolesId:this.state.role
        };
        UserService.updateUser(User).then(res=>{
           this.props.history.push('/listuser')
        });
    }
    changeusernameHandler = (event) =>{
        this.setState({username: event.target.value})
    }

    changepasswordHandler = (event) =>{
        this.setState({password: event.target.value})
    }


    cancel() {
        this.props.history.push('/listuser');
    }

    render() {
        const multiSelect = this.state.role;
        const categories = this.state.authorities;
        const selected = [];
        for (let i = 0; i < categories.length; i++) {
            if (multiSelect.includes(categories[i].id)) {
                selected.push(<div className="row col-md -12"><label className="col-md-12"  ><input type="checkbox" defaultChecked="true"
                                                                                                    onClick={() => this.changeMultiCate(categories[i].id)}/>{categories[i].name}
                </label></div>)
            } else {
                selected.push(<div className="row col-md -12"><label className="col-md-12"><input  type="checkbox"
                                                                                                   onClick={() => this.changeMultiCate(categories[i].id)}/>{categories[i].name}
                </label></div>)
            }

        }
        return (
            <div>
                <HeaderComponent/>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Update User</h3>
                            <div className="card-body">
                                <form>

                                    <div className="form-group">
                                        <label> username </label>
                                        <input placeholder="username" name="username" className="form-control"
                                               value={this.state.username} onChange={this.changeusernameHandler}/>
                                    </div>
                                    <div className="form-group">
                                        <label> password </label>
                                        <input placeholder="password" name="password" className="form-control" type="password"
                                               value={this.state.password} onChange={this.changepasswordHandler}/>
                                    </div>
                                    <div className="form-group">

                                        <label> Category </label>
                                        <div className="checkbox" style={{height: "5.5rem", overflow: "auto"}}>
                                            {selected}
                                        </div>
                                    </div>
                                    <button className="btn btn-success" onClick={this.updateUser}> Update</button>
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

export default UpdateUserComponent;