import React, {Component} from 'react';
import TableService from "../services/TableService";
import HeaderComponent from "./HeaderComponent";

class PlaceCartComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            place: []
        }
        this.goTable = this.goTable.bind(this);
    }

    goTable(table,id,placeName){
        this.props.history.push({
            pathname:'/tables',
            state :{
                categoryId:id,
                table:table,
                placeName:placeName
            }
        });

    }

    componentDidMount() {
        TableService.getPlace().then((res) => {
            this.setState({place: res.data})
        })
    }

    render() {
        return (
            <div className="social-box">
                <div className="container">
                    <HeaderComponent/>
                    <div className="row">
                        {
                            this.state.place.map(
                                place =>

                                    <div className="col-lg-4 col-xs-12 text-center" key={place.id}>
                                        <div className="box">
                                            <div className="box-btn">
                                                <a onClick={()=> this.goTable(place.tablePiece,place.id,place.name)}><i className="fa fa-behance fa-3x"
                                                      aria-hidden="true"></i>
                                                    <div className="box-title">
                                                        <h3 className="box-text1">{place.name}</h3>
                                                    </div>
                                                    <div className="box-text">
                                                        <span></span>
                                                    </div>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                            )
                        }
                    </div>
                </div>
            </div>
        );
    }
}

export default PlaceCartComponent;