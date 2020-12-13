import React, {Component} from 'react';
import HeaderComponent from "./HeaderComponent";
import TableService from "../services/TableService";

class CreatePlaceRestComponent extends Component {
    constructor(props) {
        super(props)
        this.state ={
            name:'',
            tablePiece:0
        }
        this.changePlaceHandler = this.changePlaceHandler.bind(this);
        this.changeTablePieceHandler = this.changeTablePieceHandler.bind(this);
    }

    savePlace=(e)=>{
        e.preventDefault();
        let placeRest ={
            name : this.state.name,
            tablePiece :this.state.tablePiece
        };
        TableService.addPlace(placeRest).then(res=>{
            this.props.history.push('/listplace');
        });

    }
    changePlaceHandler=(event)=>{
        this.setState({name: event.target.value})
    }

    changeTablePieceHandler=(event)=>{
        this.setState({tablePiece:event.target.value})
    }

    cancel(){
        this.props.history.push('/listplace');
    }

    render() {
        return (
            <div>
                <HeaderComponent/>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Add Category</h3>
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label> Category Name </label>
                                        <input placeholder="Category Name" name="name" className="form-control"
                                               value={this.state.name} onChange={this.changePlaceHandler}/>
                                    </div>
                                    <div className="form-group">
                                        <label> Table Piece </label>
                                        <input placeholder="Table Piece" name="tablePiece" className="form-control"
                                               value={this.state.tablePiece} onChange={this.changeTablePieceHandler}/>
                                    </div>
                                    <button className="btn btn-success" onClick={this.savePlace}>Save</button>
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

export default CreatePlaceRestComponent;