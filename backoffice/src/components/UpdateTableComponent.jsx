import React, {Component} from 'react';
import HeaderComponent from "./HeaderComponent";
import TableService from "../services/TableService";

class UpdateTableComponent extends Component {
    constructor(props) {
        super(props);
        this.state={
            table:this.props.history.location.state?.table,
            placeid:this.props.history.location.state?.id,
            name:this.props.history.location.state?.table.name,
            id:this.props.history.location.state?.table.id,
            places:[]
        }
        this.changeTableNameHandler=this.changeTableNameHandler.bind(this);
        this.updateTable=this.updateTable.bind(this);
        this.changePlaceRestId=this.changePlaceRestId.bind(this);
        console.log(this.state.table);
        console.log(this.state.placeid);

    }

    updateTable=(e)=>{
        e.preventDefault();
        let Table ={
            id:this.state.id,
            name:this.state.name
        }
        TableService.updateTable(Table,this.state.placeid).then(res=>{
            this.props.history.push({
                pathname:'/listtable'
            });
        })


    }
    componentDidMount() {
        TableService.getAllPlaces().then((res)=>{
            this.setState({places:res.data})
        });
    }


    changeTableNameHandler=(event)=>{
        this.setState({name:event.target.value})
   }
    changePlaceRestId=(event)=>{
        this.setState({placeid:event.target.value})
        console.log(this.state.placeid)
    }

    cancel() {
        this.props.history.push('/product');
    }

    render() {
        return (
            <div>
                <div>
                    <HeaderComponent/>
                    <div className="container">
                        <div className="row">
                            <div className="card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-center">Update Table</h3>
                                <div className="card-body">
                                    <form>
                                       <div className="form-group">
                                            <label> Places </label>
                                            <select onChange={this.changePlaceRestId} id="id"
                                                     className="form-control">
                                                {
                                                    this.state.places.map(
                                                        place=>
                                                            <option selected={this.state.placeid==place.id} key={place.id}
                                                                    value={place.id}>{place.name}</option>
                                                    )
                                                }
                                            </select>
                                        </div>
                                        <div className="form-group">
                                            <label> Table Name </label>
                                            <input placeholder="Table Name" name="name" className="form-control"
                                                   value={this.state.name} onChange={this.changeTableNameHandler}/>
                                        </div>
                                        <button className="btn btn-success" onClick={this.updateTable}>Update</button>
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

export default UpdateTableComponent;