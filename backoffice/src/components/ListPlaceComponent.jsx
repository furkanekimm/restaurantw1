import React, {Component} from 'react';
import TableService from "../services/TableService";
import HeaderComponent from "./HeaderComponent";

class ListPlaceComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            place: []
        }
        this.editPlace = this.editPlace.bind(this);
        this.deletePlace=this.deletePlace.bind(this);
    }

    componentDidMount() {
        if(localStorage.getItem("username")==null || localStorage.getItem("password")==null){
            this.props.history.push('/')
        }
        TableService.getAllPlaces().then((res) => {
            this.setState({place: res.data})
        });
    }

    addPlace = (event) => {
        this.props.history.push("/addplace");
    }
    editPlace(place){
        this.props.history.push({
            pathname:'/updateplace',
            state:{
                place:place
            }
        });
    }

    deletePlace(id){
        TableService.deletePlaceRest(id).then(res=>{
            window.location.reload();
        })
    }

    render() {
        return (
            <div>
                <HeaderComponent/>
                <div className="container">
                    <div className="row">
                        <button style={{marginTop: "15px",marginBottom: "10px"}} className="btn btn-primary" onClick={this.addPlace}>Add
                            Place
                        </button>
                    </div>
                    <div className="row">
                        <table className="table table-striped table-bordered">
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>Table Piece</th>
                                <th>Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            {
                                this.state.place.map(
                                    place =>
                                        <tr key={place.id}>
                                            <td>{place.name}</td>
                                            <td>{place.tablePiece}</td>
                                            <td>
                                                <button
                                                    onClick={() => this.editPlace(place)}
                                                    className="btn btn-info"> Update
                                                </button>
                                                <button style={{marginLeft: "6px"}}
                                                        onClick={() => this.deletePlace(place.id)}
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

export default ListPlaceComponent;