import React, {Component} from 'react';
import TableService from "../services/TableService";
import HeaderComponent from "./HeaderComponent";

class CreateTableRestComponent extends Component {
    constructor(props) {
        super(props)
        this.state={
            name:'',
            places:[]
        }
        this.changeTableNameHandler=this.changeTableNameHandler.bind(this);
    }
    componentDidMount() {
        TableService.getAllPlaces().then((res)=>{
            this.setState({places:res.data})
        });
    }

    saveTable=(e)=>{
        e.preventDefault();
        let table ={
            name:this.state.name
        };
        TableService.addTable(table,document.getElementById("id").value).then(res=>{
            this.props.history.push('/listtable');
        });

    }

    changeTableNameHandler=(event)=>{
        this.setState({name:event.target.value})
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
                                <h3 className="text-center">Add Table</h3>
                                <div className="card-body">
                                    <form>
                                        <div className="form-group">
                                            <label> Places </label>
                                            <select  id="id"
                                                    className="form-control">
                                                {
                                                    this.state.places.map(
                                                        place =>
                                                            <option key={place.id}
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
                                        <button className="btn btn-success" onClick={this.saveTable}>Save</button>
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

export default CreateTableRestComponent;