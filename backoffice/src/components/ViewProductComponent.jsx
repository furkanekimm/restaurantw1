import React, {Component} from 'react';
import HeaderComponent from "./HeaderComponent";

class ViewProductComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            product: this.props.history.location.state?.product
        }
    }

    render() {
        return (
            <div>
                <div className="container">
                    <HeaderComponent/>
                    <div className="row">
                        <h2 className="col-md-12 text-center">Product View Detail</h2>
                        <div className="col-md-12 mt-2">
                            <div className="card col-md-6 offset-md-3">
                                <img style={{width: "32rem", height: "20rem"}} className="card-img mt-3"
                                     src={this.state.product.urlToImage}/>
                                <div className="row col-md-12">
                                    <h3 className="card-text col-md-6">Product Name</h3>
                                    <h3 className="card-text col-md-6 text-center">{this.state.product.productName}</h3>
                                </div>
                                <div className="row col-md-12">
                                    <h3 className="card-text col-md-6">Description</h3>
                                    <h3 className="card-text col-md-6 text-center">{this.state.product.description}</h3>
                                </div>
                                <div className="row col-md-12">
                                    <h3 className="card-text col-md-6">Product Price</h3>
                                    <h3 className="card-text col-md-6 text-center">{this.state.product.price} ₺</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default ViewProductComponent;