import React, {Component} from 'react';
import ProductService from "../services/ProductService";
import HeaderComponent from "./HeaderComponent";

class ListCartsComponent extends Component {
    constructor(props) {
        super(props)
        this.state={
            carts:[]
        }
    }
    componentDidMount(){
        if(localStorage.getItem("username") ==null && localStorage.getItem("password") == null ){
            this.props.history.push('/');
        }
        ProductService.listCarts().then(res =>{
            this.setState({carts:res.data})
        });
    }




    render() {
        return (
            <div>
                <HeaderComponent/>
                <div  className="container" style={{marginTop: "15px"}}>
                    <div className="row" style={{overflow: "auto",height:"37rem"}}>
                        <table className="table table-striped table-bordered">
                            <thead>
                            <tr>
                                <th>Date</th>
                                <th>Piece</th>
                                <th>Total</th>
                                <th>Product ID</th>
                                <th>Table ID</th>
                                <th>Category ID</th>
                                <th>Waiter ID</th>
                            </tr>
                            </thead>
                            <tbody>
                            {
                                this.state.carts.map(
                                    cart =>
                                        <tr>
                                            <td>{cart.createDate}</td>
                                            <td>{cart.piece}</td>
                                            <td>{cart.total}</td>
                                            <td>{cart.productId}</td>
                                            <td>{cart.tableId}</td>
                                            <td>{cart.categoryId}</td>
                                            <td>{cart.waiterId}</td>
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

export default ListCartsComponent;