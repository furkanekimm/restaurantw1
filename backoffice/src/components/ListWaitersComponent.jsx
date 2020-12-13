import React, {Component} from 'react';
import WaiterService from "../services/WaiterService";
import HeaderComponent from "./HeaderComponent";

class ListWaitersComponent extends Component {
        constructor(props) {
            super(props);
            this.state={
                waiters:[]
            }
            this.goAddWaiter = this.goAddWaiter.bind(this);
            this.editWaiter = this.editWaiter.bind(this);
            this.deleteWaiter = this.deleteWaiter.bind(this);
        }
    componentDidMount() {
            WaiterService.getAllWaiters().then((res)=>{
                this.setState({waiters:res.data})})
    }

    editWaiter(waiter){
            this.props.history.push({
                pathname:'/updatewaiter',
                state:{
                    waiter:waiter
                }
            });
    }

    deleteWaiter(id){
        WaiterService.deleteWaiter(id).then(res=>{
            if(res.data === true){
                console.log("Silme başarılı...");
                window.location.reload();
            }
        })
    }

    goAddWaiter=(e)=>{
            this.props.history.push("/addwaiter")
    }

    render() {
        return (
            <div>

                <div className="container">
                    <HeaderComponent/>
                    <div className="row">
                        <button onClick={this.goAddWaiter} className="btn btn-info ml-3 mt-2">Add Waiter</button>
                    </div>
                    <div className="table mt-3 ">
                        <table className="table table-striped border overflow-auto">
                            <thead >
                                <tr>
                                    <th>Image</th>
                                    <th>Name</th>
                                    <th>LastName</th>
                                    <th>PhoneNumber</th>
                                    <th>Email</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                            {
                                this.state.waiters.map(
                                    waiter =>
                                        <tr>
                                            <td><img
                                                style={{marginLeft:"0rem",marginRight:"10px",borderRadius:"30px"}}
                                                src={'data:image/png;base64,' + waiter.media.fileContent}
                                                width="45rem" height="39rem"
                                            /></td>
                                            <td>{waiter.waiterName}</td>
                                            <td>{waiter.waiterLastName}</td>
                                            <td>{waiter.phoneNumber}</td>
                                            <td>{waiter.email}</td>

                                            <td>
                                                <button
                                                    onClick={() => this.editWaiter(waiter)}
                                                    className="btn btn-info"> Update
                                                </button>
                                                <button style={{margin: "6px"}}
                                                        onClick={() => this.deleteWaiter(waiter.id)}
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

export default ListWaitersComponent;