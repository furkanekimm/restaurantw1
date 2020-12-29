import React, {Component} from 'react';
import TableService from "../services/TableService";
import HeaderComponent from "./HeaderComponent";
import Loading from "./Loading";

class PlaceCartComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            place: [],
            loading:true
        }
        this.goTable = this.goTable.bind(this);
    }

    goTable(table, id, placeName) {
        this.props.history.push({
            pathname: '/tables',
            state: {
                categoryId: id,
                table: table,
                placeName: placeName
            }
        });

    }

    componentDidMount() {
        TableService.getPlace().then((res) => {
            this.setState({place: res.data,loading:false})
        })
    }

    render() {
        return (
            <>
                {this.state.loading == true ?
                    <Loading/> :
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
                                                        <img
                                                            style={{height: "40px",marginLeft:"12px",borderRadius:"10px"}} className="card-img-top"
                                                            src={'data:image/png;base64,' + place.media.fileContent}
                                                        />
                                                        <a onClick={() => this.goTable(place.tablePiece, place.id, place.name)}><i
                                                            className="fa fa-behance fa-3x"
                                                            aria-hidden="true"></i>
                                                            <div className="box-title">
                                                                <h3 className="box-text1" >{place.name}</h3>
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
                }
            </>
        );
    }
}

export default PlaceCartComponent;