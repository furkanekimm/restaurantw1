import React, {Component} from 'react';
import HeaderComponent from "./HeaderComponent";
import WaiterService from "../services/WaiterService";

class TableCartComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            table: this.props.history.location.state?.table,
            categoryId: this.props.history.location.state?.categoryId,
            placeName: this.props.history.location.state?.placeName,
            waiters: [],
            tableId: '',
            waiterId: '',
            waiterName: '',
            selectedWaiter:'',
            totalCart:0,
        }
        this.goCart = this.goCart.bind(this);
        this.waiterSet = this.waiterSet.bind(this);
        this.changeTableId = this.changeTableId.bind(this);
    }

    changeTableId(id,name,totalCart) {
        this.setState({tableId: id,selectedWaiter:name,totalCart:totalCart})
        console.log(id)
    }

    waiterSet(id, name) {
        this.setState({waiterId: id, waiterName: name})

        console.log(id)
    }

    componentDidMount() {

        WaiterService.getAllWaiters().then((res) => {
            this.setState({waiters: res.data})
        })
    }

    goCart() {
        this.props.history.push({
            pathname: '/products',
            state: {
                id: this.state.tableId,
                categoryId: this.state.categoryId,
                waiterId: this.state.waiterId,
                waiterName: this.state.waiterName,
                placeName: this.state.placeName
            }
        })
    }

    render() {
        const count = [];
        for (let i = 1; i <= this.state.table; i++) {
            if (localStorage.getItem(`${this.state.categoryId}-${i}`) !== null) {
                let inCart = JSON.parse(localStorage.getItem(`${this.state.categoryId}-${i}`));
                count.push(<div className="col-lg-4 col-xs-12 text-center">
                    <div className="box" style={{background: "#dd2800"}}>
                        <a className="text-center ml-3 mt-5" style={{color: "black"}}>[{inCart.carts.length}]</a>
                        <div className="box-btn">
                            <a onClick={() => this.changeTableId(i,inCart.waiterName,inCart.totalCart)} data-toggle="modal" data-target="#exampleModal">
                                <i className="fa fa-behance fa-3x" aria-hidden="true"></i>
                                <div className="box-title">
                                    <h3 className="box-text1">Table({i})</h3>
                                </div>
                                <div className="box-text">
                                    <span></span>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>)
            } else {
                count.push(<div className="col-lg-4 col-xs-12 text-center">
                    <div className="box" style={{background: "#a6dced"}}>
                        <a className="text-center ml-0 mt-5" style={{color: "black",visibility:"hidden"}} >EMPTY</a>
                        <div className="box-btn">
                            <a onClick={() => this.changeTableId(i)} data-toggle="modal" data-target="#exampleModal">
                                <i className="fa fa-behance fa-3x" aria-hidden="true"></i>
                                <div className="box-title">
                                    <h3 className="box-text1">Table({i})</h3>

                                </div>
                                <div className="box-text">
                                    <span></span>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>)
            }
            ;
        }

        return (
            <div className="social-box">
                <div className="container">
                    <HeaderComponent/>
                    <div className="modal fade " id="exampleModal" tabIndex="-1" role="dialog"
                         aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div className="modal-dialog" role="document">
                            <div className="modal-content">
                                <div className="modal-header">
                                    <h5 className="modal-title" id="exampleModalLabel"><span style={{color: "blue"}}>Selected Waiter : </span>{this.state.waiterName}
                                    </h5>
                                    <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div className="modal-body">
                                    {
                                        this.state.waiters.map(
                                            waiter =>
                                                <tr key={waiter.id}>
                                                    <img
                                                        style={{marginLeft:"0rem",marginRight:"10px",borderRadius:"40px"}}
                                                        src={'data:image/png;base64,' + waiter.media.fileContent}
                                                        width="45rem" height="39rem"
                                                    />
                                                    <td className="col-sm-2">{waiter.waiterName}</td>
                                                    <td>
                                                        <button className="btn btn-info"
                                                                onClick={() => this.waiterSet(waiter.id, waiter.waiterName)}>Select
                                                        </button>
                                                    </td>
                                                </tr>
                                        )
                                    }
                                </div>
                                <div className="modal-footer">
                                    <button type="button" className="btn btn-secondary" data-dismiss="modal">Close
                                    </button>
                                    <button type="button" className="btn btn-primary" data-dismiss="modal"
                                            onClick={() => this.goCart()}>Go Cart
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="row" style={{overflow: "auto",height:"37rem"}}>
                        {count}
                    </div>
                </div>
            </div>
        );
    }
}

export default TableCartComponent;