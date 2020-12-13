import React, {Component} from 'react';
import TableService from "../services/TableService";

class UpdatePlaceRestComponent extends Component {
    constructor(props) {
        super(props);
        this.state={
            id:this.props.history.location.state?.place.id,
            name:this.props.history.location.state?.place.name
        }
        this.updatePlace=this.updatePlace.bind(this);
        this.changeNameHandler=this.changeNameHandler.bind(this);
    }


    cancel() {
        this.props.history.push('/listcategories');
    }

    updatePlace=(e)=>{
        e.preventDefault();
        let place ={
            id:this.state.id,
            name:this.state.name
        }
        TableService.updatePlaceRest(place).then(res=>{
            this.props.history.push("/listplace");
        })
    }

    changeNameHandler=(e)=>{
        this.setState({name:e.target.value})
    }

    render() {
        return (
            <div className="container">
                <div className="row">
                    <div className="card col-md-6 offset-md-3 offset-md-3">
                        <h3 className="text-center">Update Place in Restaurant</h3>
                        <div className="card-body">
                            <form>
                                <div className="form-group">
                                    <label>Place Name</label>
                                    <input placeholder="Place Name" name="name" className="form-control"
                                           value={this.state.name} onChange={this.changeNameHandler}/>
                                </div>
                                <div className="form-group">
                                    <button className="btn btn-success" onClick={this.updatePlace}> Update</button>
                                    <button className="btn btn-danger" onClick={this.cancel.bind(this)}
                                            style={{marginLeft: "10px"}}>Cancel
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default UpdatePlaceRestComponent;