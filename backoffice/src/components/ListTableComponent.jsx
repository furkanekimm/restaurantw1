import React, {Component} from 'react';
import TableService from "../services/TableService";
import HeaderComponent from "./HeaderComponent";

class ListTableComponent extends Component {
    constructor(props) {
        super(props);
        this.state={
            places:[]
        }
        this.editTable = this.editTable.bind(this);
        this.deleteTable=this.deleteTable.bind(this);
    }
    componentDidMount() {
        if(localStorage.getItem("username")==null || localStorage.getItem("password")==null){
            this.props.history.push('/')
        }
        TableService.getAllPlaces().then((res)=>{
            this.setState({places:res.data})
        })
    }

    editTable(table,id,places){
        this.props.history.push({
            pathname:'/updatetable',
            state:{
                table:table,
                id:id,
            }

        })
        console.log(table);
        console.log(id);
    }

    deleteTable(tableid){
        TableService.deleteTable(tableid).then(res=>{
            window.location.reload();
        })
    }

    addTable=(event)=>{
        this.props.history.push("/addtable")
    }

    render() {
        return (
            <div>
                <HeaderComponent/>
                <div className="container">
                    <div className="row">
                        <button style={{marginTop: "15px",marginBottom: "10px"}} className="btn btn-primary" onClick={this.addTable}>Add
                            Table
                        </button>
                    </div>
                    <div className="row">
                        <table className="table table-striped table table-bordered">
                            <thead>
                                <tr>
                                    <th>Place</th>
                                    <th>Table Name</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            {
                                this.state.places.map(
                                    place=>
                                        <tbody>
                                    {

                                        place.tableRests.map(
                                                table=>
                                                    <tr>
                                                    <td>{place.name}</td>
                                                    <td>{table.name}</td>
                                                    <td>
                                                        <button
                                                            onClick={() => this.editTable(table, place.id)}
                                                            className="btn btn-info"> Update
                                                        </button>
                                                        <button style={{marginLeft: "6px"}}
                                                                onClick={() => this.deleteTable(table.id)}
                                                                className="btn btn-danger"> Delete
                                                        </button>
                                                    </td>
                                                    </tr>
                                        )

                                    }
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

export default ListTableComponent;